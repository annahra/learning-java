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
    
    
    public String replaceVowels(String phrase, char ch){
        //returns a string that is phrase but with all the vowels replaced by ch
        //intialize string builder so we can change it
        StringBuilder thePhrase = new StringBuilder(phrase);
        //loop through string
        for(int i=0; i < phrase.length(); i++){
            //is the current character a vowel?
            boolean wellIsIt = isVowel(thePhrase.charAt(i));
            //if it is, change that vowel to ch
            if (wellIsIt == true){
                //set it
                thePhrase.setCharAt(i,ch);
            }
            //if not, do nothing, continue the loop
        }
        
        return thePhrase.toString();
    }
    
    public void testReplaceVowels(){
        out.println(replaceVowels("Hello World!", '*'));
    
    }
    
    
    public String emphasize(String phrase, char ch){
        //return a string replaced by '*' if vowel is at an odd number location in the string
        //and '+' if it is at an even location in the string, count starting from 1
        //initialize string builder
        StringBuilder thePhrase = new StringBuilder(phrase);
        //loop through phrase
        for(int i=0; i < phrase.length(); i++){
            //check to see if index character is same as ch 
            //if it is, do these things
            if((thePhrase.charAt(i) == Character.toLowerCase(ch)) ||
                thePhrase.charAt(i) == Character.toUpperCase(ch)){
                //if i is even
                if((i+1)%2 == 0){
                    thePhrase.setCharAt(i,'+');
                }
                //else, if odd
                else{
                    thePhrase.setCharAt(i,'*');
                }
            
            }
        }
        
        
        return thePhrase.toString();
    }
    
    
    public void testEmphasize(){
        out.println(emphasize("dna ctgaaactga", 'a'));
        out.println(emphasize("Mary Bella Abracadabra", 'a'));
        
    }
}
