import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder messageSb = new StringBuilder(message);
        StringBuilder sb = new StringBuilder();
        for(int k = whichSlice; k<message.length();k = k+totalSlices){
            //get the character at the index
            char currChar = messageSb.charAt(k);
            //add to the sb
            sb.append(currChar);
        }
        
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //make a new caesar cracker class,
        CaesarCracker cc = new CaesarCracker(mostCommon);
        //iterate through the message
        for(int k=0;k<klength;k++){
            //find a string for this key
            String currSlice = sliceString(encrypted,k,klength);
            //find the key for that slice
            int currKey = cc.getKey(currSlice);
            key[k] = currKey;
        }

        return key;
    }
    
    public HashSet<String> readDictionary (FileResource fr){
        HashSet<String> dict = new HashSet<String>();
        for (String word : fr.lines()){
            dict.add(word.toLowerCase());
        }
        
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        //first, split the message into words
        for (String word : message.split("\\W")){
            String wordLow = word.toLowerCase();
            if(dictionary.contains(wordLow)){
                count+=1; 
            }
        }
        
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        //try all key lengths from 1 to 100
        char mostCommon = 'e';
        int max = 0;
        String decryption = "";
        int[] theKey = {};
        for(int klength=1;klength<100;klength++){
            int[] currTry = tryKeyLength(encrypted,klength,mostCommon);
            //decrypt the message using VigenereCipher's decrypt
            VigenereCipher vc = new VigenereCipher(currTry);
            String currDecrypt = vc.decrypt(encrypted);
            //count how many of the words in the decrypted message are real words using counWords
            int currCount = countWords(currDecrypt,dictionary);
            if(max==0){max = currCount; decryption = currDecrypt;theKey=currTry;}
            else{
                if(currCount > max){
                    max = currCount;
                    decryption = currDecrypt;
                    theKey = currTry;
                }
            }
        }
        //find which decryption gives the largest count  of real words and return it
        System.out.println("This file contains " + max+" valid words");
        System.out.println("The key of length "+ theKey.length+" : ");
        for (int i=0;i<theKey.length;i++){
            System.out.print("\t"+theKey[i]);
        }
        System.out.println();
        return decryption;
    }
    
    public void breakVigenere () {
        //the encrypted message which comes from a file
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        //read in the dictionary
        FileResource dict = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dict);
        
        //getting num of valid words with some key length
        int wrongKeyLength = 38;
        int[] wrongKey = tryKeyLength(encrypted, wrongKeyLength, 'e');
        VigenereCipher wrong = new VigenereCipher(wrongKey);
        int numWords = countWords(wrong.decrypt(encrypted),dictionary);
        
        
        System.out.println("---------------");
        //String decrypted = breakForLanguage(encrypted,dictionary);
        //System.out.println("The decryption: ");
        System.out.println("The number of valid words with key length "+
                            wrongKeyLength+" is: "+numWords);
        //System.out.println(decrypted.substring(0,200));
        System.out.println("---------------");
    }
    
}
