import java.util.Arrays;
import java.util.Random;

/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		// Replace the following statement with your code
		String cleanStr1 = preProcess(str1);
		String cleanStr2 = preProcess(str2);

		if (cleanStr1.length() != cleanStr2.length()) {
        	return false;
    	}

		char[] arr1 = cleanStr1.toCharArray();
    	char[] arr2 = cleanStr2.toCharArray();

		Arrays.sort(arr1);
    	Arrays.sort(arr2);

   	 	return Arrays.equals(arr1, arr2);
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		// Replace the following statement with your code
		String badchars = "@?!";
		String result = "";

		String lowerCase = str.toLowerCase(); 

		for (int i = 0; i < lowerCase.length(); i++){

			char temp = lowerCase.charAt(i);

			if(badchars.indexOf(temp) == -1){
				result += temp;
			}
		}
		return result;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	

	public static String randomAnagram(String input) {
		StringBuilder remaining = new StringBuilder(input);
		StringBuilder result = new StringBuilder();
		Random rand = new Random();

		while (remaining.length() > 0) {
			// Pick a random index
			int index = rand.nextInt(remaining.length());
			// Append the character at that index to result
			result.append(remaining.charAt(index));
			// Remove the character from remaining
			remaining.deleteCharAt(index);
		}

		return result.toString();
	}	

}
