package bitManipulation.easy;

// Write a function that takes positive integer,
// and returns the number of set bits (one bits) it has (also known as the Hamming weight).
public class LC191NumberOfOneBits {
    
    // To find if the rightmost bit is 0 or 1: Mod the integer by 2. If result is 1, then rightmost bit is 1. (Odd number has 1 on rightmost bit.)
    // (00000000 is 0. 00000001 is 1. 00000010 is 2. Pattern continues. Having 1 on rightmost bit is adding one to every even number.)
    // Time: O(1) because it's O(32). Runs through 32 bits of given integer, for whatever given integer. 
    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            count += n % 2;  // If result is 1 (rightmost bit is 1) increment by 1, otherwise increment by 0.
            n = n >> 1;  // Rightshift n to check next number. Continue until n becomes 0, consists of only 0 bits.
        }

        return count;
    }
    
    
    // Another way is by doing & (bitwise-and) with 1 (00000001) and see if result is 1 or 0.
    // (-------0 & 00000001 is 0. -------1 & 00000001 is 1.
    public int hammingWeightBitwiseAnd(int n) {
        int count = 0;

        while (n != 0) {
            count += n & 1;  // If result is 1 (rightmost bit is 1) increment by 1, otherwise increment by 0.
            n = n >> 1;  // Rightshift n to check next number. Continue until n becomes 0, consists of only 0 bits.
        }

        return count;
    }
    
    
    // Down sides of above solutions are that it has to look at every bit, even if it's not 1.
    // Following trick modifies n and increments count every time it loops. Also O(1).
    // Trick: Decrement the integer by 1. This removes one 1-bit. (10000001 to 10000000).
    // Then & with original, to remove potentially newly added 1-bits (10000001 & 10000000 = 10000000. No newly 1-bit was added anyways.)
    // (Loop again. Decrement 10000000 to 01111111. 10000000 & 01111111 = 00000000. Loop ends.)
    public int hammingWeightTrick(int n) {
        int count = 0;

        while (n != 0) {
            count++;
            n &= n - 1;
        }

        return count;
    }
    
}
