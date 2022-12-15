package controly.util;

public class Teste {
    public static void main(String[] args) {
        PasswordGenerator gerador = new PasswordGenerator();
        String novaSenha = gerador.generate();
        System.out.println(novaSenha);
    }
}
