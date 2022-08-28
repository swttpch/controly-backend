package controly.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Usuario {
    private int id;
    private String nome;
    private String apelido;
    private int avatar;

    private String senha;
    private String email;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{\n" +
                "id=" + id +
                ",\nnome='" + nome + '\'' +
                ",\napelido='" + apelido + '\'' +
                ",\navatar=" + avatar +
                ",\nsenha='" + senha + '\'' +
                ",\nemail='" + email + '\'' +
                "\n}";
    }
}
