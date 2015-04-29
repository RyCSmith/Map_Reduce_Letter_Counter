package myMapReduce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;

import mapReduce.MapReduce;
import mapReduce.Pair;

/**
 * Uses MapReduce to count the number of each letter contained in a document and how many times it occurs in as the
 * first or last letter or a word. Uses a PairComparator to sort results and prints as a formatted table.
 * @author Ryan Smith
 * @version April 2015
 */
public class LetterCounter {

	public static void main(String[] args) {
		//get file from user using JFileChooser
		String[] fileReturn = getFile();
		//create MapReduce object and execute with data from file
		MapReduce mapReduce = new MapReduce();
		List<Pair<String, String>> finalResults = mapReduce.execute(fileReturn[0], fileReturn[1].toString());
		//sort results
		Collections.sort(finalResults, new PairComparator());
		//print in a formatted table
		System.out.printf("%166s\n", "A     B     C     D     E     F     G     H     I     J     K     L     M     " + ""
				+ "N     O     P     Q     R     S     T     U     V     W     X     Y     Z");
		System.out.printf("%-10s", "Initial:");
		for (int i = 0; i < 78; i +=3){
			System.out.printf("%6s", finalResults.get(i).value);
		}
		System.out.printf("\n%-10s", "Last:");
		for (int j = 1; j < 78; j += 3){
			System.out.printf("%6s", finalResults.get(j).value);
		}
		System.out.printf("\n%-10s", "Total:");
		for (int z = 2; z < 78; z += 3){
			System.out.printf("%6s", finalResults.get(z).value);
		}
	}
	
	/**
	 * Launches JFileChooser, creates BufferedReader and reads entire file selected by user into a StringBuilder.
	 * @return results - an array containing the name of the file selected by the user and a String containing its entire contents.
	 */
	private static String[] getFile(){
		String fileName = new String();
		StringBuilder entireFile = new StringBuilder();
		String[] results = new String[2];
		try{
			BufferedReader reader;
	        JFileChooser chooser = new JFileChooser();
	        chooser.setDialogTitle("Load which file?");
	        int result = chooser.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            File file = chooser.getSelectedFile();
	            if (file != null) {
	                fileName = file.getCanonicalPath();
	                reader = new BufferedReader(new FileReader(fileName));
	                String currentLine = null;
	                while((currentLine = reader.readLine()) != null){
	                	entireFile.append(currentLine.toLowerCase() + " ");
	                }	
	            }
	        }
	        results[0] = fileName;
	        results[1] = entireFile.toString();
		} 
    	catch (IOException e) {
    		System.out.println("There was an error reading the file.");
		}
		return results;
	}
}
