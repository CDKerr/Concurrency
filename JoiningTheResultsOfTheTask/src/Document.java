import java.util.Random;


public class Document {
	
	private String words[] = {"If", "the", "task", "has", "to", "resolve", "a", "problem", "bigger", "than", "a", "predefined", "size", 
			"you", "divide", "the", "problem", "in"};
	
	public String[][] generateDocument(int numLines, int numWords, String word) {
		int counter = 0;
		String document[][] = new String [numLines][numWords];
		Random random = new Random();
		
		for (int i = 0; i < numLines; i++) {
			for (int j = 0; j < numWords; j++) {
				int index = random.nextInt(words.length);
				document[i][j] = words[index];
				if (document[i][j].equals(word)) {
					counter++;
				}
			}
		}
		System.out.println("DocumentMock: The word appears " + counter + " times in the document");
		return document;
	}



}
