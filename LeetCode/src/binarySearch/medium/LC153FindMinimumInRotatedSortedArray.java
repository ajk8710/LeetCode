package binarySearch.medium;

// Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
// For example, the array nums = [0,1,2,4,5,6,7] might become:
// [4,5,6,7,0,1,2] if it was rotated 4 times.
// [0,1,2,4,5,6,7] if it was rotated 7 times.
// Given the sorted rotated array nums of unique elements, return the minimum element of this array.
// You must write an algorithm that runs in O(log n) time.
// Input: nums = [3,4,5,1,2]
// Output: 1
public class LC153FindMinimumInRotatedSortedArray {
    
    // 1 2 3 4 5 Sorted Array
    // 5 1 2 3 4 Rotated Once (When rotates, idx n-1 goes to idx 0)
    // 4 5 1 2 3 Rotated Twice
    // 3 4 5 1 2
    // 2 3 4 5 1
    // 1 2 3 4 5 Back to original
    
    // Note that:
    // Rotated array consists of two sorted arrays: left array & right array. (Unless it's rotated back to original single sorted array.)
    // Elements in left array are always larger then elements in right array. (Due to the right rotation, larger elements are put on idx 0)
    // Leftmost element of right array is the smallest.
    
    // Binary Search:
    // 5 6 7 8 9 0 1 2 3 4
    // l       m         r  (l: leftmost element, r: rightmost element)
    // Choose m (middle for ex). Check if m itself could be smallest (Leftmost of right array).
    // We need to check if chosen m (middle for ex) belongs to left array or right array.
    // If m is part of left array, anything left of it is larger then elements of right array. Discard them by setting l = m + 1
    // If m is part of right array, anything right of it is larger then m: Discard them by setting r = m - 1
    
    // How to find if m belongs to which array:
    // For any chosen index of rotated array, middle for example:
    // If leftmost element <= m, then m belongs to left array. (leftmost == m means left array consists of m only)
    // Else (leftmost > m), then m belongs to right array.
    
    // Above logic is only valid if array is consists of two sorted arrays.
    // At any point, if an element at l <= an element at r, then it is a single sorted array. Break after checking if leftmost is smallest.
    
    // To summarize:
    // Knowing the rotated array consists of two sorted arrays, left & right,
    // the goal is to find the leftmost element of right array.
    // To do so, if m is a part of left array: discard left of m and look from right of m.
    // If m is a part of right array, but the range (l ~ r) still consists of two arrays (arr[l] > arr[r]), discard right of m and look from left of m.
    // Once the range (l ~ r) is a single sorted array (arr[l] <= arr[r]), it is the end of search, the leftmost of it is possible min.
    // When the range is a single sorted array, it could be two case: either whole elements are in left array, or whole elements are in right array.
    // If right array, we found the goal: leftmost is the leftmost of right array. (We've been discarding right of right array, so its left portion of right array.)
    // If left array, we found the goal the step earlier, when we chose m: the m was the leftmost of right array.
    // (We never discarded right of left array. The right of current rightmost (m) was the leftmost element of right array.
    
    public int findMin(int[] nums) {
        int min = nums[0];
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] <= nums[right]) {  // If the range is single sorted array, (Edge case: nums[left] == nums[right] if a single element array)
                min = Math.min(min, nums[left]);  // Check if leftmost is min
                break;
            }  // else, the range consists of two sorted arrays.
            
            int mid = (left + right) / 2;
            // min = Math.min(min, nums[mid]);  // Check if mid is min (leftmost of right sorted array)
            if (nums[left] <= nums[mid]) {  // mid is part of left sorted array
                left = mid + 1;  // Discard left of mid.
            }
            else {  // mid is part of right sorted array
                min = Math.min(min, nums[mid]);  // Check if mid is min (leftmost of right sorted array)
                right = mid - 1;  // Discard right of mid.
            }
        }
        
        return min;
    }
    
}
