package controly.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name="tbUser")
@Data @NoArgsConstructor
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name= "idUser")
    private Long idUser;
    @NotNull
    private String name;
    @NotNull
    private String nickname;

    private String avatar;

    @JsonIgnore
    private String password;

    private String email;
    @JsonIgnore
    private Boolean isActive =true;

    @JsonIgnore
    private Long idGithub;

    private String about = "";

    public UserEntity(String name, String nickname, String password, String email) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.isActive = true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setAtivo(Boolean ativo) {
        isActive = ativo;
    }


}
