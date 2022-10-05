package controly.util;

public class Teste {
    public static void main(String[] args) {
        GeradorSenha gerador = new GeradorSenha();
        String novaSenha = gerador.gerarSenha();
        System.out.println(novaSenha);
    }
}
