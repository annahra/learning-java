
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
   public String mystery(String dna) {
  int pos = dna.indexOf("T");
  int count = 0;
  int startPos = 0;
  String newDna = "";
  if (pos == -1) {
    return dna;
  }
  while (count < 3) {
    count += 1;
    newDna = newDna + dna.substring(startPos,pos);
    startPos = pos+1;
    pos = dna.indexOf("T", startPos);
    if (pos == -1) {
      break;
    }
  }
  newDna = newDna + dna.substring(startPos);
  return newDna;
}

    void testStringmystery(){
        String str1 = "ATTGTFUCKTHIS";
        String str2 = "SKJOETEWOHTEWOITWE";
        
        System.out.println("This is the string: " + str1);
        System.out.println("After mystery function");
        System.out.println(mystery(str1));
        System.out.println("");
        
        System.out.println("This is the string: " + str2);
        System.out.println("After mystery function");
        System.out.println(mystery(str2));
        System.out.println("");
    }
}
