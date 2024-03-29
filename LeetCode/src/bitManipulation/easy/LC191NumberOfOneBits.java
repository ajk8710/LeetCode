package bitManipulation.easy;

// Write a function that takes positive integer,
// and returns the number of set bits (one bits) it has (also known as the Hamming weight).
public class LC191NumberOfOneBits {
    
    // To find if the rightmost bit is 0 or 1: Mod the integer by 2. If result is 1, then rightmost bit is 1.
    // Another way is by doing & (bitwise-and) with 1 (00000001) and see if result is 1 or 0.
    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            count += n % 2;  // If result is 1 (rightmost bit is 1) increment by 1, otherwise increment by 0.
            n = n >> 1;  // Rightshift n to check next number. Continue until n becomes 0, consists of only 0 bits.
        }

        return count;
    }
    
}
