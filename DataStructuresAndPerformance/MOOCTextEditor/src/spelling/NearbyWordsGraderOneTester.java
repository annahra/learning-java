/**
 * 
 */
package spelling;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
/**
 * @author Annah
 *
 */

public class NearbyWordsGraderOneTester {
	int tests = 0;
    int incorrect = 0;
    String feedback = "";
    PrintWriter out;
    Dictionary d = new DictionaryHashSet();
    NearbyWords nw;
    List<String> d1;

    @Before
	public void setUp() throws Exception {
        DictionaryLoader.loadDictionary(d, "test_cases/dict.txt");
        nw = new NearbyWords(d);
        d1 = nw.distanceOne("word", true);
    }
	
    @Test
    public void testPrintWriter() {
    	try {
            out = new PrintWriter("grader_output/module5.part1.out");
        } catch (Exception e) {
            e.printStackTrace();
            //return;
        }
    }
    
    @Test
    public void test3() {
    	d1 = nw.distanceOne("word", false);
    	assertEquals("test 3: ",230, d1.size());
    }
    
    @Test
    public void test1() {
    	for(String s: d1) {
    		//System.out.println(s);
    	}
    	assertEquals("test 1: ",5,d1.size());
    }

    
    @Test
    public void test6() {
    	d1 = new ArrayList<String>();
    	nw.insertions("or", d1, true);
    	assertEquals("test 6: ", 3, d1.size());
    	for(String s: d1) {
    		//System.out.println(s);
    	}
    }
    
    @Test
    public void testEverything() {
    	Dictionary d = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(d, "test_cases/dict.txt");
    	try {
            
            NearbyWords nw = new NearbyWords(d);

            List<String> d1 = nw.distanceOne("word", true);
            
            
            feedback += "** Test 2: distanceOne words returned... ";
            for (String i : d1) {
                feedback += i + ", ";
            }
            
            d1 = new ArrayList<String>();
            
            feedback += "** Test 4: deletions list size... ";
            nw.deletions("makers", d1, true);
            feedback += "deletions returned " + d1.size() + " words.\n";

            feedback += "** Test 5: deletions words returned... ";
            feedback += "deletions returned: ";
            for (String i : d1) {
                feedback += i + ", ";
            }


            feedback += "** Test 7: insertions words returned... ";
            feedback += "insertions returned: ";
            for (String i : d1) {
                feedback += i + ", ";
            }
            feedback += "\n";
            
        } catch (Exception e) {
            out.println("Runtime error: " + e);
            return;
        }
    }
    
}