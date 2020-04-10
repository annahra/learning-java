import edu.duke.*;
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    int howMany(String stringa, String stringb){
        int lenOfA = stringa.length();
        //counter
        int count = 0;
        //find first instance of stringa in stringb
        int startIndex = stringb.indexOf(stringa, 0);
        //if doesn't exit return 0
        if (startIndex == -1){return 0;}
        //while we can find stringa in stringb do dis
        while(startIndex != -1){
            count = count + 1;
            startIndex = stringb.indexOf(stringa, startIndex+lenOfA);
        }
    
        return count;
    }
    
    void testHowMany(){
        String stringa1 = "GAA";
        String stringb1 = "ATGAACGAATTGAATC";
        System.out.println("String A is: " + stringa1);
        System.out.println("String B is: " + stringb1);
        System.out.println("The number of times string a appears in string b: " + 
        howMany(stringa1,stringb1));
        System.out.println("");
        
        String stringa2 = "AA";
        String stringb2 = "ATAAAA";
        System.out.println("String A is: " + stringa2);
        System.out.println("String B is: " + stringb2);
        System.out.println("The number of times string a appears in string b: " + 
        howMany(stringa2,stringb2));
        System.out.println("");
    }

}
