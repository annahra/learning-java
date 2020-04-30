import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Determines which words occur in the greatest number of files, and for each word, determines which
 * files they occur in
 * 
 * @author Annah 
 * @version April 29,2020
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    
    public WordsInFiles(){
        map = new HashMap<String, ArrayList<String>>();
    }
    
    private void buildWordFileMap(){
        
    
    }
    
    private void addWordsFromFile(File f){
        //adds all words from file f into the map
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        //loop through the words of the file resource
        for(String word : fr.words()){
            //need to check if this word is already a key in the hashmap
            boolean isItInMap = map.containsKey(word);
            //if it is in, add the file name to the value array list
            if(isItInMap){
                //check if the filename is in the array list
                //get the array list of the word
                ArrayList<String> al = map.get(word);
                boolean isItInList = al.contains(fileName);
                //if its not in the array list, add it to the array list
                if(!isItInList){
                    al.add(fileName);
                }
                //if its already in the list, no need to do anything
            }
            //if the word is not a key, add it as a key
            else{
                //more specifically, put a key and value in there
                ArrayList<String> newAl = new ArrayList<String>();
                newAl.add(fileName);
                map.put(word, newAl);
            }
        }
    }
}
