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
    
    private ArrayList<String> wordsInNumFiles(int number){
        //returns an arralylist of words that appear in exactly number of files
        //initialize the array list
        ArrayList<String> al = new ArrayList<String>();
        //iterate through the map
        
        
        return null;
    }
    
    private int maxNumber(){
        //finds max number of time a word appears in a group of files
        //initialize the max number
        int max = 0;
        //iterate through the map
        for(String s : map.keySet()){
            //for each word, grab the number of files that word shows up in
            int currNum = map.get(s).size();
            //if this is the first time the loop is running, initialize max to first value
            if(max == 0){max = currNum;}
            //for any other case
            else{
                //check if currNum is larger than max
                if(currNum > max){
                    //if it is, set that num as the max
                    max = currNum;
                }
                //if it isnt dont do anyting
            }
        }
        
        
        return max;
    }
    
    private void buildWordFileMap(){
        //clears map, then uses a directory resorce to get a group of files, then puts all the words
        //in the file in the map by calling addWordsFromFile
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        //grab all the files from the directory resource
        for(File f : dr.selectedFiles()){
            //for each file, add the words in the word map
            addWordsFromFile(f);
        }
    
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
