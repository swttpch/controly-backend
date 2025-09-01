package br.com.controly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Email mentioned does not exists")
public class UsersEmailNotFould extends RuntimeException{
}
