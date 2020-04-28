import edu.duke.*;
/**
 * Testing Caesar CipherTwo implemented with Object Oriented Programming concepts
 * 
 * Annah
 * April 28, 2020
 */
public class TestCaesarCipherTwo {
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        //CaesarCipherTwo cc2 = new CaesarCipherTwo(14,24);
        //String encrypted = cc2.encrypt(message);
        
        System.out.println();
        System.out.println("The message: ");
        System.out.println(message);
        //System.out.println("Encrypted: ");
        //System.out.println(encrypted);
        System.out.println("Decrypted: ");
        //System.out.println(cc2.decrypt(message));
        System.out.println(breakCaesarCipher(message));
    }
    
    public String breakCaesarCipher(String encrypted){

        String firstHalf = halfOfString(encrypted,0);
        String secondHalf = halfOfString(encrypted,1);
        
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        System.out.println("Inside decryptTwoKeys"+"\tKey 1: " +key1+ "\tKey2: " + key2);
        
        CaesarCipherTwo cc2 = new CaesarCipherTwo(key1,key2);
        String decrypted = cc2.decrypt(encrypted);
    
        return decrypted;
    }
    
    public int getKey(String s){
        //calls countletters to get array of letter freuencies in string s and then use max index
        //to calc the index of the largest letter frequency, which is the location of 'e'
        int[] freqs = countLetters(s);
        int maxInd = indexOfMax(freqs);
        //find the key of this message, assumes letter that appeared the most in the message is e
        int key = maxInd - 4;
        //if the max index is less than 4, wrap around the alphabet
        if(maxInd<4){
            key = 26 - (4-maxInd);
        }
        
        return key;
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
