import edu.duke.*;
import java.util.*;
/**
 * Counts number of times each codon occurs in a strand of dna based on reading frames
 * 
 * @author Annah
 * @version April 29,2020
 */
public class CodonCount {
    private HashMap<String,Integer> map;
    
    public CodonCount(){
        map = new HashMap<String,Integer>();
    }
    
    public void printCodonCounts(int start, int end){
        //prints all codons in the hashmap whose counts are between start and end inclusive
        for (String s : map.keySet()){
            
        }
    }
    
    public String getMostCommonCodon(){
        //returns codon that has the largest count
        //intialize max
        int max = 0;
        //initialize codon
        String codon = "";
        //loop through the map
        for (String s : map.keySet()){
            //initialize first case
            if(max==0){codon = s; max = map.get(s);}
            //every case after the first one
            else{
                //get the value of the current key
                int currCount = map.get(s);
                //check if its greater than the max
                if(currCount > max){
                    //if it is, replace the current codon, and current max
                    codon = s;
                    max = currCount;
                }
                //if its not, do nothing
            }
            
        }
        
        return codon;
    }
    
    private void buildCodonMap(int start, String dna){
        //builds map of codons in a string, maps codon to number of times they occur in the string of dna
        //will be building it multiple times, so need to clear map.
        map.clear();
        //loop through string, start at start index
        for(int k=start; k<dna.length();k++){
            //a codon has a length of 3, find end index and check if its within the length of the dna strand
            int endIdx = start+2;
            //if the end index is less than or equal to the length of the array, do things
            if (endIdx <= dna.length()){
                //if it is, go ahead and find the codon and add it to the map
                String codon = dna.substring(k,endIdx);
                //check if its already in the map
                if (!map.containsKey(codon)){
                    //if its not there, put it in there
                    map.put(codon,1);
                }
                //if its already there, increment the count
                else{
                    map.put(codon,map.get(codon)+1);
                }
            }
            //if not, do nothing
        }
    }
}
