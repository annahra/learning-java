import edu.duke.*;
import static java.lang.System.out;
public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabetUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLow = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedAlphabetUp = alphabetUp.substring(key)+alphabetUp.substring(0,key);
        String shiftedAlphabetLow = alphabetLow.substring(key)+alphabetLow.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
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
    public void testCaesar() {
        /*int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key +"\n"+ encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted); */
        
        out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 
                    15));
        //out.println(encrypt("First Legion", 17));
    }
    
    public void testEncryptTwoKeys(){
        out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",
                                    8, 21));
    }
    
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabetUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLow = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet for key 1
        String shiftedAlphabetUp1 = alphabetUp.substring(key1)+alphabetUp.substring(0,key1);
        String shiftedAlphabetLow1 = alphabetLow.substring(key1)+alphabetLow.substring(0,key1);
        //Compute the shifted alphabet for key 2
        String shiftedAlphabetUp2 = alphabetUp.substring(key2)+alphabetUp.substring(0,key2);
        String shiftedAlphabetLow2 = alphabetLow.substring(key2)+alphabetLow.substring(0,key2);
        //loop through input
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
    
    
    
}






