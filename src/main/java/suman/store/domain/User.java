package suman.store.domain;


import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Getter
@Setter
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

    @Builder
    private User(String password, UserRole userRole, String userId) {
        this.password = password;
        this.userId = userId;
        this.userRole = userRole;

    }

}
