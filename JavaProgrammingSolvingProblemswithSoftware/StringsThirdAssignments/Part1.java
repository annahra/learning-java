import edu.duke.*;
import java.lang.Object;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon,startIndex);
        
        while(currIndex != -1){
            int diff = currIndex - startIndex;
            if (diff%3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex+3);
            }
        }
        return dna.length();
    }
    
    String findGene(String dna, int newStartIndex){
        //find the first instance of startIndex
        //int newStartIndex = dna.indexOf("ATG", startIndex);
        //if doesnt exist, return empty string
        if(newStartIndex==-1){return "";}
        //find first occurrence of stop codon "TAA" after first occurance of "ATG"
        int taaIndex = findStopCodon(dna, newStartIndex+3, "TAA");
        //System.out.println("        taaIndex is " + taaIndex);
        //find first occurrence of stop codon "TAG" after first occurance of "ATG"
        int tagIndex = findStopCodon(dna, newStartIndex+3, "TAG");
        //System.out.println("        tagIndex is " + tagIndex);
        //find first occurrence of stop codon "TGA" after first occurance of "ATG"
        int tgaIndex = findStopCodon(dna, newStartIndex+3, "TGA");
        //System.out.println("        tgaIndex is " + tgaIndex);
        
        //find minimum index of stop codons
        int min1 = Math.min(taaIndex, tagIndex);
        int stopCodon = Math.min(min1, tgaIndex);
        
        //if no stop codon, return empty string
        if(stopCodon == dna.length()){return "";}
        else{
                String gene = dna.substring(newStartIndex, stopCodon+3);
                return gene;
             }
    }
    
    void testFindStopCodon(){
        //             ATGv  v  v  v
        String dna1 = "ATGCGCAAGTAGGTA";
        if(findStopCodon(dna1, 0, "TAA") != 15){System.out.println("Error dna1");}
        //             ATGv  v  v  TAA
        String dna2 = "ATGCGCAAGTAGTAA";
        if(findStopCodon(dna2, 0, "TAA") != 12){System.out.println("Error dna2");}
        //             ATGv  v  v TAA
        String dna3 = "ATGCGCAAGTATAA";
        if(findStopCodon(dna3, 0, "TAA") != 14){System.out.println("Error dna3");}
        
        System.out.println("tests are finished");
    }
    
    void testFindGene(){
        //             ATGv  v  TAG  
        String dna1 = "AGGATGCGCAAGTAGGTA";
        System.out.println("DNA 1 is: " + dna1);
        System.out.println("Gene is: " + findGene(dna1,dna1.indexOf("ATG")));
        System.out.println("");
        //             ATGv  v  TAATAG  vTGA
        String dna2 = "ATGCGCAAGTAATAGGTATGA";
        System.out.println("DNA 2 is: " + dna2);
        System.out.println("Gene is: " + findGene(dna2,dna2.indexOf("ATG")));
        System.out.println("");
        
        //             ATGv  v  TAATAG  vTGA
        String dna3 = "ATGCGCAATAATAGGTATTGA";
        System.out.println("DNA 3 is: " + dna3);
        System.out.println("Gene is: " + findGene(dna3,dna3.indexOf("ATG")));
        System.out.println("");
        
        //             ATGv  v  v  v  v  v  v
        String dna4 = "ATGCGCAADDTAATAGGTATTGGA";
        System.out.println("DNA 4 is: " + dna4);
        System.out.println("Gene is: " + findGene(dna4,dna4.indexOf("ATG")));
        System.out.println("");
        
        //             v  v  v  v  v  v  v
        String dna5 = "CGCAADDTAATAGGTATTGGA";
        System.out.println("DNA 5 is: " + dna5);
        System.out.println("Gene is: " + findGene(dna5,dna5.indexOf("ATG")));
        System.out.println("");
    }
    
    void printAllGenes(String dna){
        //find the first starting index
        int startIndex = dna.indexOf("ATG");
        int count = 1;
        while(true){
            
            if (startIndex == -1){break;}
            else{
                String gene = findGene(dna, startIndex);
                System.out.println("Gene #"+count+"is " +gene);
                //System.out.println("");
                if(gene.isEmpty()){break;}
                //System.out.println("Before update"+startIndex);
                startIndex = dna.indexOf("ATG", startIndex+gene.length());
                System.out.println("After update"+startIndex);
                count = count + 1;
            }
        }
    }
    
    public StorageResource getAllGenes(String dna){
        //create empty storage resource
        StorageResource geneList = new StorageResource();
        //find the first starting index
        int startIndex = dna.indexOf("ATG");
        
        while(true){
            
            if (startIndex == -1){break;}
            else{
                String gene = findGene(dna, startIndex);
                
                if(gene.isEmpty()){break;}
                
                //add gene to list
                geneList.add(gene);
                
                startIndex = dna.indexOf("ATG", startIndex+gene.length());
                
            }
        }
        return geneList;
    }
    
    String findAValue(String fullString, String s, int startIndex){
        //int newStartIndex = fullString.indexOf(s,startIndex);
        int lenOfS = s.length();
        String foundS = fullString.substring(startIndex,startIndex+lenOfS);
        
        return foundS;
    }
    
    StorageResource getAllofValue(String fullString, String s){
        StorageResource stringList = new StorageResource();
        int startIndex = fullString.indexOf(s);
        int lenofS = s.length();
        //test
        int count = 0;
        while (true){
            if (startIndex ==-1){break;}
            else{
                String foundS = findAValue(fullString, s, startIndex);
                stringList.add(foundS);
                startIndex = fullString.indexOf(s,startIndex+lenofS);
                //test 
                count = count + 1;
            }
        }
        
        
        return stringList;
    }
    
    float cgRatio(String dna){
        String lowerDNA = dna.toLowerCase();
        //create storage resource for c's
        StorageResource allCs = getAllofValue(lowerDNA, "c");
        //for(String c : allCs.data()){
            //System.out.println("Supposed to be a C: "+c);
        //}
        //create storage resource for gangsta's
        StorageResource allGs = getAllofValue(lowerDNA, "g");
        //for(String g : allGs.data()){
            //System.out.println("Supposed to be a G: "+g);
        //}
        //get length of c storage resource
        int numOfCs = allCs.size();
        //System.out.println("The # of Cs: " + numOfCs);
        //get length of g storage resource
        int numOfGs = allGs.size();
        //System.out.println("The # of Gs: " + numOfGs);
        
        int numOfChars = dna.length();
        int cPlusg = numOfCs+numOfGs;
        
        float ratioCG = (float)cPlusg/numOfChars;
        
        return ratioCG;
    }
    
    int countCTG(String dna){
        StorageResource allDeCTGs = getAllofValue(dna, "CTG");
        int numOfCTGs = allDeCTGs.size();
        
        return numOfCTGs;
    }
    
    void processGenes(StorageResource sr){
        //count the strings that are longer than 9
        int countLong9 = 0;
        for(String s : sr.data()){
            if(s.length() > 9){
                System.out.println("This string is longer than 9 characters" + s);
                countLong9 = countLong9 + 1;
            }
        }
        //print number of strings that are longer than 9
        System.out.println("The number of strings in sr that are longer than 9: " + countLong9);
        
        //count num of strings whose cg ratio is higher than 0.35
        int countCG = 0;
        for(String s : sr.data()){
            float CGRatio = cgRatio(s);
            if(CGRatio > 0.35){
                System.out.println("The string: " + s +
                "had a CG ratio of: " + CGRatio + " which is greater than 0.35.");
                countCG = countCG + 1;
            }
        }
        //print num of strings whose cg ratio is higher than 0.35
        System.out.println("The number of strings with CG ratio higher than 0.35 is "+ countCG);
        
        //initialize string length
        String longestStr = "";
        for (String s : sr.data()){
            if(s.length() > longestStr.length()){
                longestStr = s;
            }
        }
        //print longest string
        System.out.println("This is the longest string: ");
        System.out.println(longestStr);
        
    }
    
    void testProcessGenes(){
        String dna1 = "AGGATGCGCAAGTAGGTAATGFGCAAGTAGTAAATGCGC3rdTAGATGTAAATG";
        String dna2 = "ATGTTCGCAADDTTCGCAADDTTCGCAADDTAA";
        String dna3 = "ATGTTCTTCTAA";
        
        StorageResource sr = new StorageResource();
        sr.add(dna1);
        sr.add(dna2);
        sr.add(dna3);
        
        processGenes(sr);
        
    }
    
    
    void processGenesStr(String dna){
        StorageResource listOfGenes = getAllGenes(dna);
        
        //count the strings that are longer than 9
        int countLong9 = 0;
        for(String s : listOfGenes.data()){
            if(s.length() > 60){
                System.out.println("This gene is longer than 60 characters" + s);
                countLong9 = countLong9 + 1;
            }
        }
        //print number of strings that are longer than 9
        System.out.println("The number of genes in this dna that are longer than 60: " + countLong9);
        
        //count num of strings whose cg ratio is higher than 0.35
        int countCG = 0;
        for(String s : listOfGenes.data()){
            float CGRatio = cgRatio(s);
            if(CGRatio > 0.35){
                System.out.println("The gene: " + s +
                " had a CG ratio of: " + CGRatio + " which is greater than 0.35.");
                countCG = countCG + 1;
            }
        }
        //print num of strings whose cg ratio is higher than 0.35
        System.out.println("The number of genes with CG ratio higher than 0.35 is "+ countCG);
        
        //initialize string length
        String longestStr = "";
        for (String s : listOfGenes.data()){
            if(s.length() > longestStr.length()){
                longestStr = s;
            }
        }
        //print longest string
        System.out.println("This is the longest gene: ");
        System.out.println(longestStr);
        System.out.println("The longest gene "+longestStr.length()+" characters long.");
        
        System.out.println("The number of CTGs in this DNA is " + countCTG(dna));
    }
    
    void testProcessGenesStr(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        //System.out.println(dna);
        dna = dna.toUpperCase();
        System.out.println(dna);
        processGenesStr(dna); 
    }
    
    void testCGRatio(){
        String dna1 = "ATGCCATAG";
        System.out.println("");
        System.out.println(cgRatio(dna1));
    }
    
    void testgetAllGenes(String dna){
        StorageResource allGenes = getAllGenes(dna);
        for (String g : allGenes.data()){
            System.out.println("Gene is: " + g);
        }
        System.out.println("");
    }
    
    void testprintAllGenes(){
        //                       10        20        30        40 
        //             012345678901234567890123456789012345678901234567890123 
        String dna1 = "AGGATGCGCAAGTAGGTAATGFGCAAGTAGTAAATGCGC3rdTAGATGTAAATG";
        testgetAllGenes(dna1);
    }
}
