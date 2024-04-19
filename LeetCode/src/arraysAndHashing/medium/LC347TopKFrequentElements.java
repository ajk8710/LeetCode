package arraysAndHashing.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Given an integer array nums and an integer k, return the k most frequent elements.
// You may return the answer in any order.
// k is in the range [1, the number of unique elements in the array].
// Input: nums = [1, 1, 1, 2, 2, 3], k = 2
// Output: [1, 2]
public class LC347TopKFrequentElements {
    
    // Method using HashMap, then creating an array of number lists. Time: O(n)
    // For the array, index means frequency, value means a list of the numbers of that frequency.
    // Starting from last index, if list is not null, add numbers of the list into rv. Stop when added k numbers.
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> numFreq = new HashMap<>();
        
        for (int n : nums) {  // Create a map of number to frequency.
            if (!numFreq.containsKey(n)) {
                numFreq.put(n, 1);
            }
            else {
                numFreq.put(n, numFreq.get(n) + 1);
            }
        }
        
        // An array of lists. Maximum length possible is nums.length (if each num is distinct).
        // Index represents frequency of each num. Value represents a list of numbers of that frequency.
        List<Integer>[] arrayOfList = new ArrayList[nums.length + 1];  // cannot create array with '<>' on righthand.
        
        for (java.util.Map.Entry<Integer, Integer> entry : numFreq.entrySet()) {
            int freq = entry.getValue();
            int number = entry.getKey();
            if (arrayOfList[freq] == null) {
                arrayOfList[freq] = new ArrayList<Integer>();
            }
            arrayOfList[freq].add(number);
        }
        
        int[] rv = new int[k];
        int rvIdx = 0;
        for (int i = arrayOfList.length - 1; i >= 1; i--) {  // From last index, if a list is not null, add its number into rv. Stop when added k numbers.
            if (arrayOfList[i] != null) {
                for (int num : arrayOfList[i]) {
                    rv[rvIdx] = num;
                    rvIdx++;
                    if (rvIdx == k) {
                        return rv;
                    }
                }
            }
        }
        return null;
    }
    
    
    // Method using HashMap, converting it to list of entrySets then sorting by value. Time: O(nlogn)
    // Create a map of number to frequency.
    // Create a list of entrySets using map.entrySet(). Sort by value.
    // Return an array of k elements. For each index of an array, insert from most frequent number.
    public int[] topKFrequentUsingSorting(int[] nums, int k) {
        HashMap<Integer, Integer> numFreq = new HashMap<>();
        
        for (int n : nums) {  // Create a map of number to frequency.
            if (!numFreq.containsKey(n)) {
                numFreq.put(n, 1);
            }
            else {
                numFreq.put(n, numFreq.get(n) + 1);
            }
        }
        
        // Create a list of entrySets. Sort by value.
        List<java.util.Map.Entry<Integer, Integer>> list = new ArrayList<>(numFreq.entrySet());
        list.sort(java.util.Map.Entry.comparingByValue());
        
        // Return an array of k elements. Either iterate from last index, or do Collections.reverse(list) then iterate.
        int[] topKs = new int[k];
        for (int i = list.size() - 1; i >= list.size() - k; i--) {  // i from last index, until becomes smaller than size - k.
            topKs[list.size() - i - 1] = list.get(i).getKey();
        }
        
        return topKs;
    }
    
}
