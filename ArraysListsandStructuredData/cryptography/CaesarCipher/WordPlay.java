import edu.duke.*;
import static java.lang.System.out;
/**
 * Doing things with vowels
 * 
 * @author Annah
 * @version April 27, 2020
 */
public class WordPlay {
    public boolean isVowel(char ch){
        // this method returns true if ch is a vowel and false otherwise
        //initialize boolean
        boolean ans = false;
        //first, standardize ch to be lowercase so we can compare
        char stanChar = Character.toLowerCase(ch);
        //define vowels
        String vowels = "aeiou";
        //get index of so we can perform a check
        int idx = vowels.indexOf(stanChar);
        //check to see if index was not -1, if so return true
        if (idx != -1){ans=true;}

        return ans;
    }
    
    public void testIsVowel(){
        char ch1 = 'c';
        char ch2 = 'A';
        char ch3 = 'i';
        char ch4 = '!';
        
        out.println(ch1 + " is a vowel: " + isVowel(ch1));
        out.println(ch2 + " is a vowel: " + isVowel(ch2));
        out.println(ch3 + " is a vowel: " + isVowel(ch3));
        out.println(ch4 + " is a vowel: " + isVowel(ch4));
        
    }
    
    
    
}
