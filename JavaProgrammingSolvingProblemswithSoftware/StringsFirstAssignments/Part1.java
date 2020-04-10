
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    String findSimpleGene(String dna){
        //find the start codon, ATG
        int startIndex = dna.indexOf("ATG");
        //if no start codon, there is no gene in this dna
        if (startIndex == -1){
            return "";
        }
        //find the stop codon, TAA
        int stopIndex = dna.indexOf("TAA", startIndex+3);
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
        String dna1 = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("Dna is "+dna1);
        String gene1 = findSimpleGene(dna1);
        System.out.println("Gene is "+gene1);
        System.out.println("");
        
        //String dna2 = "ATG";
        //System.out.println("Dna is "+dna2);
        //String gene2 = findSimpleGene(dna2);
        //System.out.println("Gene is "+gene2);
        //System.out.println("");
        
        //String dna3 = "GTAAAAGCTG";
        //System.out.println("Dna is "+dna3);
        //String gene3 = findSimpleGene(dna3);
        //System.out.println("Gene is "+gene3);
        //System.out.println("");
        
        //String dna4 = "TAAATGGTACAGTAATGG";
        //System.out.println("Dna is "+dna4);
        //String gene4 = findSimpleGene(dna4);
        //System.out.println("Gene is "+gene4);
        //System.out.println("");
        
        //String dna5 = "TAAATGGTCAGTAATGG";
        //System.out.println("Dna is "+dna5);
        //String gene5 = findSimpleGene(dna5);
        //System.out.println("Gene is "+gene5);
        //System.out.println("");
        
    }
}
