package slidingWindow.medium;

import java.util.HashMap;
import java.util.HashSet;

// Given a string s, find the length of the longest substring without repeating characters.
// Input: s = "abcabcbb"
// Output: 3
// Explanation: The answer is "abc", with the length of 3.
public class LC3LongestSubstringWithoutRepeatingCharacters {
    
    // Sliding window technique (is a kind of two pointer technique). Time: O(n), Space: O(n) for a set.
    // Ex: abcdeabc
    // Instead of creating almost duplicate substrings: abcde, bcde.., cde..
    // Drop the existing duplicate letter and ones preceding it, when duplicate is encountered.
    // Ex: abcde then 'a' encountered - Drop the leading a & whatever preceeds.
    // This way, we only iterate n times from beginning to end.
    // Also we do not create almost duplicate substrings such as abcde & bcde.

    // Two pointer technique is each pointer advancing on different conditions.
    // From left = 0 & right = 0, add charAt(right) to set, and advance right only.
    // Before advancing right, see if set will contain duplicate by advancing / increasing the window size (window size: left ~ right).
    // If so, Until the duplicate is removed, remove charAt(left) and advance left pointer.
    // (Compute the length either on each iteration, or right before truncating & at the end in case of never truncating.)
    // (Can compute the length either by window size, or set size.)
    public int lengthOfLongestSubstring(String s) {
        
        HashSet<Character> set = new HashSet<>();
        int maxLen = 0;
        char[] sArr = s.toCharArray();
        
        int left = 0;
        for (int right = 0; right < sArr.length; right++) {  // Keep increasing the window (left ~ right) by advancing right.
            while (set.contains(sArr[right])) {  // See if window will have duplicate by incrementing right.
                maxLen = Math.max(maxLen, set.size());
                set.remove(sArr[left]);  // Using 'while' instead of 'if' to check, because we need to truncate left until window will not contain duplicate
                left++;
            }
            set.add(sArr[right]);  // Knowing this won't be duplicate, add to set.
            // maxLen = Math.max(maxLen, right - left + 1);  // Can use window size to check the length, instead of using set size.
        }
        maxLen = Math.max(maxLen, set.size());
        
        return maxLen;
    }
    
    
    // In this method, I tried to keep the track of index of each character using HashMap.
    // This is not a good way because index keep changing when the subString is truncated from the front.
    // Also I have to remove items in HashMap when I remove characters in subString. It's harder to keep two data structures consistent.
    public int lengthOfLongestSubstringNoGood(String s) {
        
        HashMap<Character, Integer> map = new HashMap<>();  // Map to save index of each char in current subString.
        String subStr = "";
        int maxLen = 0;
        char[] sArr = s.toCharArray();
        
        for (int i = 0; i < sArr.length; i++) {
            char iChar = sArr[i];
            
            if (map.containsKey(iChar)) {  // Duplicate encountered.
                maxLen = Math.max(maxLen, subStr.length());
                if (iChar == subStr.charAt(subStr.length() - 1)) {  // If duplicate encountered is the same as the last char of subStr,
                    System.out.println("Duplicate encountered and is same as the last char of subStr.");
                    System.out.println("subStr  : " + subStr);
                    System.out.println("iChar   : " + iChar);
                    subStr = String.valueOf(iChar);                 // Drop whatever preceeds & Restart subStr.
                    map = new HashMap<>();
                    map.put(iChar, i);
                }
                else {  // If not the last char, drop it and whatever preceeding letters. Also remove them from the set.
                    System.out.println("Duplicate encountered and is not the same as the last char of subStr.");
                    System.out.println("subStr  : " + subStr);
                    System.out.println("iChar   : " + iChar);
                    
                    int iCharIdx = map.get(iChar);
                    System.out.println("iCharIdx: " + iCharIdx);

                    for (int k = 0; k <= iCharIdx; k++) {
                        System.out.println("Removing from map.");
                        System.out.println("idx             : " + k);
                        System.out.println("subStr.charAt(k): " + subStr.charAt(k));
                        map.remove(subStr.charAt(k));
                    }
                    
                    subStr = subStr.substring(iCharIdx + 1);
                    map.put(iChar, subStr.length());
                    subStr += String.valueOf(iChar);  // Drop existing duplicate letter & whatever preceeds it from subStr.

                    for (int x = 0; x < subStr.length(); x++) {  // Index of each character is rearranged as subStr is truncated at front.
                        map.put(subStr.charAt(x), x);
                    }
                    System.out.println("New subStr: " + subStr);
                    System.out.println("New map   : " + map);
                    
                }
            }
            else {  // iChar is not duplicate compared to whatever in current subStr.
                map.put(iChar, subStr.length());
                subStr += String.valueOf(iChar);
                System.out.println("Duplicate not encountered.");
                System.out.println("subStr  : " + subStr);
            }
        }
        maxLen = Math.max(maxLen, subStr.length());
        
        return maxLen;
    }
    
    
    // Brute force method, creating all possible substrings without duplicates. Time O(n^2).
    // Starting from the first letter, i, create substring by keep appending next letter if it's not duplicate.
    // Stop if duplicate found. Do the same for next letter i + 1 (creating substring starting from i + 1).
    public int lengthOfLongestSubstringBruteForce(String s) {
        
        int maxLen = 0;
        char[] sArr = s.toCharArray();

        for (int i = 0; i < sArr.length; i++) {  // Iterate from the first letter, i.
            HashSet<Character> set = new HashSet<>();  // A set for this i.
            set.add(sArr[i]);
            maxLen = Math.max(maxLen, set.size());
            for (int j = i + 1; j < sArr.length; j++) {  // From i + 1, append it if not duplicate.
                if (set.contains(sArr[j])) {  // If duplicate, stop for this i.
                    break;
                }
                else {
                    set.add(sArr[j]);  // If not duplicate, keep appending. No need to create actual substring.
                    maxLen = Math.max(maxLen, set.size());  // Just add a letter to the set. set.size() is the length of substring.
                }
            }
        }

        return maxLen;
    }
    
}
