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
    
    public void tester(){
        System.out.println("-----------");
        //build hashmap of words out of group of files
        buildWordFileMap();
        //max num of files that a word appears in
        int maxNum = maxNumber();
        //arraylist of words with maximum number of appearances
        ArrayList<String> filesMax = wordsInNumFiles(maxNum);
        int numInMax = filesMax.size();
        /*
        if(map.size()<25){
            for(String word : map.keySet()){
                ArrayList<String> arL = map.get(word);
                System.out.println("The word " + word + " appears in the following files: ");
                for(String k : arL){
                    System.out.println(k);
                }
            }
        }*/
        System.out.println();
        System.out.println("The greatest number of files a word appear in is "+
                            maxNum+" and there are " + numInMax+ " such words: ");
        for (String s : filesMax){
            System.out.println(s+" appears in the files ");
            printFilesIn(s);
        }
        System.out.println("-----------");
    }
    
    private void printFilesIn(String word){
        //prints the names of the files this word appears in, one filename per line
        //iterate through the map
        for(String s : map.keySet()){
            //check if that string is equal to the word
            //if it is, print out everything in its arraylist
            if(s.equals(word)){
                //make the arraylist for this guy
                ArrayList<String> al = map.get(s);
                //iterate through the arraylist
                for(String fileName : al){
                    System.out.println(fileName);
                }
            }
        }
    
    }
    
    private ArrayList<String> wordsInNumFiles(int number){
        //returns an arralylist of words that appear in exactly number of files
        //initialize the array list
        ArrayList<String> al = new ArrayList<String>();
        //iterate through the map
        for(String s: map.keySet()){
            //for each word/key grab the number of files that the word shows up in
            int currNum = map.get(s).size();
            //check if its equal to the number parameter
            if(currNum == number){
                //if it is, put it in the array list
                al.add(s);
            }
            //if its not, do nothing
        }
        
        return al;
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
