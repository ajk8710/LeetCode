package arraysAndHashing.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Given an array of strings strs, group the anagrams together. You can return the answer in any order.
// Input: strs = ["eat","tea","tan","ate","nat","bat"]
// Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
public class LC49GroupAnagrams {
    
    // A map: characters to count - represents each anagram.
    // Construct "groupByAnagram" map: anagram to list<String>.
    // Return groupByAnagram.values().
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
    
}
