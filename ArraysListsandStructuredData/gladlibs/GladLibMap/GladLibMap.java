import edu.duke.*;
import java.util.*;
/**
 * Implementation of GladLibMap using HashMap
 * 
 * @author Annah
 * @version April 29, 2020
 */
public class GladLibMap {
    private HashMap<String, ArrayList<String>> map;
    private ArrayList<String> usedList;
    private ArrayList<String> consList;
    private Random myRandom;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        map = new HashMap<String, ArrayList<String>>();
        myRandom = new Random();
        usedList = new ArrayList<String>();
        consList = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
    }
    
    public GladLibMap(String source){
        map = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source){
        String[] labels = {"country", "noun", "animal", "adjective", "name", "color", "timeframe", "verb","fruit"};
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            map.put(s,list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if(label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        boolean considered = consList.contains(label);
        if(!considered){consList.add(label);}
        return randomFrom(map.get(label));

    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        //check if word is in the usedlist
        boolean isItUsed = usedList.contains(sub);
        //if not, add it to the list
        if(!isItUsed){usedList.add(sub);}
        //if it is, keep looking for another word
        while(isItUsed){
            //get a new sub word
            String currSub = getSubstitute(w.substring(first+1,last));
            //check if the new word is in the usedlist
            boolean currUsed = usedList.contains(currSub);
            //if its not, set that to sub variable, add it to the list and set isitused to false
            if(!currUsed){ 
                sub = currSub; usedList.add(currSub);isItUsed=false;
                //boolean considered = consList.contains();
            }
        }
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap(){
        int count = 0;
        //iterate through mapp
        for(String type : map.keySet()){
            //grab the number of values in the key
            int currNum = map.get(type).size();
            count = count + currNum;
        }
        
        return count;
    }
    
    public int totalWordsConsidered(){
        int count = 0;
        //iterate throuhg the considered list
        
        for(String type : consList){
            int currCount = map.get(type).size();
            count = count + currCount;
        }
        
        return count;
    }
    
    public void makeStory(){
        System.out.println("\n");
        System.out.println("---------");
        String story = fromTemplate("data/madtemplate3.txt");
        printOut(story, 60);
        System.out.println();
        /*System.out.println("The following is the list of used words");
        for(int k=0; k<usedList.size();k++){
            System.out.println(usedList.get(k)+"\t");
        }*/
        System.out.println("Total words considered: " + totalWordsConsidered());
        System.out.println("---------");
        usedList.clear();
    }
}
