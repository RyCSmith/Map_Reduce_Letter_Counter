package myMapReduce;

import java.util.Comparator;

import mapReduce.Pair;

/**
 * Compares two Pair<String, String> objects by their keys. 
 * Sorted Collection results in alphabetical ordering with the three types, first, last, total, occurring in that order.
 * @author Ryan Smith
 * @version April 2015
 *
 */
public class PairComparator implements Comparator<Pair<String, String>>{
	
	/**
	 * Compares 2 objects and returns result.
	 */
	@Override
	public int compare(Pair<String, String> o1, Pair<String, String> o2) {
		char[] o1Chars = o1.key.toCharArray();
		char[] o2Chars = o2.key.toCharArray();
		if (o1Chars[0] == o2Chars[0]){
			if (o1Chars.length == o2Chars.length){
				if (o1Chars[1] > o2Chars[1]){
					return -1;
				}
				else{
					return 1;
				}
			}
			else if (o1Chars.length < o2Chars.length){
				return 1;
			}
			else{
				return -1;
			}
		}
		else{
			if (o1Chars[0] < o2Chars[0]){
				return -1;
			}
			else {
				return 1;
			}
		}
		
	}
}
