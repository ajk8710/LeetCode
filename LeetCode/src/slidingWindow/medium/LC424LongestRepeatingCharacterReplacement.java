package slidingWindow.medium;

import java.util.Collections;
import java.util.HashMap;

public class LC424LongestRepeatingCharacterReplacement {
    
    // Sliding window technique. Time: O(26n) = O(n).
    // From left = 0 & right = 0, add charAt(right) to map, updating its frequency, and advance right only.
    // Before advancing, find the maximum frequent char from the map, O(26).
    // If windowSize - maxFreqency <= allowedNonEqualityCount, then it is valid window.
    // If not valid window, truncate left by advancing left pointer until valid (Use while loop, or if).
    // Check the maximum length which is max(maxLen, windowSize)
    public int characterReplacement(String s, int k) {
        
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        char[] strArr = s.toCharArray();
        
        int left = 0;
        for (int right = 0; right < strArr.length; right++) {
            char ch = strArr[right];
            if (map.containsKey(ch)) {  // Update the frequency of char
                map.put(ch, map.get(ch) + 1);
            }
            else {
                map.put(ch, 1);
            }
            
            int windowSize = right - left + 1;
            if (windowSize - Collections.max(map.values()) > k) {  // windowSize - maxFreqency <= allowedNonEqualityCount for valid window
                map.put(strArr[left], map.get(strArr[left]) - 1);
                left++;
                windowSize--;  // No need to use while loop, because reducing window size results in decrementing "windowSize - Collections.max(map.values())".
            }
            maxLen = Math.max(maxLen, windowSize);
        }
        
        return maxLen;
    }
    
    
    // Instead of finding the maxFreq from the map when checking if the valid window size,
    // Update maxFreq when updating the map: it's either previous max or the value that I just updated.
    public int characterReplacementRefined(String s, int k) {
        
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        char[] strArr = s.toCharArray();
        
        int maxFreq = 0;  // Keep track of maxFreq.
        
        int left = 0;
        for (int right = 0; right < strArr.length; right++) {
            char ch = strArr[right];
            if (map.containsKey(ch)) {  // Update the frequency of char
                map.put(ch, map.get(ch) + 1);
            }
            else {
                map.put(ch, 1);
            }
            maxFreq = Math.max(maxFreq, map.get(ch));  // maxFreq is either previous max or the value that I just updated.

            int windowSize = right - left + 1;
            if (windowSize - maxFreq > k) {  // windowSize - maxFreqency <= allowedNonEqualityCount for valid window
                map.put(strArr[left], map.get(strArr[left]) - 1);  // What if this decremented maxFreq? Does not matter: It does not change the reuslt of "windowSize - maxFreq > k"
                left++;                                            // Potential best answer is "maxFreq + k." Reducing maxFreq does not influence the final answer.
                windowSize--;  // No need to use while loop, because reducing window size results in decrementing "windowSize - Collections.max(map.values())".
            }
            maxLen = Math.max(maxLen, windowSize);
        }
        
        return maxLen;
    }
    
    
    // Brute force method, creating all possible substrings with maximum non-equal characters. Time O(n^2). Not working as intended.
    // Starting from the first letter, i, create substring by keep appending next letter until maximum non-equality.
    // Do the same for next letter i + 1 (creating substring starting from i + 1).
    // I need to run this loop twice: from left and then from right, because String could be abbbbb.
    // Then what about baaab? Does not work.
    // The method should be computing the maximum frequent character, not comparing with ith character.
    public int characterReplacementBruteForce(String s, int k) {
        int maxLen = 0;
        char[] strArr = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {  // A String starting with i.
            char iChar = strArr[i];
            int numberOfNonI = 0;
            int strLen = 1;
            for (int j = i + 1; j < s.length(); j++) {  // Append next chars until maximum non-equality.
                if (strArr[j] != iChar) {
                    numberOfNonI++;
                }
                if (numberOfNonI > k) {  // Maximum non-equality reached.
                    break;
                }
                strLen++;
                maxLen = Math.max(maxLen, strLen);
            }
        }

        for (int i = s.length() - 1; i >= 1; i--) {  // A String starting with i.
            char iChar = strArr[i];
            int numberOfNonI = 0;
            int strLen = 1;
            for (int j = i - 1; j >= 0; j--) {  // Append next chars until maximum non-equality.
                if (strArr[j] != iChar) {
                    numberOfNonI++;
                }
                if (numberOfNonI > k) {  // Maximum non-equality reached.
                    break;
                }
                strLen++;
                maxLen = Math.max(maxLen, strLen);
            }
        }

        return maxLen;
    }
    
}
