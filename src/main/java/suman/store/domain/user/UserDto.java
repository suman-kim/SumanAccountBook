package suman.store.domain.user;

import lombok.Getter;

@Getter
public class UserDto {

    private String userId;
    private String name;
    private String password;
    private String phone;
    private String email;
    private UserRole userRole;
}
