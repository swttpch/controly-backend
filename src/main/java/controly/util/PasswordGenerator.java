package controly.util;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordGenerator {

    private String newPassword = "#";
    private String[] upperCaseLetters = {"A","B","C","D"
            ,"E","F","G","H","I","J","K","L","M"
            ,"N","O","P","Q","R","S","T","U","V"
            ,"W","X","Y","Z"};
    private String[] lowerCaseLetters = {"a","b","c","d"
            ,"e","f","g","h","i","j","k","l","m"
            ,"n","o","p","q","r","s","t","u","v"
            ,"w","x","y","z"};
    private String[] symbols = {"!","@","$","%","&"};
    private Integer[] numbers = {0,1,2,3,4,5,6,7,8,9};

    public String generate(){
        Random random = new Random();
        for(int i = 0; i < 14; i++){

            Integer option = random.nextInt(3);
            Integer randomLetter = random.nextInt(25);
            Integer randomSymbol = random.nextInt(5);
            Integer randomNumber = random.nextInt(9);

            switch (option){

                case 0:
                    newPassword += getUpperCaseLetter(randomLetter);
                    break;
                case 1:
                    newPassword += getLowerCaseLetter(randomLetter);
                    break;
                case 2:
                    newPassword += getSymbol(randomSymbol);
                    break;
                case 3:
                    newPassword += getNumber(randomNumber);
                    break;
                default:
                    break;
            }
        }

        return newPassword;

    }

    private String getUpperCaseLetter(Integer posicao){

        for(int i = 0; i < upperCaseLetters.length; i++){
            if(i==posicao){
                return upperCaseLetters[i];
            }
        }
        return "A";
    }

    private String getLowerCaseLetter(Integer posicao){

        for(int i = 0; i < lowerCaseLetters.length; i++){
            if(i==posicao){
                return lowerCaseLetters[i];
            }
        }
        return "a";
    }

    private String getSymbol(Integer posicao){

        for(int i = 0; i < symbols.length; i++){
            if(i==posicao){
                return symbols[i];
            }
        }
        return "@";
    }

    private Integer getNumber(Integer posicao){

        for(int i = 0; i < numbers.length; i++){
            if(i==posicao){
                return numbers[i];
            }
        }
        return 0;
    }

}
