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
        char mostCommon = mostCommonCharIn(dictionary);
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
        //System.out.println("This file contains " + max+" valid words");
        //System.out.println("The key of length "+ theKey.length+" : ");
        for (int i=0;i<theKey.length;i++){
            if(theKey.length<10){
                System.out.println();
                System.out.println("The key is: " + "\t"+theKey[i]);
                
            }
        }
        System.out.println();
        return decryption;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        
        //initialize a hash map with key String and value integer
        HashMap<Character,Integer> count = new HashMap<Character,Integer>();
        //iterate through the dictionary
        for(String word : dictionary){
            //make a string builder of the word so we can grab each character
            StringBuilder sb = new StringBuilder(word);
            //iterate through each character
            for(int i=0;i<word.length();i++){
                //grab the character at this index and turn to string
                char currChar = sb.charAt(i);
                //another string builder to turn character into a string
                if(!count.containsKey(currChar)){
                    //if current letter isnt in the counting hashmap, add it add 1
                    count.put(currChar,1);
                }
                else{
                    //if current letter is in the hashmap, increment the count
                    count.put(currChar,count.get(currChar)+1);
                }
                
            }
        }
        //at this point, the hash map has been built. time to compare all of the values to themax
        int max = 0;
        Character ans = 'a';
        for(Character letter : count.keySet()){
            //grab curr number of occurence
            int currNum = count.get(letter);
            if(max==0){max=currNum; ans = letter;}
            else{
                if (currNum > max){
                    max = currNum;
                    ans = letter;
                }
            }
        }
        //how we have the letter that appears the most, but it is a string
        return ans;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        //String english = "English"; String danish = "Danish"; String dutch = "Dutch";
        //String french = "French"; String german = "German"; String italian = "Italian";
        //String portuguese = "Portuguese"; String spanish = "Spanish";
        //String breakForLanguage(String encrypted, HashSet<String> dictionary)
        //breakForLanguage(String encrypted, HashSet<String> dictionary)
        //int countWords(String message, HashSet<String> dictionary)
        int max = 0;
        String decrypted = "";
        String rightLang = "";
        //iterate through the hashmap
        for(String language : languages.keySet()){
            HashSet<String> currDict = languages.get(language);
            System.out.println(language+":");
            String currDecrypt = breakForLanguage(encrypted, currDict);
            int currMax = countWords(currDecrypt, currDict);
            System.out.println(currMax+" matched words in "+language);
            if(max==0){max = currMax; decrypted = currDecrypt;rightLang=language;}
            else{
                if(currMax>max){max = currMax; decrypted = currDecrypt;rightLang=language;}
            }
        }
        
        System.out.println("The language of the message: "+rightLang);
        System.out.println("The number of matched words in the message in this lang: " + max);
        System.out.println("The decrypted message: ");
        System.out.println(decrypted);
    }
    
    public void breakVigenere () {
        //for unknown language
        //make hashpmap of language to hashset of dictionaries in that language
        HashMap<String,HashSet<String>> langDict = new HashMap<String,HashSet<String>>();
        //start adding the languages in
        String[] languages = {"Danish","Dutch","English","French","German",
                              "Italian", "Portuguese", "Spanish"};
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println("---------------");
        for (String lang : languages){
            FileResource currDict = new FileResource("dictionaries/"+lang);
            langDict.put(lang,readDictionary(currDict));
            System.out.println("Successfully read "+lang+" dictionary");
        }
        
        breakForAllLangs(encrypted,langDict);
        
        
        System.out.println("---------------");
        //for unknown key length
        //the encrypted message which comes from a file
        //FileResource fr = new FileResource();
        //String encrypted = fr.asString();
        //read in the dictionary
        //FileResource dict = new FileResource("dictionaries/English");
        //HashSet<String> dictionary = readDictionary(dict);
        
        //getting num of valid words with some key length
        //int wrongKeyLength = 38;
        //int[] wrongKey = tryKeyLength(encrypted, wrongKeyLength, 'e');
        //VigenereCipher wrong = new VigenereCipher(wrongKey);
        //int numWords = countWords(wrong.decrypt(encrypted),dictionary);
        
        
        //System.out.println("---------------");
        //String decrypted = breakForLanguage(encrypted,dictionary);
        //System.out.println("The decryption: ");
        //System.out.println("The number of valid words with key length "+
         //                   wrongKeyLength+" is: "+numWords);
        //System.out.println(decrypted.substring(0,200));
        //System.out.println("---------------");
    }
    
}
