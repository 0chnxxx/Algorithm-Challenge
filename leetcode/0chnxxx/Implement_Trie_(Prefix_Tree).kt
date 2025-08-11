/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
 * There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 * Constraints:
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 10^4 calls in total will be made to insert, search, and startsWith.
 */

fun main() {
    val trie = Trie();
    trie.insert("apple");
    trie.search("apple");   // return True
    trie.search("app");     // return False
    trie.startsWith("app");        // return True
    trie.insert("app");
    trie.search("app");     // return True
}

class TrieNode {
    val children = HashMap<Char, TrieNode>()
    var isEnd = false
}

class Trie() {
    private val root = TrieNode()

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun insert(word: String) {
        var node = root

        for (char in word) {
            if (char !in node.children) {
                node.children[char] = TrieNode()
            }

            node = node.children[char]!!
        }

        node.isEnd = true
    }

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun search(word: String): Boolean {
        val node = searchNode(word)

        return node != null && node.isEnd
    }

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun startsWith(prefix: String): Boolean {
        return searchNode(prefix) != null
    }

    private fun searchNode(s: String): TrieNode? {
        var node = root

        for (char in s) {
            if (char !in node.children) return null

            node = node.children[char]!!
        }

        return node
    }
}