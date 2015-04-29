package myMapReduce;

import mapReduce.Mapper;

/**
 * Uses map() to break a document into words and process characters based on position in words.
 * @author Ryan Smith
 * @version April 2015
 *
 */
public class MyMapper extends Mapper {
    
	/**
	 * Splits a document String into words as specified by the assignment.
	 * Evaluates each character in each word and assigns it a value based on its position in the word.
	 * 0 - character not encountered, used to ensure all characters are present
	 * 1 - character in middle of word
	 * 2 - character in front of word
	 * 3 - character at rear of word
	 * 4 - character at front and rear of word
	 * 
	 */
    @Override
    public void map(String documentID, String document) {
    	//remove 's and split on any non-character using regex
    	document = document.replace("'", "");
        String[] words = document.split("[^a-zA-Z']+");
        
        //emits a "0" for each letter [a-z]. This ensures all characters are present in the count even if 
        //they do not appear in the document.
        for (int i = 97; i < 123; i++){
        	emit(Character.toString((char)i), "0");
        }
        
        for (String word : words) {
        	char[] individualChars = word.toCharArray();
        	for (int i = 0; i < individualChars.length; i++){
        		if (i == 0 && individualChars.length == 1){ //character is both first and last character in word
        			emit(Character.toString(individualChars[i]), Integer.toString(4));
        		}
        		else if (i == 0){ //character is first character in word
        			emit(Character.toString(individualChars[i]), Integer.toString(2));
        		}
        		else if (i == individualChars.length - 1){ //character is last character in word
        			emit(Character.toString(individualChars[i]), Integer.toString(3));
        		}
        		else { //character is neither first nor last character
        			emit(Character.toString(individualChars[i]), Integer.toString(1));
        		}
        	}
        }
    }
}