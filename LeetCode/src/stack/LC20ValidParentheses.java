package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

/*
 * LeetCode 20. Valid Parentheses.
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * 1 <= s.length
 * s consists of parentheses only '()[]{}'.
 */
public class LC20ValidParentheses {
    
    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("("));
        System.out.println(isValid(")"));
        System.out.println(isValid("[)"));
        System.out.println(isValid("()[]{}"));
    }
    
    // Iterate over string. If open bracket, push to stack. If closing bracket, pop latest open bracket and see if it's match.
    // Edge cases: If stack is empty when to pop, return false. If stack remains after iteration, return false.
    public static boolean isValid(String s) {
        
        // Maps closing bracket to corresponding opening bracket
        // (Alternative is to write multiple if statements for each closing bracket)
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        
        // Set of opening brackets
        // Alternative is to write '(' || '[' || '{' or check if not key of HashMap
        HashSet<Character> openBrackets = new HashSet<>();
        openBrackets.add('(');
        openBrackets.add('[');
        openBrackets.add('{');
        
        // Stack to contain opening brackets
        Deque<Character> deq = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {  // iterate over String
            // if (openBrackets.contains(c)) {
            if (!map.keySet().contains(c)) {  // if opening bracket, push
                deq.addLast(c);
            }
            else {  // if closing bracket, pop
                if (deq.size() == 0) {  // there was no preceding opening bracket
                    return false;
                }
                
                char popped = deq.pollLast();  // popped opening bracket must match map.get(closingBracket)
                if (popped != map.get(c)) {
                    return false;
                }
            }
        }
        
        return deq.size() == 0;  // must have no remaining opening bracket
    }
}
