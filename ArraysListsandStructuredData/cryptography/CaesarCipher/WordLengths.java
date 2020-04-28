import edu.duke.*;
/**
 * Figures out the most common word length of words from a file. We will need to keep track of how many words from a file
 * are of each possible length. 
 * @author Annah
 * @version April 27, 2020
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        //reads in words from the resource and count the number of words of each length
        //for all the words in the resource, storing the counts in the array counts
        //loop through the resource
        for(String word: resource.words()){
            //initialize string builder
            StringBuilder modWord = new StringBuilder(word);
            int lengthOfCurrWord = word.length();
            //check if first and last character of word are letters            
            boolean first = Character.isLetter(modWord.charAt(0));
            boolean last = Character.isLetter(modWord.charAt(lengthOfCurrWord-1));
            //if both the first and last characters are not letters,
            if(!first && !last){
                //the word at this index has a length of -2 of this index
                counts[lengthOfCurrWord-2] +=1;
                //System.out.println("The word " + word + " is " + (lengthOfCurrWord-2));
                //System.out.println("\t First: " + first + "\tLast: " + last);
            }
            //else if, if both characters are letters
            else if(first && last){
                //the word at this index has a length of -1 of this index
                counts[lengthOfCurrWord] += 1;
                //System.out.println("The word " + word + " is " + (lengthOfCurrWord));
                //System.out.println("\t First: " + first + "\tLast: " + last);
            }
            //if word length is greater or equal to last index of array, add it to last index
            else if(lengthOfCurrWord >= (counts.length-1)){
                counts[counts.length -1] += 1;
            }
            //else, catches the cases where only 1 is not a letter
            else{
                counts[lengthOfCurrWord-1] += 1;
                //System.out.println("The word " + word + " is " + (lengthOfCurrWord-1));
                //System.out.println("\t First: " + first + "\tLast: " + last);
            }
        }
        
        for(int k=0; k<counts.length; k++){
            if(counts[k] != 0){
                System.out.println(counts[k]+" words of length "+k);
            }
        }
        
        int maxInd = indexOfMax(counts);
        
        System.out.println();
        System.out.println("Index of max word length: "+maxInd);
    }
    
    public int indexOfMax(int[] values){
        //returns index position of the largest element in the array values
        int currMax = 0;
        int currIndex = 0;
        for(int k=0; k<values.length;k++){
            int currValue = values[k];
            if(currValue > currMax){currMax = currValue; currIndex = k;}
        }
        return currIndex;
    }
    
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        System.out.println();
        countWordLengths(resource,new int[31]);
    }
}
