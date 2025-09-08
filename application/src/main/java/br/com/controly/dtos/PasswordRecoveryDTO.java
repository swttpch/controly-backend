package br.com.controly.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PasswordRecoveryDTO {
    @NotNull
    @Email
    private String email;
}
