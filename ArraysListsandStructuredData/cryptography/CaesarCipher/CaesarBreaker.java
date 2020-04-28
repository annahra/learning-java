import edu.duke.*;
/**
 * Breaks Caesar Cipher
 * 
 * @author Annah 
 * @version April 27, 2020
 */
public class CaesarBreaker {
    
    public void testDecrypt(){
        CaesarCipher cc = new CaesarCipher();
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = cc.encrypt(message,15);
        
        String decrypted = decrypt(encrypted);
        System.out.println();
        System.out.println("Encrypted message: ");
        System.out.println(encrypted);
        System.out.println("Decrypted message: ");
        System.out.println(decrypted);
    }
    
    public void testHalfOfString(){
        String message = "Qbkm Zgis";
        
        System.out.println(message);
        System.out.println("Starting at index 0 " + halfOfString(message,0));
        System.out.println("Starting at index 1 " + halfOfString(message,1));
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
    
    public String decrypt(String encrypted){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        //make a new cc object
        CaesarCipher cc = new CaesarCipher();
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
        
        for(int k=0; k<letterCounts.length;k++){
            System.out.println("Letter " + alphabet.substring(k,k+1)+": " + letterCounts[k]);
        }
        
        //System.out.println("Max Index is: " + maxIndex);
        //System.out.println("Key is: " + key);
        return cc.encrypt(encrypted,26-key);
    }
    
    public String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder(message);
        StringBuilder newString = new StringBuilder();
        
        for(int k=start; k<message.length();k=k+2){
            char currChar = sb.charAt(k);
            //append to new string builder
            newString.append(currChar);
            
        }
    
        return newString.toString();
    }
}
