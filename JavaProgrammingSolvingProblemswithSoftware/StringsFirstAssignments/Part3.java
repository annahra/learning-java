
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    Boolean twoOccurrences(String stringa, String stringb){
        int lenOfA = stringa.length();
        
        //find first instance of stringa in stringb
        int firstCase = stringb.indexOf(stringa);
        //if it doesn't exit return false
        if (firstCase == -1){
            return false;
        }
        
        //find second instance of stringa in stringb
        int secondCase = stringb.indexOf(stringa,firstCase+lenOfA);
        if (secondCase == -1){
            return false;
        }
        
        return true;
    }
    
    String lastPart(String stringa, String stringb){
        int lenOfB = stringb.length();
        
        //find starting index of stringa in stringb
        int indOfAinB = stringb.indexOf(stringa);
        
        //if stringa not in stringb return stringb
        if (indOfAinB == -1){
            return stringb;
        }
        //find all of the characters of string b starting with stringa
        String restOfB = stringb.substring(indOfAinB, lenOfB); 
        
        return restOfB;
    }
    
    void testFunction(){
        //first case
        String stringa1 = "by";
        String stringb1 = "A story by Abby Long";
        System.out.println("String A is " + stringa1);
        System.out.println("String B is " + stringb1);
        System.out.println("String A is seen twice in String B: " + twoOccurrences(stringa1, stringb1));
        System.out.println("");
        
        //second case
        String stringa2 = "atg";
        String stringb2 = "ctgtatgta";
        System.out.println("String A is " + stringa2);
        System.out.println("String B is " + stringb2);
        System.out.println("String A is seen twice in String B: " + twoOccurrences(stringa2, stringb2));
    }
    
    void testOtherFunction(){
        String stringa1 = "an";
        String stringb1 = "bananaboobs";
        System.out.println("String A is " + stringa1);
        System.out.println("String B is " + stringb1);
        System.out.println("String B starting w StringA: " + lastPart(stringa1, stringb1));
        System.out.println("");
        
        String stringa2 = "zoo";
        String stringb2 = "forest";
        System.out.println("String A is " + stringa2);
        System.out.println("String B is " + stringb2);
        System.out.println("String B starting w StringA: " + lastPart(stringa2, stringb2));
        System.out.println("");
    }

}
