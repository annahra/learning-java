import edu.duke.*;
import java.lang.Object;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    void test(){
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String youtube = "youtube.com";
        
        for (String word : ur.words()){
            String useWord = word.toLowerCase();
            
            int startIndex = useWord.indexOf(youtube);
            //if youtube.com is in the word
            if(startIndex != -1){
                int firstQuot = word.lastIndexOf("\"", startIndex);
                int lastQuot = word.indexOf("\"",startIndex+11);
                
                String youtubeLink = word.substring(firstQuot, lastQuot);
                System.out.println(youtubeLink); 
                
                
            }
        }
        
    }
}
