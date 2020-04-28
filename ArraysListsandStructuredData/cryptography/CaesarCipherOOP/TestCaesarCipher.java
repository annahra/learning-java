import edu.duke.*;
/**
 * Testing Caesar Cipher implemented with Object Oriented Programming concepts
 * 
 * Annah
 * April 28, 2020
 */
public class TestCaesarCipher {
    
    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt(message);
        
        System.out.println();
        System.out.println("The message: ");
        System.out.println(message);
        System.out.println("Encrypted: ");
        System.out.println(encrypted);
        System.out.println("Decrypted using method in this class: ");
        //System.out.println(breakCaesarCipher(encrypted));
        System.out.println(cc.decrypt(encrypted));
    }
    
    public String breakCaesarCipher(String encrypted){

        //this holds the frequency of letters appearing in the message
        int[] letterCounts = countLetters(encrypted);
        //finds the index of the letter that had appeared the most
        int maxIndex = indexOfMax(letterCounts);
        //find the key of this message, assumes letter that appeared the most in the message is e
        int key = maxIndex - 4;
        //if the max index is less than 4, wrap around the alphabet
        if(maxIndex<4){
            key = 26 - (4-maxIndex);
        }
        CaesarCipher cc = new CaesarCipher(key);
        
        return cc.decrypt(encrypted);
    }
    
    
    public int[] countLetters(String message){
        String messageNorm = message.toLowerCase();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        //initialize counting array
        int[] counts = new int[26];
        //loop through message
        int len = message.length();
        
        for(int k=0;k<len;k++){
            //current character
            char currChar = Character.toLowerCase(message.charAt(k));
            //is the character is actually a letter
            int index = alphabet.indexOf(currChar);
            if(index != -1){
                
                counts[index] += 1;
                
            }
        
        }
        
        return counts;
    }
    
    public int indexOfMax(int[] values){
        //returns index position of the largest element in the array values
        int currMax = 0;
        int currIndex = 0;
        for(int k=0; k<values.length;k++){
            int currValue = values[k];
            if(currValue > currMax){currMax = currValue; currIndex = k;}
        }
        return currIndex;
    }
}
