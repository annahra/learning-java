
/**
 * These methods parse through weather data files and perform certain calculations on them.
 * 
 * @Annah
 * @2020-04-10
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    
    CSVRecord findLowest(CSVRecord currentRec, CSVRecord lowestRec, String attribute){
        CSVRecord realLowest = null;
        //turn record strings into double values
        double currVal = Double.parseDouble(currentRec.get(attribute));
        double lowestVal = Double.parseDouble(lowestRec.get(attribute));
        
        if(currVal < lowestVal){ return currentRec;}
        else{return lowestRec;}
         
    }
    
    CSVRecord coldestHourInFile(CSVParser parser){
        //finds coldest temperature in a file and returns the record
        //with the coldest temperature
        //ignore values of -9999
        
        //initialize csvrecord file with that will hold the lowest temp
        CSVRecord coldestTemp = null;
        //iterate over parser
        for(CSVRecord currentTemp : parser){
            //initialize coldestTemp to hold the first file
            if(coldestTemp == null){coldestTemp = currentTemp;}
            //if the temperature is some junk value,in this case -9999, keep coldesTemp the same
            else if(Double.parseDouble(currentTemp.get("TemperatureF")) == -9999.){
                coldestTemp = coldestTemp;
            }
            else{
                coldestTemp = findLowest(currentTemp, coldestTemp, "TemperatureF");
                
            }
        
        }
        return coldestTemp;
    }
    
    void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Temperature was " + coldest.get("TemperatureF") + 
                            " at " + coldest.get("DateUTC"));
    }
    
    String fileWithColdestTemperature(){
        //return a string that is the name of the file from the selected files
        //that has the coldest temperature
        //make a directory resource, allows you to pick multiple files
        DirectoryResource dr = new DirectoryResource();
        //intialize file string
        String fileStr = null;
        //intialize actual file
        File file = null;
        //intialize record with lowest temp
        CSVRecord lowestTempRec = null;
        //iterate over files
        for(File f : dr.selectedFiles()){
            //create a file resource for each file, lets you iterate over file
            FileResource fr = new FileResource(f);
            if(lowestTempRec==null){
                lowestTempRec = coldestHourInFile(fr.getCSVParser());
                fileStr = f.getName();
                file = f;
            }
            //use method to find instance of the coldest tempearture in a file
            else{
                CSVRecord currentTemp = coldestHourInFile(fr.getCSVParser());
                lowestTempRec = findLowest(currentTemp, lowestTempRec, "TemperatureF");
                
                if(lowestTempRec == currentTemp){fileStr = f.getName(); file = f;}
                
            }
        }
        
        System.out.println("Coldest day was in file: " + fileStr);
        System.out.println("Coldest temperature on that day was: " + lowestTempRec.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: ");
        FileResource fr = new FileResource(file);
        for(CSVRecord record : fr.getCSVParser()){
            String utc = record.get("DateUTC");
            String temp = record.get("TemperatureF");
            System.out.println(utc + ": " + temp);
        
        }
        
        return fileStr;
    }
    
    void testFileWithColdestTemperature(){
        String fileWithColdestTemp = fileWithColdestTemperature();
        
    }
    
    CSVRecord lowestHumidityInFile(CSVParser parser){
        //returns the record that has the lowest humidity
        //if there's a tie, return the first record that was found
        //intialize csvrecord
        CSVRecord lowestHum = null;
        //iterate over parser
        for(CSVRecord currentHum : parser){
            //initialize lowestHum with the first record
            if(lowestHum == null){lowestHum = currentHum;}
            //some files have "N/A" for humidity, check before turning into num
            else if (currentHum.get("Humidity").equals("N/A")){lowestHum = lowestHum;}
            else{lowestHum = findLowest(currentHum, lowestHum, "Humidity");}
        }

        return lowestHum;
    }
    
    void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + 
                           " at " + csv.get("DateUTC"));
    }
    
    CSVRecord lowestHumidityInManyFiles(){
        //create a directory resource to choose multiple files and iterate over
        DirectoryResource dr = new DirectoryResource();
        //intialize lowest humidity file
        File lowHum = null;
        //initialize record with lowest humidity
        CSVRecord lowHumRec = null;
        //iterate over directory resource
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            if(lowHumRec == null){lowHumRec = lowestHumidityInFile(fr.getCSVParser());lowHum = f;}
            else{
                CSVRecord currentRec = lowestHumidityInFile(fr.getCSVParser()); 
                lowHumRec = findLowest(currentRec, lowHumRec, "Humidity");
                if(lowHumRec == currentRec){lowHum = f;}
            }
        }
        
        return lowHumRec;
    }
    
    void testLowestHumidityInManyFiles(){
        CSVRecord cr = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + cr.get("Humidity") + 
                           " at " + cr.get("DateUTC"));
    }
    
    double averageTemperatureInFile(CSVParser parser){
        //returns average temperature in a file
        //initialze variable that will hold sum
        double sum = 0;
        //intialize count
        double count = 0;
        //iterate through parser
        for(CSVRecord record : parser){
            double currentVal = Double.parseDouble(record.get("TemperatureF"));
            sum = sum + currentVal;
            count = count + 1;
        }

        return sum/count; 
    }
    
    void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        Double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is: " + average);
    }
    
    double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        //intialize variable that will hold sum
        double sum = 0;
        //intialize count
        double count = 0;
        //iterate through parser
        for(CSVRecord record : parser){
            double currHum = Double.parseDouble(record.get("Humidity"));
            
            if(currHum >= value){
                double currTemp = Double.parseDouble(record.get("TemperatureF"));
                sum = sum + currTemp;
                count = count + 1;
            }
        }
        if(count != 0){return sum/count;}
        else{return 0;}
    }
    
    void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double avgTempHum = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (avgTempHum == 0){System.out.println("No temperatures with that humidity");}
        else{System.out.println("Average Temp with High Humidity is " + avgTempHum);}
    }
    
}
