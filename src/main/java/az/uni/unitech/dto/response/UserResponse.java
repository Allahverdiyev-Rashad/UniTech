package az.uni.unitech.dto.response;

import lombok.Data;

@Data
public class UserResponse {

    private String username;
    private String pin;
    private String email;
    private String password;

}