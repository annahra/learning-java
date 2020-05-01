
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
     
     HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> visits = new HashMap<String,Integer>();
         //we need to parse through records
         for (LogEntry record : records){
            //get the ip address of current record
            String currIP = record.getIpAddress();
            //check if that ip address is in the hashmap
            if(!visits.containsKey(currIP)){
                //if its not, add it to the ip
                visits.put(currIP,1);
            }
            //if it is in there, increment the value
            else{
                visits.put(currIP, visits.get(currIP)+1);
            }
         }
         
         return visits;
     } 
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
            int currStatus = le.getStatusCode();
            String currIP = le.getIpAddress();
            if(currStatus >= low && currStatus <= high && !uniqueIPs.contains(currIP)){
                uniqueIPs.add(currIP);
            }
         }
         
         return uniqueIPs.size();
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         //.getAccessTime().toString().substring(4,10)
         //parse through records
         for(LogEntry le : records){
            //get the dates
            String currDate = le.getAccessTime().toString().substring(4,10);
            String currIP = le.getIpAddress();
            //check if its equal to someday
            if(currDate.equals(someday) && !uniqueIPs.contains(currIP)){
                //add ip address to the array list
                uniqueIPs.add(currIP);
            }
         }
         return uniqueIPs;
     }
     
     public void printAllHigherThanNum(int num){
        ArrayList<LogEntry> hold = new ArrayList<LogEntry>();
        for (LogEntry le : records){
            //grab the status code
            int currStatus = le.getStatusCode();
            //check to see if its greater than the status code num
            if (currStatus > num){
                //if it is, add the record to hold
                hold.add(le);
            }
        }
        System.out.println("The following "+ hold.size()+" log entries have a status code greater than " + num);
        for(LogEntry lE : hold){
            System.out.println(lE);
        }
     }
     
     public int countUniqueIps(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         //parse through the array list
         for(LogEntry record : records){
             //grab the ip address from the record
             String currIP = record.getIpAddress();
             //check to see if its in there, if its not, add it in
             if (!uniqueIPs.contains(currIP)){uniqueIPs.add(currIP);}
         }
         return uniqueIPs.size();
     }
     
}
