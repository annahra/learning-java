
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    void tester(){
        //create file resource to select file
        FileResource fr = new FileResource();
        //create csv parser to parse through the csv file
        CSVParser parser = fr.getCSVParser();
        
        //test countryInfo
        System.out.println(countryInfo(parser, "Nauru"));
        //System.out.print("");
        
        //test listExportersTwoProducts
        //listExportersTwoProducts(parser, "gold", "diamonds");
        //System.out.print("");
        
        //test numberOfExporters
        //System.out.println(numberOfExporters(parser, "gold"));
        //System.out.print("");
        
        //test bigExporters
        //bigExporters(parser, "$999,999,999,999");
        //System.out.print("");
    }
    
    String countryInfo(CSVParser parser, String country){
        String info = "";
        //parse through parser
        for(CSVRecord record: parser){
            /* Why this doesn't work? theory: looks like records.get returns a list, but its a string?? iuno man
             * okay, i think it doesn't work because "==" operator is for numbers, not strings
            if (record.get("Country") == country){
                System.out.println("I'm in the if statement");
                String export = record.get("Export");
                String amount = record.get("Value (dollars)");
                if (export == ""){export = "NOT FOUND";}
                if (amount == ""){amount = "NOT FOUND";}
                
                info = info + ": " + export + ": " + amount;
                
            }
            else{System.out.println(record.get("Country") + " is not " + country);}*/
            
            if(record.get("Country").equals(country)){
                String export = record.get("Exports");
                String amount = record.get("Value (dollars)");
                
                
                info = country + ": " + export + ": " + amount;
                break;
            }
            else{info = "NO DATA";}
        }
        //string format ex - "Germany: motor vehicles, machinery, chemicals: $1,547,000,000,000"
        //return a string of information about the country
        //returns not found if there is no information about the country
        return info;
    }
    
    void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        //iterate through parser
        for(CSVRecord record : parser){
            String country = record.get("Country");
            String export = record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2)){
                System.out.println(country);
            }
        }
    }
    
    int numberOfExporters(CSVParser parser, String exportItem){
        //create a counter
        int count = 0;
        
        //iterate through parser
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem)){ count = count + 1;}
        }
        
        return count;
    }
    
    void bigExporters(CSVParser parser, String amount){
        //doesn't really compare integers, just the size of strings
        //depending on how big the threshold string is, the accuracy of this function
        //is the largest tens place of the input string
        
        //length of threshold
        int thresh = amount.length();
        //iterate through parser
        for(CSVRecord record : parser){
            int currAmount = record.get("Value (dollars)").length();
            if(currAmount > thresh){
                String country = record.get("Country");
                String currAmStr = record.get("Value (dollars)");
                System.out.println(country + " " + currAmStr);
            }
        }
    
    }

}
