
/**
 * Tests log analyzer code
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testUniqueIP(){
        System.out.println("-------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIps();
        System.out.println("There are "+uniqueIPs+" unique IP addresses");
        System.out.println("-------------");
    }
    
    public void testDayWithMostIPVisits(){
       System.out.println("-------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipsdays = la.iPsForDays();
        String day = la.dayWithMostIPVisits(ipsdays);
        System.out.println("The day with the most visits is " +day);
        System.out.println("-------------"); 
    }
    
    public void testIPsForDays(){
       System.out.println("-------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipsdays = la.iPsForDays();
        for (String date : ipsdays.keySet()){
            ArrayList<String> currList = ipsdays.get(date);
            System.out.println(date + " ("+currList.size()+")");
            for(String ip : currList){
                System.out.println("\t"+ip);
            }
        }
        System.out.println("-------------"); 
    }

    public void testIPsMostVisits(){
       System.out.println("-------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, Integer> visits = la.countVisitsPerIP();
        ArrayList<String> list = la.iPsMostVisits(visits);
        System.out.println("There are "+list.size()+" unique IP addresses with max visits");
        for (String s : list){
            System.out.println(s);
        }
        System.out.println("-------------"); 
    }
    
    public void testMostNumberVisitsByIp(){
       System.out.println("-------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, Integer> visits = la.countVisitsPerIP();
        int max = la.mostNumberVisitsByIP(visits);
        System.out.println("The maximum number of times an IP has visited is "+max);
        System.out.println("-------------"); 
    }
    
    public void testCountVisitsPerIP(){
        System.out.println("-------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> visits = la.countVisitsPerIP();
        System.out.println("There are "+visits.size()+" unique IP addresses");
        for (String ip : visits.keySet()){
            System.out.println(ip+" visited  "+visits.get(ip)+" times");
        }
        System.out.println("-------------");
    }
    
    public void testCountUniqueIPsInRange(){
        System.out.println("-------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int low = 200; int high = 299;
        int num = la.countUniqueIPsInRange(low,high);
        System.out.println("There are "+num+" unique IP addresses that have status codes between "+
                            low+" and "+high);
        System.out.println("-------------");
    }
    
    public void testUniqueIPVisitsOnDay(){
        System.out.println("-------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        String date = "Mar 17";
        ArrayList<String> list = la.uniqueIPVisitsOnDay(date);
        System.out.println("There are "+list.size()+" IP address that visisted on " + date);
        for (String s : list){
            System.out.println(s);
        }
        System.out.println("-------------");
    }
    
    public void testPrintAllHigherThanNum(){
        System.out.println("-------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int num = 400;
        la.printAllHigherThanNum(num);
        System.out.println("-------------");
    }
    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        System.out.println("-------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
        System.out.println("-------------");
    }
}
