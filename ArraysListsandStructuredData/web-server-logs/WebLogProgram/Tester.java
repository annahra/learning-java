
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
    
    public void testPrintAllHigherThanNum(){
        System.out.println("-------------");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int num = 300;
        la.printAllHigherThanNum(300);
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
