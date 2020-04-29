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
        charNames.clear(); charCounts.clear();
        findAllCharacters();
        int thresh = 5;
        System.out.println("-------------");
        for(int k=0; k<charNames.size();k++){
            int numCount = charCounts.get(k);
            if (numCount > thresh){
                System.out.println(charNames.get(k)+"\t"+charCounts.get(k));
            }
        }
        
        charactersWithNumParts(10,15);
        System.out.println("-------------");
    }
    
    private void charactersWithNumParts(int num1, int num2){
        /*
        for(int k=0; k<charCounts.size();k++){
            int count = charCounts.get(k);
            //if count is greater than or equal to num1 and less than or equal to num2
            if(count >= num1 && count <= num2){
                //check the indices where the number of counts are the same
                for(int i=k+1; i<charCounts.size();i++){
                    //but only if its not the same index
                    if(k != i){
                        //if its a different index, grab the count
                        int innerCount = charCounts.get(i);
                        //if this innercount is equal to the outer count,
                        if(innerCount == count){
                            //print the following statements
                            System.out.println();
                            System.out.println("Following have the same amount of lines: " + charNames.get(i) + 
                                                    " and " + charNames.get(k));
                            //System.out.println("Num of lines: " + innerCount + " and " + count);
                        }
                    }
                }
            }
        }*/
        
        
        for(int k=0; k<charCounts.size();k++){
            int number = charCounts.get(k);
            if(number >= num1 && number <= num2){
                System.out.println("Following has greater than " + num1 + 
                                    " but less than " + num2 + " counts " + "("+number+")");
                System.out.println(charNames.get(k));
            }
        }
    }
    
    private void findAllCharacters(){
        
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
