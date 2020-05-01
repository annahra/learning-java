import edu.duke.*;
/**
 * Tests
 * 
 * @author Annah
 * @version May 1 2020
 */
public class Tester {
   
   public void testVigenereCipher(){
      FileResource fr = new FileResource();
      String message = fr.asString(); 
      int[] key = {17, 14, 12, 4};
      VigenereCipher vc = new VigenereCipher(key);
      String encrypted = vc.encrypt(message);
      String decrypted = vc.decrypt(encrypted);
      System.out.println("---------------");
      System.out.println("The message: ");
      System.out.println(message);
      System.out.println("Encrypted: ");
      System.out.println(encrypted);
      System.out.println("Decrypted: ");
      System.out.println(decrypted);
      System.out.println("They keys were: ");
      for(int k=0; k<key.length;k++){
          System.out.print("\t"+key[k]);
      }
      System.out.println();
      System.out.println("---------------");
   }  
   
   public void testCaesarCracker(){
       FileResource fr = new FileResource();
       String message = fr.asString();
       char mostCommon = 'a'; //<- for portugese
       CaesarCracker cc = new CaesarCracker(mostCommon);
       String decrypted = cc.decrypt(message);
       int key = cc.getKey(message);
       System.out.println("---------------");
       System.out.println("The message: ");
       System.out.println(message);
       System.out.println("Decrypted with a key of "+ key+": ");
       System.out.println(decrypted);
       System.out.println("---------------");
   } 
   
   public void testCaesarCipher(){
       FileResource fr = new FileResource();
       String message = fr.asString();
       int key = 5;
       CaesarCipher cc = new CaesarCipher(key);
       String encrypted = cc.encrypt(message);
       String decrypted = cc.decrypt(encrypted);
       System.out.println("---------------");
       System.out.println("The message: ");
       System.out.println(message);
       System.out.println("Encrypted: ");
       System.out.println(encrypted);
       System.out.println("Decrypted with a key of "+ key+": ");
       System.out.println(decrypted);
       System.out.println("---------------");
   }
    
}
