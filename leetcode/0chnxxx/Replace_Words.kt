/**
 * In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word derivative.
 * For example, when the root "help" is followed by the word "ful", we can form a derivative "helpful".
 * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the derivatives in the sentence with the root forming it.
 * If a derivative can be replaced by more than one root, replace it with the root that has the shortest length.
 * Return the sentence after the replacement.
 *
 * Constraints:
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] consists of only lower-case letters.
 * 1 <= sentence.length <= 106
 * sentence consists of only lower-case letters and spaces.
 * The number of words in sentence is in the range [1, 1000]
 * The length of each word in sentence is in the range [1, 1000]
 * Every two consecutive words in sentence will be separated by exactly one space.
 * sentence does not have leading or trailing spaces.
 */

fun main() {
    val dictionary = listOf(
        "cat",
        "bat",
        "rat"
    )
    val sentence = "the cattle was rattled by the battery"

    val result = Solution().replaceWords(dictionary, sentence)

    println(result)
}

class Solution {
    class TrieNode {
        val children = HashMap<Char, TrieNode>()
        var isEnd = false
    }

    private val root = TrieNode()

    // 시간 복잡도 : O(dictionary.size() + sentence.length())
    // 공간 복잡도 : O(dictionary.size() + sentence.length())
    fun replaceWords(dictionary: List<String>, sentence: String): String {
        // 1. Trie 생성
        for (word in dictionary) {
            insert(word)
        }

        // 2. sentence 처리
        return sentence.split(" ")
            .joinToString(" ") { word ->
                findRoot(word)
            }
    }

    private fun insert(word: String) {
        var node = root
        for (c in word) {
            node = node.children.getOrPut(c) { TrieNode() }
        }
        node.isEnd = true
    }

    private fun findRoot(word: String): String {
        var node = root
        val sb = StringBuilder()

        for (c in word) {
            if (!node.children.containsKey(c)) {
                return word // root 없음
            }

            sb.append(c)
            node = node.children[c]!!

            if (node.isEnd) {
                return sb.toString() // 가장 짧은 root
            }
        }

        return word
    }
}