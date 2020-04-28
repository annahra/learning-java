import edu.duke.*;
/**
 * Caesar Cipher implemented with Object Oriented Programming concepts
 * 
 * Annah
 * April 28, 2020
 */
public class CaesarCipher {
    private String alphabetUp;
    private String alphabetLow;
    
    private String shiftedAlphabetUp;
    private String shiftedAlphabetLow;
    
    private int mainKey;
    
    public CaesarCipher(int key){
        alphabetUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetLow = "abcdefghijklmnopqrstuvwxyz";
        
        shiftedAlphabetUp = alphabetUp.substring(key)+alphabetUp.substring(0,key);
        shiftedAlphabetLow = alphabetLow.substring(key)+alphabetLow.substring(0,key);
        
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idxUp = alphabetUp.indexOf(currChar);
            int idxLow = alphabetLow.indexOf(currChar);
            //If currChar is in the alphabet
            if(idxUp != -1 || idxLow != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                if (idxUp == -1){
                    //this means the letter is lower case
                    char newCharLow = shiftedAlphabetLow.charAt(idxLow);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newCharLow);
                }
                else{
                    //this means the letter is uppercase
                    char newCharHigh = shiftedAlphabetUp.charAt(idxUp);
                    encrypted.setCharAt(i,newCharHigh);
                }  
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    } 
    
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        
        return cc.encrypt(encrypted);
    }
    
    
}
