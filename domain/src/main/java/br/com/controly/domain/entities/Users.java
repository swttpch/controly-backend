package br.com.controly.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nickname;
    private String password;
    private String email;
    private String about = "";
}
