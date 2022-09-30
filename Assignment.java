import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LongestCompoundWord {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		File file = new File("ex.txt");

		 
		Trie trie = new Trie();
		LinkedList<Pair<String>> queue = new LinkedList<Pair<String>>();
		HashSet<String> compoundWords = new HashSet<String>();
		@SuppressWarnings("resource")
		Scanner s = new Scanner(file);

		String word;				
		List<Integer> suffix;	
		
		
		while (s.hasNext()) {
			word = s.next();		
			suffix = trie.getSuffixesStartIndices(word);
		
			for (int i : suffix) {
				if (i >= word.length())		
					break;					
											
				queue.add(new Pair<String>(word, word.substring(i)));
			}
	
			trie.insert(word);
		}
		
		Pair<String> p;				 
		int maxLength = 0;			  		 		
		String longest = "";		
		String sec_longest = "";	 

		while (!queue.isEmpty()) {
			p = queue.removeFirst();
			word = p.second();
			
			suffix = trie.getSuffixesStartIndices(word);
			
			
			if (suffix.isEmpty()) {
				continue;
			}			
			for (int i : suffix) {
				if (i > word.length()) { // sanity check 
					break;
				}
				
				if (i == word.length()) { 
					if (p.first().length() > maxLength) {
						
						sec_longest = longest;
						maxLength = p.first().length();
						longest = p.first();
					}
			
					compoundWords.add(p.first());	
					
				} else {
					queue.add(new Pair<String>(p.first(), word.substring(i)));
				}
			}
		}
		
		System.out.println("Longest Compound Word: " + longest);
		System.out.println("Second Longest Compound Word: " + sec_longest);
	}
}