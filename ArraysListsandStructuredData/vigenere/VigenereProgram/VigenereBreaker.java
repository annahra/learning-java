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
        //WRITE YOUR CODE HERE
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
    }
    
}
