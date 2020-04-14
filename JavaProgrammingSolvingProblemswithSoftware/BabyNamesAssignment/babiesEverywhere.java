import edu.duke.*;
import java.lang.Object;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of babiesEverywhere here.
 * 
 * @author Annah 
 * @version 4/13/2020
 */
public class babiesEverywhere {
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord record : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(record.get(2));
            if (numBorn <= 100){
                System.out.println("Name: " + record.get(0) +
                                    " Gender: " + record.get(1) + 
                                    " Number Born: " + record.get(2));
            }
        
        }
    }
    
    
    public void totalBirths(){
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        int numofGirlNames = 0;
        int numofBoyNames = 0;
        for(CSVRecord record: parser){
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            if(record.get(1).equals("F")){totalGirls+=numBorn; numofGirlNames+=1;}
            else{totalBoys+=numBorn; numofBoyNames+=1;}
        }
        int totalNames = numofGirlNames + numofBoyNames;
        
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total unique girl names = " + numofGirlNames);
        System.out.println("total unique boy names = " + numofBoyNames);
        System.out.println("total names = " + totalNames);

    }
    
    void testGetRank(){
        
        String name = "Frank";
        String gender = "M";
        int year = 1971;
        FileResource fr = new FileResource();
        System.out.println(name+"("+gender+") of year " + year+" Rank: " +
                            getRank(year,name,gender, fr));
    }
    
    int getRank(int year, String name, String gender, FileResource fr){
        //returns the rank of that name with that gender in that year
        //initialize rank to -1 meaning that the name with that gender cant be found
        int rank = -1;
        int count = 0;
        //FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord rec : parser){
            String currName = rec.get(0);
            String currGend = rec.get(1);
            //if the current name is the same gender as the person we're looking for
            //incremement count
            if(currGend.equals(gender)){count+=1;}
            //if we found the name, set our count is our rank
            if(currName.equals(name) && currGend.equals(gender)){
                rank = count;
                
            }
        }
        return rank;
    }
    
    void testGetName(){
        int rank = 450;
        String gender = "M";
        int year = 1982;
        System.out.println("The person with rank " + rank +
                            " and gender " + gender + 
                            " in the year " + year + 
                            " is: " + getName(year,rank,gender));
    }
    
    String getName(int year, int rank, String gender){
        //gets the name of the person with that rank with that gender
        String name = "NO NAME";
        int count = 0;
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){
            String currGen = rec.get(1);
            if(currGen.equals(gender)){count+=1;}
            if(count == rank && gender.equals(currGen)){
                name = rec.get(0);
            }
        }
    
        return name;
    }
    
    void whatIsNameInYear(String name, int year, int newYear, String gender){
        //rank of name in OG name
        FileResource fr = new FileResource();
        int rank = getRank(year, name, gender, fr);
        //FileResource frNew = new FileResource();
        //get the name in the new year
        String newName = getName(newYear,rank,gender);
        
        System.out.println(name + " born in " + year + 
                            " would be " + newName + " if they were born in " + 
                            newYear);
    }
    
    void testYearOfHighestRank(){
        String name = "Mich";
        String gender = "M";
        System.out.println("The name " + name +
                            " (" + gender + ") " + 
                            "had the highest rank in year " + 
                             yearOfHighestRank(name,gender));
    }
    
    int yearOfHighestRank(String name, String gender){
        //initialize year with condition if name and gen not in file
        int yearHigh = -1;
        //initialize rank
        int rank = 0;
        //initialize directory resource
        DirectoryResource dr = new DirectoryResource();
        //parse through directory resource
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int currRank = getRank(1,name, gender, fr);
            //creating the int for year
            String fileStr = f.getName();
            String yearStr = fileStr.substring(3,7);
            int currYear = Integer.parseInt(yearStr);
            //for the first case
            if(rank == 0){ rank=currRank; yearHigh = currYear;}
            //for case where name is not in file
            else if(currRank == -1){rank = rank;}
            //for every other case
            else{
                if(currRank==rank){rank=rank;}
                else if(currRank<rank){
                    rank = currRank;
                    yearHigh = currYear;
                }
            }
        }
        
        return yearHigh;
    }
    
    void testGetAverageRank(){
        String name = "Robert";
        String gender = "M";
        double avgRank = getAverageRank(name,gender);
        System.out.println("The average rank of " + name +
                            " (" + gender + ") " + "is: " + avgRank);
    
    }
    
    double getAverageRank(String name, String gender){
        //initialize rank with 
        double rank = -1;
        //initialize count
        double count = 0;
        //initialize sum
        double sum = 0;
        //initialize directory resource
        DirectoryResource dr = new DirectoryResource();
        //parse through directory resource
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            //iterate through file
            for(CSVRecord rec : fr.getCSVParser()){
                String currName = rec.get(0);
                String currGen = rec.get(1);
                if(currName.equals(name) && currGen.equals(gender)){
                    int currRank = getRank(1,name,gender,fr);
                    //check if this name and gender is in the file
                    if(currRank == -1){rank=rank;}
                    //if so, get the rank number and add it to the sum and increment count
                    else{sum+=currRank; count+=1;}
                }
            }
        }
        
        if(sum == 0){return -1;}
        else{
            rank = sum/count;
            return rank;
        }
    }
    
    void testGetTotalBirthsRankedHigher(){
        String name = "Drew";
        String gender = "M";
        int year = 1990; 
        FileResource fr = new FileResource();
        
        System.out.println("Total births ranked higher than " +
                            name + " (" + gender + ") on year " + year +
                            ": " + getTotalBirthsRankedHigher(year,name, gender, fr));
    }
    
    int getTotalBirthsRankedHigher(int year, String name, String gender, FileResource fr){
        //intialize count of num of births
        int count = 0;
        //FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord rec : parser){
            String currName = rec.get(0);
            String currGend = rec.get(1);
            int currCount = Integer.parseInt(rec.get(2));
            //if we found the name, set our count is our rank
            if(currName.equals(name) && currGend.equals(gender)){
                break;  
            }
            //incremement count
            if(currGend.equals(gender)){count+= currCount;}
        }
        
        return count;
    }
    
}
