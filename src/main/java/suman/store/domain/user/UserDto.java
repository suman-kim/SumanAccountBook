package suman.store.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {

    private String userId;
    private String name;
    private String phone;
    private String email;
    private UserRole userRole;

    @Builder
    public UserDto(String userId,String name,String phone,String email, UserRole userRole) {
        this.userId = userId;
        this.userRole = userRole;
        this.email = email;
        this.name = name;
        this.phone = phone;

    }
}
