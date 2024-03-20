package twoPointers.easy;

// It is a palindrome if it reads the same forward and backward after removing all non-alphanumeric, ignore case difference.
// Given a string s, return true if it is a palindrome, or false otherwise.
// s consists only of printable ASCII characters.
public class LC125ValidPalindrome {
    
    // Increment left & decrement right pointers until both letters are alphanumeric and they still do not meet/cross.
    public boolean isPalindrome(String s) {
        
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {  // Set i = first letter, j = last letter.

            while (!Character.isLetter(s.charAt(i)) && !Character.isDigit(s.charAt(i)) && i < j) {
                i++;
            }
            while (!Character.isLetter(s.charAt(j)) && !Character.isDigit(s.charAt(j)) && i < j) {  // If i = j after i++ above, j-- do not happen.
                j--;
            }

            // At this point, either i = j (could be alphanumeric or not), or both letters are alphanumeric.
            // "i < j condition" also ensures indexing within string boundary.
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
        }

        return true;
    }
    
    
    // Similar solution using while loop that I've done before.
    // Solution without extra memory
    // Increment & Decrement left & right pointer until it's alphanumeric, then compare
    public static boolean isPalindromeNoExtraMemory(String s) {
        // Compare until left & right pointer do not cross or meet.
        // (This way we do not need to check whether s.length is even or odd)
        // (Nor need to reverse the string than compare from beginning to end)
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            // Increment left pointer until it's alphanumeric, while making sure index is not out of boundary
            while (left < right && !(Character.isLetter(s.charAt(left)) || Character.isDigit(s.charAt(left))) ) {
                left++;
            }
            // Decrement right pointer until it's alphanumeric, while making sure index is not out of boundary
            while (left < right && !(Character.isLetter(s.charAt(right)) || Character.isDigit(s.charAt(right))) ) {
                right--;
            }
            
            // If still left < right, compare
            if (left < right && Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) {
                return false;
            }
        }
        
        return true;
    }
    
    
    // Solution with extra memory for creating new string without spaces & non-alphanumeric characters
    public static boolean isPalindromeExtraMemory(String s) {
        String newS = "";
        
        // new string with spaces & non-alphanumeric removed
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c)) {
                newS += Character.toLowerCase(c);
            }
        }
        
        // Compare until left & right pointer do not cross or meet.
        // (This way we do not need to check whether s.length is even or odd)
        // (Nor need to reverse the string than compare from beginning to end)
        int left = 0;
        int right = newS.length() - 1;
        while (left < right) {
            if (newS.charAt(left++) != newS.charAt(right--)) {
                return false;
            }
        }
        
        return true;
    }
    
}
