package twoPointers.hard;

// Given a string s, return the last substring of s in lexicographical order.
// Input: s = "abab"
// Output: "bab"
// Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"].
// The lexicographically maximum (i.e. the latest) substring is "bab".
public class LC1163LastSubstringInLexicographicalOrder {
    
    // lastSubstring is lexicographically (dictionary-wise) the last string among all the substrings of given string.
    // EX: String = zazbz, then lastSubstring = zbz. Not za, Not zazbz, Not z.
    // Notice also "z then anything appended" is later than "z" itself. This means the lastSubstring is always a suffix of given string.
    // This means we can iterate from the last index. See if it's latest char and if so set it as idx0.
    // At end of iteration, the answer is s.substring(idx0).
    // The concern is when we have duplicate lastest character. Then the idx1 should be the lastest, then idx2 and so forth.
    // Compare next characters of current idx0 and newly found candidate. Update idx0 if necessary.
    public String lastSubstring(String s) {
        int idx0 = s.length() - 1;  // idx0 is the starting index of lastSubstring.
        
        // From 2nd-to-last index to 1st index of given string, compare with idx0.
        for (int curIdx = s.length() - 2; curIdx >= 0; curIdx--) {
            if (s.charAt(curIdx) > s.charAt(idx0)) {  // If curIdx is later than idx0, curIdx should be idx0.
                idx0 = curIdx;
            }
            else if (s.charAt(curIdx) == s.charAt(idx0)) {  // If duplicate found, compare next characters.
                int i = curIdx + 1;  // i is next char of curIdx.
                int j = idx0 + 1;  // j is next char of idx0.
                while (i < idx0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                    i++;  // Increment i & j as long as next chars are the same.
                    j++;  // No need to go further if i go past idx0, or j go past length. Means newly found should be new idx0.
                }         // (as cbacba... is later than cba)
                if (i == idx0 || j == s.length() || s.charAt(i) > s.charAt(j)) {  // also if s.charAt(i) > s.charAt(j)
                    // cbacbac > cbac || cbaacba > cba || cbaccbaa > cbaa
                    idx0 = curIdx;
                }
            }
        }
        
        return s.substring(idx0);
    }
    
}
