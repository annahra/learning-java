
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // create a file resource and iterate over all the linse in the file
         //for each line, create a log entry and tore it in teh records array list
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
            LogEntry currEntry = WebLogParser.parseEntry(line);
            records.add(currEntry);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
