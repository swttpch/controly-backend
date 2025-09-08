package br.com.controly.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class NewUserDTO {
    @NotBlank
    private String name;

    private String nickname;
    @NotNull
    @Size(min=8)
    private String password;
    @NotNull
    @Email
    private String email;
}
