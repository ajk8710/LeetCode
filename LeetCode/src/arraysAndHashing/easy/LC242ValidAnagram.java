package arraysAndHashing.easy;

import java.util.Arrays;
import java.util.HashMap;

// Given two strings s and t, return true if t is an anagram of s, and false otherwise.
// Anagram is a word formed by rearranging the letters, using all the original letters exactly once.
public class LC242ValidAnagram {
    
    // This problem is basically to find if counts of each letter are the same.
    // Solution using map: letter to its count.
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {  // if length is different
            return false;
        }
        
        HashMap<Character, Integer> letterToCount = new HashMap<>();
        
        // Count each letter for String s.
        for (char c : s.toCharArray()) {
            if (!letterToCount.containsKey(c)) {  // method name is containsKey, not contains.
                letterToCount.putIfAbsent(c, 1);  // regular put method would work too
            }
            else {
                letterToCount.replace(c, letterToCount.get(c) + 1);  // regular put method would work too
            }
        }
        
        // Compare with String t.
        for (char c : t.toCharArray()) {
            if (!letterToCount.containsKey(c)) {  // if contains letter that is not in String s
                return false;
            }
            else if (letterToCount.get(c) == 0) {  // if there is more of this letter than String s
                return false;
            }
            
            letterToCount.replace(c, letterToCount.get(c) - 1);  // match and decrement
        }
        
        return true;
    }
    
    
    // Solution by sorting then comparing.
    public boolean isAnagramBySorting(String s, String t) {
        if (s.length() != t.length()) {  // if length is different
            return false;
        }
        
        char[] arrS = s.toCharArray();
        char[] arrT = t.toCharArray();
        Arrays.sort(arrS);  // Arrays.sort(array) sorts given array in place and returns void
        Arrays.sort(arrT);  // If you want sorted String, String sortedString = new String(sortedArray);
        
        return Arrays.equals(arrS, arrT);  // Arrays.equals(arr1, arr2) returns boolean.
    }
    
}
