
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

    String findSimpleGene(String dna, String startCodon, String stopCodon){
        //find the start codon, ATG
        int startIndex = dna.indexOf(startCodon);
        //if no start codon, there is no gene in this dna
        if (startIndex == -1){
            return "";
        }
        //find the stop codon, TAA
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        //if no stop codon, there is no gene in this dna
        if (stopIndex == -1){
            return "";
        }
        
        String gene = dna.substring(startIndex, stopIndex+3);
        
        if ((gene.length()%3) != 0){
            return "";
        }
        
        return gene;
    }

    void testSimpleGene(){
        String begCodon = "ATG";
        String endCodon = "TAA";
        
        String dna1 = "TAA";
        System.out.println("Dna is "+dna1);
        String gene1 = findSimpleGene(dna1, begCodon, endCodon);
        System.out.println("Gene is "+gene1);
        System.out.println("");
        
        String dna2 = "ATG";
        System.out.println("Dna is "+dna2);
        String gene2 = findSimpleGene(dna2, begCodon, endCodon);
        System.out.println("Gene is "+gene2);
        System.out.println("");
        
        String dna3 = "GTAAAAGCTG";
        System.out.println("Dna is "+dna3);
        String gene3 = findSimpleGene(dna3, begCodon, endCodon);
        System.out.println("Gene is "+gene3);
        System.out.println("");
        
        String dna4 = "TAAATGGTACAGTAATGG";
        System.out.println("Dna is "+dna4);
        String gene4 = findSimpleGene(dna4, begCodon, endCodon);
        System.out.println("Gene is "+gene4);
        System.out.println("");
        
        String dna5 = "TAAATGGTCAGTAATGG";
        System.out.println("Dna is "+dna5);
        String gene5 = findSimpleGene(dna5, begCodon, endCodon);
        System.out.println("Gene is "+gene5);
        System.out.println("");
        
    }
}
