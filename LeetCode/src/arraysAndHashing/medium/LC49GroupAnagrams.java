package arraysAndHashing.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// Given an array of strings strs, group the anagrams together. You can return the answer in any order.
// Input: strs = ["eat","tea","tan","ate","nat","bat"]
// Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
public class LC49GroupAnagrams {
    
    // A map: characters to count - represents each anagram.
    // Construct "groupByAnagram" map: anagram to list<String>.
    // Return groupByAnagram.values().
    // Time: O(m*n) where n is length of input list and m is average length of strings.
    public List<List<String>> groupAnagrams(String[] strs) {
        
        // "groupByAnagram" map: anagram to list<String>
        HashMap<HashMap<Character, Integer>, List<String>> groupByAnagram = new HashMap<>();
        
        // For each string of given list<String>, construct char_to_count map (anagram).
        // Update groupByAnagram depending on whether char_to_count already present as a key or not.
        for (String str : strs) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (char c : str.toCharArray()) {
                if (!map.containsKey(c)) {  // containsKey, not contains.
                    map.put(c, 1);
                }
                else {
                    map.put(c, map.get(c) + 1);
                }
            }
            
            // If char_to_count map is not present, put it as new key-value, otherwise add string for proper key.
            if (!groupByAnagram.containsKey(map)) {  // containsKey, not contains.
                List<String> list = new ArrayList<>();
                list.add(str);
                groupByAnagram.put(map, list);
            }
            else {
                groupByAnagram.get(map).add(str);
            }
        }
        
        // Return groupByAnagram.values() which is Collection<List<String>>.
        return new ArrayList<List<String>>(groupByAnagram.values()); 
    }
    
    
    // Another way is using ArrayList of length 26 as keys, instead of char_to_count map.
    // 26 index represents count of each char from a to z.
    public List<List<String>> groupAnagramsUsingArrayList(String[] strs) {
        
        // Instead of HashMap<Character, Integer> as a key,
        // Using ArrayList<Integer> as a key.
        HashMap<ArrayList<Integer>, List<String>> groupByAnagram = new HashMap<>();
        
        // For each string of given list<String>, construct char_to_count map (anagram).
        // Update groupByAnagram depending on whether char_to_count already present as a key or not. (This check is not necessary if using ArrayList)
        for (String str : strs) {
            ArrayList<Integer> anagram = new ArrayList<>(Arrays.asList(new Integer[26]));
            Collections.fill(anagram, 0);
            for (char c : str.toCharArray()) {
                anagram.set(c - 'a', anagram.get(c - 'a') + 1);
            }
            
            // If char_to_count map is not present, put it as new key-value, otherwise add string for proper key.
            if (!groupByAnagram.containsKey(anagram)) {  // containsKey, not contains.
                List<String> list = new ArrayList<>();
                list.add(str);
                groupByAnagram.put(anagram, list);
            }
            else {
                groupByAnagram.get(anagram).add(str);
            }
        }
        
        // Return groupByAnagram.values() which is Collection<List<String>>.
        return new ArrayList<List<String>>(groupByAnagram.values()); 
    }
    
}
