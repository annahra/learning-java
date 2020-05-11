package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile("[a-zA-Z,!.']+");
		Matcher m = tokSplitter.matcher(sourceText);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
//		for(int k=0;k<tokens.size();k++) {
//			System.out.println(tokens.get(k));
//		}
		
		if(tokens.size() != 0) {
		starter = tokens.get(0);
		ListNode starter = new ListNode(tokens.get(0));
		ListNode prevWord = starter;
		for(int k=1;k<tokens.size()+1;k++) {
			if(k==tokens.size()) {
				String w = tokens.get(k-1);
				int inList = isItInList(w);
				if(inList != -1) {
					wordList.get(inList).addNextWord(tokens.get(0));
				}
				else {
					ListNode lastWord = new ListNode(w);
					lastWord.addNextWord(tokens.get(0));
					wordList.add(lastWord);
				}
			}
			else {
				String w = tokens.get(k);
				int inList = isItInList(prevWord.getWord());
				if(inList != -1) {
					wordList.get(inList).addNextWord(w);
				}
				else {
					prevWord.addNextWord(w);
					wordList.add(prevWord);
				}
				prevWord = new ListNode(w);
			}
		}
		}
	
		
	}
	
	public int isItInList(String word) {
		for(int k=0;k<wordList.size();k++) {
			String currWord = wordList.get(k).getWord();
			if(currWord.equals(word)) {return k;}
		}
		return -1;
	}
	
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		if(wordList.size()==0 || numWords == 0) {
			return "";
		}
		else {
			String currWord = starter;
			String output = "";
			output = output + currWord;
			int counter = 0;
			while(counter <= numWords) {
				ListNode currNode = findNode(currWord);
//				if(currNode != null) {
//					//System.out.println("This executes");
//					String w = currNode.getRandomNextWord(rnGenerator);
//					output = output + w;
//					currWord = w;
//					counter +=1;
//				}
				if(currNode==null) {System.out.println(counter+" "+currWord);}
				String w = currNode.getRandomNextWord(rnGenerator);
				output = output + " "+ w;
				currWord = w;
				counter +=1;
			}
			return output;
		}
	}
	
	public ListNode findNode(String word) {
		for(int k=0;k<wordList.size();k++) {
			String currWord = wordList.get(k).getWord();
			if(currWord.equals(word)) {
				//System.out.println("This executes");
				return wordList.get(k);
			}
		}
		
		return null;
	}
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		starter = "";
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
//		String textString = "hi there hi Leo";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen.toString());
		
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
//		System.out.println("---------------");
//		System.out.println(word);
//		System.out.println("         ");
//		for(int k =0;k<nextWords.size();k++) {
//			System.out.println(nextWords.get(k));
//		}
//		System.out.println("---------------");
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int randomInt = generator.nextInt(nextWords.size());
		//System.out.println(nextWords.get(randomInt));
		
	    return nextWords.get(randomInt);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


