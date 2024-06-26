package org.backend.evidencedatabasewebapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationForm {
    @Size(min = 5, max = 16, message = "Логин должен содержать от 5 до 16 символов")
    @NotBlank
    private String username;

    @Size(min = 6, max = 32, message = "Пароль должен содержать от 6 до 32 символов")
    @NotBlank
    private String password;

    @Size(min = 6, max = 32, message = "Пароль должен содержать от 6 до 32 символов")
    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String invitationKey;
}
