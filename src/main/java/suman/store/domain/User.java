package suman.store.domain;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String userId;
    @NotNull
    private String name;
    @NotNull
    private String password;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Builder
    private User(String password, UserRole userRole, String userId) {
        this.password = password;
        this.userId = userId;
        this.userRole = userRole;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 계정의 권한 목록을 리턴
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRole.getValue()));
        return roles;
    }


    @Override
    public String getPassword() {
        return this.password; // 계정의 비밀번호 리턴
    }

    @Override
    public String getUsername() {
        return this.userId; // 계정의 고유한 값 리턴
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
