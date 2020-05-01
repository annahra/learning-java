import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder messageSb = new StringBuilder(message);
        StringBuilder sb = new StringBuilder();
        for(int k = whichSlice; k<message.length();k = k+totalSlices){
            //get the character at the index
            char currChar = messageSb.charAt(k);
            //add to the sb
            sb.append(currChar);
        }
        
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //make a new caesar cracker class,
        CaesarCracker cc = new CaesarCracker(mostCommon);
        //iterate through the message
        for(int k=0;k<klength;k++){
            //find a string for this key
            String currSlice = sliceString(encrypted,k,klength);
            //find the key for that slice
            int currKey = cc.getKey(currSlice);
            key[k] = currKey;
        }

        return key;
    }

    public void breakVigenere () {
        //the encrypted message which comes from a file
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        //need to know the key length
        int klength = 5;
        //need to know most common character
        char c = 'e';
        int[] key = tryKeyLength(encrypted,klength,c);
        //create new vigenere cipher
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("---------------");
        //System.out.println(encrypted);
        System.out.println(decrypted);
        System.out.println("---------------");
    }
    
}
