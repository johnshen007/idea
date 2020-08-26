// java program to sort an array of strings
// using Trie

public class Sorting_Words_in_trie1 {

    // Alphabet size (# of symbols)
    static final int ALPHABET_SIZE = 26;

    // Trie node
    static class TrieNode1 {
        TrieNode1[] children = new TrieNode1[ALPHABET_SIZE];
        // isLeaf is true if the node represents
        // end of a word
        boolean isLeaf;

        TrieNode1() {
            isLeaf = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    }

    static TrieNode1 root;

    // If not present, inserts key into trie
    // If the key is prefix of trie node, just
    // marks leaf node
    static void insert(String key) {
        int length = key.length();

        TrieNode1 pCrawl = root;

        for (int level = 0; level < length; level++) {
            int index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode1();

            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.isLeaf = true;
    }

    /* function for print traversal in cording to alphabet(按首字母顺序打印)*/
    static void printSorted(TrieNode1 node) {
        if (node == null)
            return ;

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (node.children[i] != null) {
                System.out.print((char)('a'+i));
                printSorted(node.children[i]);
            }
        }
        if (node.isLeaf) {
            System.out.println("");
            return;
        }
    }


    // Driver code
    public static void main(String args[]) {
        String arr[] = {"abc", "xy", "bcd"};
        root = new TrieNode1();
        for (int i = 0; i <arr.length ; i++) {
            insert(arr[i]);
        }
        printSorted(root);
    }
}