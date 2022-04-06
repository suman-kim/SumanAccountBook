package suman.store.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

    public String userId;
    public String name;
    public String password;
    public String phone;
    private String email;

    private UserRole userRole;
}
