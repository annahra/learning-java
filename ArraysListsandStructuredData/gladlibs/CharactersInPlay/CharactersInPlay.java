import edu.duke.*;
import java.util.ArrayList;
/**
 * Finds characters in a play
 * 
 * @author Annah 
 * @version April 28,2020
 */
public class CharactersInPlay {
    private ArrayList<String> charNames;
    private ArrayList<Integer> charCounts;
    
    public CharactersInPlay(){
        charNames = new ArrayList<String>();
        charCounts = new ArrayList<Integer>();
    }
    
    public void tester(){
        findAllCharacters();
        int thresh = 10;
        System.out.println();
        for(int k=0; k<charNames.size();k++){
            int numCount = charCounts.get(k);
            if (numCount > thresh){
                System.out.println(charNames.get(k)+"\t"+charCounts.get(k));
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2){
        
    }
    
    private void findAllCharacters(){
        charNames.clear(); charCounts.clear();
        //create a new fileresource
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            int perIdx = line.indexOf(".");
            //if there is a period
            if (perIdx != -1){
                //get string before period
                String person = line.substring(0,perIdx);
                //update the charNames arraylist
                update(person);
            }
        }
    }
    
    private void update(String person){
        //check if the current person is in the arraylist already
        int index = charNames.indexOf(person);
        //if they arent
        if(index == -1){
            //add them to list
            charNames.add(person);
            charCounts.add(1);
        }
        //if they are
        else{
            //update their counts
            int count = charCounts.get(index);
            charCounts.set(index,count+1);
        }
    }
}
