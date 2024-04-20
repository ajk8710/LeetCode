package arrays.medium;

import java.util.Arrays;
import java.util.HashSet;

// Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
// You must write an algorithm that runs in O(n) time.

// Input: nums = [100, 4, 200, 1, 3, 2]
// Output: 4
public class LC128LongestConsecutiveSequence {
    
    // Method using HashSet, removes duplicates. Time: O(n)
    // Iterate HashSet. If current element - 1 does not exists, it means it's the first element of a sequence.
    // (Only consider the first element of a sequence. The other ones, I already visited & ignored, or will visit later when I see the first element.)
    // Find current element + 1 from the set. If exists, currentSeq++. Update current element += 1 and continue.
    // When next consecutive element does not exists, reset current sequence and see if it was longest sequence.
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {  // edge case for empty array
            return nums.length;
        }
        
        // Create HashSet from array. Can use Streams.
        // Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());  // Using Streams.
        // HashSet<Integer> set = new HashSet<>(Arrays.asList(arr));  // Does not work because arr is not Integer[].
        HashSet<Integer> set = new HashSet<>();  // Or manually add with for loop.
        for (int n : nums) {
            set.add(n);
        }
        
        int longestSeq = 1;
        int currentSeq = 1;
        for (int i : set) {  // Iterate the set.
            if (!set.contains(i - 1)) {  // Only if i is the first element of a sequence,
                while (set.contains(i + 1)) {  // If next consequtive number exists,
                    currentSeq++;  // Increment currentSeq.
                    i++;  // Update i.
                }
                longestSeq = Math.max(longestSeq, currentSeq);  // Check if currentSeq was longestSeq.
                currentSeq = 1;  // Reset currentSeq.
            }
        }
        
        return longestSeq;
    }
    
    
    // Method using sort. Time: O(nlogn)
    // Sort. Go through array while counting the length of current sequence if it's consecutive.
    // Reset current sequence if next number is not consecutive, and check if current sequence was longest.
    public int longestConsecutiveUsingSort(int[] nums) {
        if (nums.length <= 1) {  // edge case for empty array
            return nums.length;
        }
        
        Arrays.sort(nums);
        
        // Go through array while counting the length of current seqence if it's consecutive.
        // If next element is non-consecutive, end current sequence and see if it was longest.
        int longestSeq = 1;
        int currentSeq = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 == nums[i + 1]) {  // If current number + 1 == next number,
                currentSeq += 1;  // Increment the length of current sequence.
            }
            else if (nums[i] != nums[i + 1]) {  // Then if current number != next number,
                longestSeq = Math.max(longestSeq, currentSeq);  // See if currentSeq was longest.
                currentSeq = 1;  // Reset currentSeq.
            }
            // else current number == next number, do nothing and move on.
            
            // Edge case when the last element was consecutive.
            longestSeq = Math.max(longestSeq, currentSeq);  // See if currentSeq was longest
        }
        
        return longestSeq;
    }
    
}
