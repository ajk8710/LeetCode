package arraysAndHashing.easy;

import java.util.HashMap;

// Given an array of integers nums and an integer target,
// return indices of the two numbers such that they add up to target.

// You may assume that each input would have exactly one solution,
// and you may not use the same element twice.
public class LC1TwoSum {
    
    // Prepare HashMap<Integer, Integer>: key is number, value is index.
    // Go through array, compute value's pair = target - value.
    // If pair found in map, return {i, pair's index}.
    // If pair not found, put(value, index).
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numToIdx = new HashMap<>();
        
        // Go through array, compute pair, return {i, pair's index} if pair is found.
        for (int i = 0; i < nums.length; i++) {
            int pair = target - nums[i];
            
            if (numToIdx.containsKey(pair)) {  // nums[i] not added to map yet, prevents returning {i, i}
                return new int[] {i, numToIdx.get(pair)};
            }
            
            numToIdx.put(nums[i], i);  // If pair not found, add nums[i] to map.
        }
        
        return null;
        
        
        /* Following requires to run for-loop twice, also requires to check I'm not returning {i, i}
        HashMap<Integer, Integer> numToIdx = new HashMap<>();
        
        // Create map: number to index
        for (int i = 0; i < nums.length; i++) {
            numToIdx.put(nums[i], i);  // If same number is repeated, new value replaces old value.
        }

        // Go through array, compute pair, return {i, pair's index} if pair is found.
        for (int i = 0; i < nums.length; i++) {
            int pair = target - nums[i];
            if (numToIdx.containsKey(pair) && numToIdx.get(pair) != i) {
                return new int[] {i, numToIdx.get(pair)};
            }
        }

        return null;
        */
    }
}
