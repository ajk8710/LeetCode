package twoPointers.medium;

// Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
// find two numbers such that they add up to a specific target number.
// Return the indices of the two numbers, index1 and index2 where 1 <= index1 < index2 <= numbers.length.
// There is exactly one solution. You may not use the same element twice.

// This is LC1 Two Sum I, but the input array is sorted.
// Since array is sorted, O(n) solution with "no extra memory" is available, using two pointers.
public class LC167TwoSumII {
    
    // O(n) solution: Use two pointers, starting from the beginning (i) & the end (j) of given sorted array.
    // Array is sorted. Sum first (smallest) and last (largest) element.
    // If sum < target, increment i, to add bigger number.
    // If sum > target, decrement j, to add smaller number.
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        
        while (i < j) {  // Easier to use while-loop instead of for-loop, as i & j does not always inc/decrement in every loop.
            if (numbers[i] + numbers[j] < target) {  // If sum < target, increment i, to add bigger number.
                i++;
            }
            else if (numbers[i] + numbers[j] > target) {  // If sum > target, decrement j, to add smaller number.
                j--;
            }
            else {
                return new int[] {i + 1, j + 1};  // Return 1-indexed array if target sum found.
            }
        }
        
        return null;  // Never gets here as answer exists for the problem.
    }
    
    
    // O(n^2) solution: Loop from 1st element to last-1 element, checking every sum with next elements.
    // When sum > target, discard next elements in consideration.
    // Because given array is sorted, sum with next element will always be larger.
    public int[] twoSumLessEfficient(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {  // For each element,
            for (int j = i + 1; j < numbers.length; j++) {  // sum with next elements
                if (numbers[i] + numbers[j] == target) {
                    return new int[] {i + 1, j + 1};  // Return 1-indexed array if target sum found.
                }
                else if (numbers[i] + numbers[j] > target) {  // If sum > target,
                    break;  // ignore next j elements for this i. Go loop for next i.
                }
            }
        }
        
        return null;  // Never gets here as answer exists for the problem.
    }
    
}
