package twoPointers.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
// such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
// The solution set must not contain duplicate triplets.
// Input: nums = [-1, 0, 1, 2, -1, -4]
// Output: [[-1, -1, 2], [-1, 0, 1]]
public class LC15ThreeSum {
    
    // x + y + z = 0
    // Sort the array. In each iteration, select x from the index 0 to lastIndex - 2.
    // Once x is chosen, it's basically two sum problem. (Two Sum II - sorted array).
    // Select y & z (in which x+y+z=0) from right-side of x. Use two pointer technique.
    // To avoid duplicate triplets, do not select x that was selected as x before.
    // We can stop the loop when x becomes positive, because pos + pos + pos cannot be 0.
    // Time: O(n^2), Space: O(1)
    public List<List<Integer>> threeSum(int[] nums) {
        
        // Sorting helps to: not select duplicate x & apply two pointer to find y & z.
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        
        // If given array is [-3 -3 -3 ... 6]
        // First appearance of -3, Select as x: [-3, -3, 6]
        // Next appearance of -3, Skip. Do not select as x.
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;  // Do not select duplicate x.
            }
            
            if (nums[i] > 0) {  // Array is sorted & pos + pos + pos cannot be 0. No further evaluation needed.
                break;
            }
            
            // Two pointer technique.
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int threeSum = nums[i] + nums[left] + nums[right];
                if (threeSum < 0) {
                    left++;
                }
                else if (threeSum > 0) {
                    right--;
                }
                else {
                    list.add(Arrays.asList(new Integer[] {nums[i], nums[left], nums[right]}));
                    left++;
                    while (nums[left] == nums[left - 1] && left < right) {
                        left++;  // Skip duplicate left.
                    }
                }
            }
        }
        
        return list;
    }
    
}
