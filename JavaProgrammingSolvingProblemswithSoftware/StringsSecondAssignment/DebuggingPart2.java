
/**
 * Write a description of DebuggingPart2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DebuggingPart2 {
    public void findAbc(String input){
       int index = input.indexOf("abc");
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           System.out.println("");
           System.out.println("index "+index);
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           index = input.indexOf("abc",index+3);
           System.out.println("index after updating"+index);
       }
   }

   public void test(){
       //findAbc("abcd");
       //                 10        20        30
       //       01234567890123456789012345678901234567890
       //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
       
       findAbc("abcabcabcabca");
   }
}