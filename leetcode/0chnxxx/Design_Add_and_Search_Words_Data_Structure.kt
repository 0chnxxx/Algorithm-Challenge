/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 * word may contain dots '.' where dots can be matched with any letter.
 *
 * Constraints:
 * 1 <= word.length <= 25
 * word in addWord consists of lowercase English letters.
 * word in search consist of '.' or lowercase English letters.
 * There will be at most 2 dots in word for search queries.
 * At most 104 calls will be made to addWord and search.
 */

fun main() {
    val wordDictionary = WordDictionary();

    wordDictionary.addWord("bad")
    wordDictionary.addWord("dad")
    wordDictionary.addWord("mad")
    println(wordDictionary.search("pad")) // return False
    println(wordDictionary.search("bad")) // return True
    println(wordDictionary.search(".ad")) // return True
    println(wordDictionary.search("b..")) // return True
}

class WordDictionary() {
    class TrieNode {
        val children = mutableMapOf<Char, TrieNode>()
        var isEnd = false
    }

    val root = TrieNode()

    // 시간 복잡도 : O(L)
    // 공간 복잡도 : O(N * L)
    fun addWord(word: String) {
        var node = root

        for (c in word) {
            node = node.children.computeIfAbsent(c) { TrieNode() }
        }

        node.isEnd = true
    }

    // 시간 복잡도 : O(26^d * L)
    // 공간 복잡도 : O(L)
    fun search(word: String): Boolean {
        return dfs(word, 0, root)
    }

    private fun dfs(word: String, index: Int, node: TrieNode): Boolean {
        if (index == word.length) return node.isEnd

        val c = word[index]

        return if (c == '.') {
            for (child in node.children.values) {
                if (dfs(word, index + 1, child)) return true
            }

            false
        } else {
            val child = node.children[c] ?: return false

            dfs(word, index + 1, child)
        }
    }
}