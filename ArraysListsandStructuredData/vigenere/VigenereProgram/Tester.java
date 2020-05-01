import edu.duke.*;
/**
 * Tests
 * 
 * @author Annah
 * @version May 1 2020
 */
public class Tester {
   
   public void testTryKeyLength(){
      FileResource fr = new FileResource();
      String message = fr.asString();
      VigenereBreaker vb = new VigenereBreaker();
      String keyString = "flute"; int klength = keyString.length();
      char c = 'e';
      int[] key = vb.tryKeyLength(message,klength,c);
      System.out.println("---------------");
      System.out.println("The message: ");
      System.out.println(message);
      System.out.println("The key: ");
      for (int i=0; i<key.length; i++){
          System.out.print("\t"+key[i]);
      }
      System.out.println();
      System.out.println("---------------");
   } 
   
   public void testSliceString(){
       String message = "";
       VigenereBreaker vb = new VigenereBreaker();
       System.out.println("---------------");
       System.out.println("0 and 3: "+vb.sliceString("abcdefghijklm", 0, 3));
       System.out.println("1 and 3: "+vb.sliceString("abcdefghijklm", 1, 3));
       System.out.println("2 and 3: "+vb.sliceString("abcdefghijklm", 2, 3));
       System.out.println("0 and 4: "+vb.sliceString("abcdefghijklm", 0, 4));
       System.out.println("1 and 4: "+vb.sliceString("abcdefghijklm", 1, 4));
       System.out.println("2 and 4: "+vb.sliceString("abcdefghijklm", 2, 4));
       System.out.println("3 and 4: "+vb.sliceString("abcdefghijklm", 3, 4));
       System.out.println("0 and 5: "+vb.sliceString("abcdefghijklm", 0, 5));
       System.out.println("1 and 5: "+vb.sliceString("abcdefghijklm", 1, 5));
       System.out.println("2 and 5: "+vb.sliceString("abcdefghijklm", 2, 5));
       System.out.println("3 and 5: "+vb.sliceString("abcdefghijklm", 3, 5));
       System.out.println("4 and 5: "+vb.sliceString("abcdefghijklm", 4, 5));
       System.out.println("---------------");
   }

   
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
      System.out.println("The keys were: ");
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
