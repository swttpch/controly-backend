package br.com.controly.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PasswordRecoveryDTO {
    @NotNull
    @Email
    private String email;
}
