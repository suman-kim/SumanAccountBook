package suman.store.domain.user;

import lombok.Getter;

@Getter
public class UserDto {

    public String userId;
    public String name;
    public String password;
    public String phone;
    public String email;
    public UserRole userRole;
}
