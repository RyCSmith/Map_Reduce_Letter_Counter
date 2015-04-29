package myMapReduce;

import java.util.List;

import mapReduce.Reducer;

/**
 * Reduces a list of data pertaining to a key to a calculated value.
 * @author Ryan Smith
 * @version April 2015
 *
 */
public class MyReducer extends Reducer {
	/**
	 * Counts totals for each type of character occurrence (first, last, total). 
	 * Creates a Pair<String, String> for each type and emits.
	 * char - indicates total count
	 * char^ - indicates count for char at beginning of word
	 * char$ - indicates count for char at end of word
	 */
    @Override
    public void reduce(String word, List<String> counts) {
    	int first = 0;
    	int last = 0;
    	int total = 0;
    	for (String value : counts){
    		switch (value){
    		case ("4"):
    			first++;
    			last++;
    			total++;
    			break;
    		case ("3"):
    			last++;
    			total++;
    			break;
    		case ("2"):
    			first++;
    			total++;
    			break;
    		case ("1"):
    			total++;
    			break;
    		}
    	}
        emit(word, Integer.toString(total));
        emit(word + "^", Integer.toString(first));
        emit(word + "$", Integer.toString(last));
    }
}
