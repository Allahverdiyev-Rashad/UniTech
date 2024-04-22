package az.uni.unitech.dto.request;

import az.uni.unitech.config.validation.Password;
import az.uni.unitech.config.validation.Pin;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest {

    @Pin
    private String pin;

    @Password
    private String password;

}