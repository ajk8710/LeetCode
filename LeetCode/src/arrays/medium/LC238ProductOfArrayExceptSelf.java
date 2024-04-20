package arrays.medium;

// Given an integer array nums, return an array answer such that
// answer[i] is equal to the product of all the elements of nums except nums[i].
// Input: nums = [1,2,3,4]
// Output: [24,12,8,6]

// You must write an algorithm that runs in O(n) time and without using the division operation.
// The output array does not count as extra space for space complexity analysis.
public class LC238ProductOfArrayExceptSelf {
    
    // Concept: product except me = product of my left * product of my right
    //                  Input nums: [1, 2, 3, 4]
    //      Left product before me: [1, 1, 2, 6]  // Each index represents left product before the index
    //      Right product after me: [24,12,4, 1]  // Each index represents right product before the index
    // Multiply left & right of me: [24,12,8, 6]
    
    // Solution using two extra arrays: leftProds & rightProds
    public int[] productExceptSelfExtraMemory(int[] nums) {
        int len = nums.length;
        int[] leftProds = new int[len];  // Each index represents left product before the index.
        int[] rightProds = new int[len];  // Each index represents right product before the index.
        int[] answer = new int[len];
        
        // Construct leftProds.
        int leftProduct = 1;  // start from 1 since index 0 is always 1 for leftProds.
        for (int i = 0; i < len; i++) {
            leftProds[i] = leftProduct;  // Assign leftProduct so far before index i.
            leftProduct *= nums[i];  // Update leftProduct so far by multiplying me.
        }
        
        // Construct rightProds.
        int rightProduct = 1;  // start from 1 since last index is always 1 for rightProds.
        for (int i = len - 1; i >= 0; i--) {
            rightProds[i] = rightProduct;  // Assign rightProduct so far before index i.
            rightProduct *= nums[i];  // Update rightProduct so far by multiplying me.
        }
        
        // Construct answer.
        for (int i = 0; i < len; i++) {
            answer[i] = leftProds[i] * rightProds[i];
        }
        
        return answer;
    }
    
    // Solution using no extra memory: Use answer-array to construct leftProds.
    // Then multiply elements on the fly, starting from right.
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] answer = new int[len];
        
        // Construct leftProds. Use answer-array itself.
        int leftProduct = 1;  // start from 1 since index 0 is always 1 for leftProds.
        for (int i = 0; i < len; i++) {
            answer[i] = leftProduct;  // Assign leftProduct so far before index i.
            leftProduct *= nums[i];  // Update leftProduct so far by multiplying me.
        }

        // Instead of constructing rightProds then multiplying, multiply elements on the fly.
        int rightProduct = 1;  // start from 1 since last index is always 1 for rightProds.
        for (int i = len - 1; i >= 0; i--) {
            answer[i] *= rightProduct;  // Instead of assign (=) rightProduct so far before index i, multiply (*=) it onto the answer on the fly.
            rightProduct *= nums[i];  // Update rightProduct so far by multiplying me.
        }

        return answer;
    }
    
}
