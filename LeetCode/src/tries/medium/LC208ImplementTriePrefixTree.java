package tries.medium;

import java.util.HashMap;

// A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
// There are various applications of this data structure, such as autocomplete and spellchecker.
public class LC208ImplementTriePrefixTree {
    // Trie is a tree data structure where each node represents a lowercase alphabet.
    // Each node can have upto 26 children (26 lowercases).
    // Implementation can be done by: each node having an array or a hashmap to represents its children.
    // Words are stored by traversing from root to children, marking endOfWord if the node is the last char for a word.
    // Below classes are the implementations.
}

class TrieNode {
    // Instead of having an array of 26 children, use hashmap.
    // To add children for the node: node.children.put(char, TireNode()); Existence of node for char-key means having a child(char).
    // (If using an array, index can be used to represent each alphabet, or each node can have a alphabet as a property.)
    public HashMap<Character, TrieNode> children = new HashMap<>();
    public boolean endOfWord = false;  // Indicates if this node is the last char for a word.
}

class Trie {
    // root is an empty node that has upto 26 first characters (children), represented by hashMap.
    TrieNode root = new TrieNode();

    public Trie() {

    }
    
    // From the first char, check if char is in the children.
    // If so, advance to that child. Otherwise add as new child then advance to newly created child.
    // Make sure to mark endOfWord when done.
    public void insert(String word) {
        TrieNode curr = root;

        for (char c : word.toCharArray()) {  // Loop for each character in given word.
            if (!curr.children.containsKey(c)) {  // If there is no child with this char, put it as new node.
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);  // Traverse to next node.
        }

        curr.endOfWord = true;  // When done, set endOfWord for the last node (may not be structurally the last, but the last node for this given word).
    }
    
    // search() & startsWith() method are similar to insert().
    public boolean search(String word) {
        TrieNode curr = root;
        
        for (char c : word.toCharArray()) {  // Loop for each character in given word.
            if (!curr.children.containsKey(c)) {  // If there is no child with this char, return false.
                return false;
            }
            curr = curr.children.get(c);  // Traverse to next node.
        }

        return curr.endOfWord;  // If this node is not marked as the end of the word, then we do not have that word, but only have words that start with it.
    }
    
    // startsWith() functionality is the reason for Trie structure. For search only, we could simply use HashSet.
    public boolean startsWith(String prefix) {
        TrieNode curr = root;

        for (char c : prefix.toCharArray()) {  // Loop for each character in given prefix.
            if (!curr.children.containsKey(c)) {  // If there is no child with this char, return false.
                return false;
            }
            curr = curr.children.get(c);  // Traverse to next node.
        }

        return true;
    }
}
