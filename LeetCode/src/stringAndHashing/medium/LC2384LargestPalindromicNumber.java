package stringAndHashing.medium;

import java.util.HashMap;

// You are given a string num consisting of digits only.
// Return the largest palindromic integer (in the form of a string) that can be formed using digits taken from num.
// It should not contain leading zeroes.

// Input: num = "666967137"
// Output: "7669667"
// Input: num = "00009"
// Output: "9"
public class LC2384LargestPalindromicNumber {
    
    // A given string contains mix of digits from 0 to 9.
    // Create HashMap of number (digit) to freq. Then iterate map from the largest digit 9 to 0 (using for-loop from 9 to 0).
    // If digit exists in map, get its frequency.
    // If freq is even (No need to check if it's even), get numPairs with int-division by 2, and append i to firstHalf for numPairs times.
    // If freq is odd, set the number as center. Only set center once (because I set center from largest 9).
    // When iteration is done, reverse firstHalf and append as lastHalf to the result.
    // Time: O(n), Space: O(n)
    public String largestPalindromic(String num) {
        HashMap<Character, Integer> numFreq = new HashMap<>();
        
        // Create HashMap of number (digit) to freq.
        for (char n : num.toCharArray()) {
            if (!numFreq.containsKey(n)) {
                numFreq.put(n, 1);
            }
            else {
                numFreq.put(n, numFreq.get(n) + 1);
            }
        }
        
        String firstHalf = "";
        String center = "";
        
        // Iterate from the largest digit 9 to 0.
        for (int i = 9; i >= 0; i--) {
            // Convert integer i to Char
            String s = String.valueOf(i);
            Character c = s.charAt(0);
            
            // If i exists in given num, get its frequency.
            // If freq is even (or numPair > 0), get number of pairs.
            if (numFreq.containsKey(c)) {
                int freq = numFreq.get(c);  // Get freq.
                int numPairs = freq / 2;  // int division. Get numPairs.
                
                if (numPairs > 0) {  // If a pair exists.
                    if (firstHalf.length() == 0 && c == '0') {  // Edge case to avoid leading zeros
                        // numFreq.put('0', 1);  // Also the result should not be like 00000.
                    }
                    else {
                        for (int j = numPairs; j > 0; j--) {  // Fill first half with this i.
                            firstHalf += String.valueOf(c);

                        }
                    }
                }
                if (freq % 2 > 0 && center.isEmpty()) {  // If freq was odd, I can use remaining one as a center. Do this check from largest 9.
                    center = String.valueOf(c);
                }
            
            }
        }
        
        if (center.isEmpty() && firstHalf.length() == 0) {  // Edge case if given num only contains zeros.
             return "0";
        }
        
        // Reverse firstHalf and append as lastHalf.
        String lastHalf = new StringBuilder(firstHalf).reverse().toString();
        
        return firstHalf + center + lastHalf;
    }
    
}
