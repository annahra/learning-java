import edu.duke.*;
/**
 * Breaks Caesar Cipher OOP
 * 
 * @author Annah 
 * @version April 28, 2020
 */
public class CaesarCipherTwo {
    
    private String alphabetUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String alphabetLow = "abcdefghijklmnopqrstuvwxyz";
    
    private String shiftedAlphabetUp1;
    private String shiftedAlphabetLow1;
    
    private String shiftedAlphabetUp2;
    private String shiftedAlphabetLow2;
    
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabetUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLow = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabetUp1 = alphabetUp.substring(key1)+alphabetUp.substring(0,key1);
        shiftedAlphabetLow1 = alphabetLow.substring(key1)+alphabetLow.substring(0,key1);
        shiftedAlphabetUp2 = alphabetUp.substring(key2)+alphabetUp.substring(0,key2);
        shiftedAlphabetLow2 = alphabetLow.substring(key2)+alphabetLow.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i=0; i < input.length(); i++){
            //grab the current character
            char currChar = encrypted.charAt(i);
            //find the indexes in regular alphabet
            int indUp = alphabetUp.indexOf(currChar);
            int indLow = alphabetLow.indexOf(currChar);
            //check to see if it currChar is even a letter
            if(indUp != -1 || indLow != -1){
                //use key1 for even indexes
                if(i%2 == 0){
                    //check for upper or lower case
                    //this means lowercase
                    if(indUp == -1){
                        char newCharLow1 = shiftedAlphabetLow1.charAt(indLow);
                        encrypted.setCharAt(i,newCharLow1);
                    }
                    //this means uppercase
                    else{
                        char newCharUp1 = shiftedAlphabetUp1.charAt(indUp);
                        encrypted.setCharAt(i,newCharUp1);
                    }
                }
                //use key2 for odd index
                else{
                    //check for upper or lower case
                    //this means lowercase
                    if(indUp == -1){
                        char newCharLow2 = shiftedAlphabetLow2.charAt(indLow);
                        encrypted.setCharAt(i,newCharLow2);
                    }
                    //this means uppercase
                    else{
                        char newCharUp2 = shiftedAlphabetUp2.charAt(indUp);
                        encrypted.setCharAt(i,newCharUp2);
                    }
                }
            }
            //do nothing if not a letter, continue looping
        }

        //System.out.println("Inside encryptTwoKeys"+"\tKey 1: " +key1+ "\tKey2: " + key2);
        return encrypted.toString();
    }
    
    public String decrypt(String encrypted){
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1,26-mainKey2);

        return cc.encrypt(encrypted);
    }
}
