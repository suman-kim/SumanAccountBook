package suman.store.domain.user;


import lombok.*;
import suman.store.domain.memo.Memo;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Getter
public class User {

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

    @OneToMany(mappedBy = "user")
    private List<Memo> memoList = new ArrayList<>();

    @Builder
    public User(String userId,String name,String password,String phone,String email, UserRole userRole) {
        this.password = password;
        this.userId = userId;
        this.userRole = userRole;
        this.email = email;
        this.name = name;
        this.phone = phone;

    }

    public void UpdateUser(String userid, String name, String phone,String email){
        this.userId = userid;
        this.name =  name;
        this.phone = phone;
        this.email = email;

    }

}
