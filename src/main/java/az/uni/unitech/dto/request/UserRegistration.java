package az.uni.unitech.dto.request;

import az.uni.unitech.config.validation.Password;
import az.uni.unitech.config.validation.Pin;
import az.uni.unitech.config.validation.Username;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistration {

    @Username
    private String username;

    @Pin
    private String pin;

    @Password
    private String password;

    @NotNull
    @NotBlank
    @Email
    private String email;

}