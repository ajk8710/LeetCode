package twoPointers.medium;

// You are given an integer array height of length n.
// There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]). (meaning vertical walls)
// Find two lines that together with the x-axis form a container, such that the container contains the most water.
// Return the maximum amount of water a container can store.
// 2 <= n <= 105
// Input: height = [1,8,6,2,5,4,8,3,7]
// Output: 49
public class LC11ContainerWithMostWater {
    
    // Using two pointers. Time O(n), Space O(1).
    // Set left & right pointers at each ends because then the width is maximum. Calculate area.
    // Always move the pointer with lower height. By doing so we check if moved pointer has higher height even though width is reduced.
    // If moving pointers end up with lower area, it does not matter because we have already captured maximum area.
    public int maxArea(int[] height) {
        int maxA = 0;
        
        // Using two pointers
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int h = Math.min(height[left], height[right]);  // Lower wall is the height.
            maxA = Math.max(maxA, h * (right - left));  // Calculate area.
            if (height[left] < height[right]) {  // Move the pointer with lower wall.
                left++;
            }
            else {
                right--;
            }
        }

        return maxA;
    }
    
    
    /*
    // Brute force O(n^2) solution - time limit exceeds
    public int maxArea(int[] height) {
        int maxA = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int h = Math.min(height[i], height[j]);
                maxA = Math.max(maxA, h * (j - i));
            }
        }
        return maxA;
    }
    */
    
}
