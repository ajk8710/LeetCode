package binarySearch.medium;

// There is an integer array nums sorted in ascending order (with distinct values).
// Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
// such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
// For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
// Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
// You must write an algorithm with O(log n) runtime complexity.
// Input: nums = [4,5,6,7,0,1,2], target = 0
// Output: 4
public class LC33SearchInRotatedSortedArray {
    
    // Notice that the rotated array consists of two sorted arrays, left array & right array. Choose arbitrary m, mid for example.
    // 4 5 6 7 0 1 2
    // If m is in left array, and target is in between l ~ m (left portion of left array: sorted portion), binary search l ~ m
    //                      , and target is not in l ~ m, set l = m + 1.
    // If m is in right array, and target is in between m ~ r (right portion of right array: sorted portion), binary search m ~ r.
    //                       , and target is not in m ~ r, set r = m - 1.
    // How to find if m is in which sorted array: nums[left] <= nums[mid] then m in left array. Else right array.
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            if (nums[left] <= nums[right]) {  // If a single sorted array,
                return binarySearch(nums, target, left, right);  // Do regular binary search.
            }  // Else it consists of two sorted arrays.
            
            int mid = (left + right) / 2;
            if (nums[mid] == target) {  // See if mid is target.
                return mid;
            }
            
            if (nums[left] <= nums[mid]) {  // If mid is in left array
                if (nums[left] <= target && target < nums[mid]) {  // and if left <= target <= mid,
                    return binarySearch(nums, target, left, mid - 1);  // Do regular binary search within that portion of left array.
                }
                else {
                    left = mid + 1;  // Else look from right of mid.
                }
            }       // 4 5 6 7 0 1 2
            else {  // nums[left] > nums[mid]: If mid is in right array
                if (nums[mid] < target && target <= nums[right]) {  // & if mid <= target <= right,
                    return binarySearch(nums, target, mid + 1, right);  // Do regular binary search within that portion of right array.
                }
                else {
                    right = mid - 1;  // Else look from left of mid.
                }
            }
        }
        
        return -1;
    }
    
    public static int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {  // Do regular binary search
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } 
            else if (nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    
    // Notice search() method itself is a binary search and we do not need a separate method to do regular binary search.
    public int searchWithSingleMethod(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {            
            int mid = (left + right) / 2;
            if (nums[mid] == target) {  // See if mid is target.
                return mid;
            }
            
            if (nums[left] <= nums[mid]) {  // If mid is in left array
                if (nums[left] <= target && target < nums[mid]) {  // and if left <= target <= mid,
                    right = mid - 1;  // Do regular binary search within that sorted portion of left array. Which is "if (nums[left] <= nums[mid])" case.
                }
                else {
                    left = mid + 1;  // Else look from right of mid.
                }
            }       // 4 5 6 7 0 1 2
            else {  // nums[left] > nums[mid]: If mid is in right array
                if (nums[mid] < target && target <= nums[right]) {  // & if mid <= target <= right,
                     left = mid + 1;  // Do regular binary search within that sorted portion of right array. Which is "if (nums[left] <= nums[mid])" case.
                }
                else {
                    right = mid - 1;  // Else look from left of mid.
                }
            }
        }
        
        return -1;
    }
    
}
