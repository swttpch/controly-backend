package controly.util;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GeradorSenha {

    private String newSenha = "#";
    private String[] letraG = {"A","B","C","D"
            ,"E","F","G","H","I","J","K","L","M"
            ,"N","O","P","Q","R","S","T","U","V"
            ,"W","X","Y","Z"};
    private String[] letraM = {"a","b","c","d"
            ,"e","f","g","h","i","j","k","l","m"
            ,"n","o","p","q","r","s","t","u","v"
            ,"w","x","y","z"};
    private String[] simbolos = {"!","@","$","%","&"};
    private Integer[] numeros = {0,1,2,3,4,5,6,7,8,9};

    public String gerarSenha(){
        Random random = new Random();
        for(int i = 0; i < 14; i++){

            Integer opcao = random.nextInt(3);
            Integer randomLetra = random.nextInt(25);
            Integer randomSimbolo = random.nextInt(5);
            Integer randomNumero = random.nextInt(9);

            switch (opcao){

                case 0:
                    newSenha += getMaiuscula(randomLetra);
                    break;
                case 1:
                    newSenha += getMinuscula(randomLetra);
                    break;
                case 2:
                    newSenha += getSimbolo(randomSimbolo);
                    break;
                case 3:
                    newSenha += getNumero(randomNumero);
                    break;
                default:
                    break;
            }
        }

        return newSenha;

    }

    private String getMaiuscula(Integer posicao){

        for(int i = 0; i <letraG.length; i++){
            if(i==posicao){
                return letraG[i];
            }
        }
        return "A";
    }

    private String getMinuscula(Integer posicao){

        for(int i = 0; i <letraM.length; i++){
            if(i==posicao){
                return letraM[i];
            }
        }
        return "a";
    }

    private String getSimbolo(Integer posicao){

        for(int i = 0; i <simbolos.length; i++){
            if(i==posicao){
                return simbolos[i];
            }
        }
        return "@";
    }

    private Integer getNumero(Integer posicao){

        for(int i = 0; i <numeros.length; i++){
            if(i==posicao){
                return numeros[i];
            }
        }
        return 0;
    }

}
