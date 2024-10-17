import java.util.*

class Solution {
    fun simplifyPath(path: String): String {
        var result = "/"

        val stack = Stack<String>()
        val reversed = path.split("/").reversed()
        reversed.forEach { stack.push(it) }

        var resultWords = mutableListOf<String>()

        while (stack.isNotEmpty()) {
            val word = stack.pop()

            if (word == "") {
                continue
            }

            if (word == ".") {
                continue
            }

            if (word == "..") {
                resultWords.removeLastOrNull()
                continue
            }

            resultWords.add(word)
        }

        result += resultWords.joinToString("/")

        return result
    }
}

fun main(args: Array<String>) {
//    val path = "/.../a/../b/c/../d/./"
    val path = "/a/./b/../../c/"

    val result = Solution().simplifyPath(path)

    println(result)
}
