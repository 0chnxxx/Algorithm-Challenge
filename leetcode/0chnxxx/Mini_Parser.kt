/**
 * Given a string s represents the serialization of a nested list, implement a parser to deserialize it and return the deserialized NestedInteger.
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 *
 * Constraints:
 * 1 <= s.length <= 5 * 10^4
 * s consists of digits, square brackets "[]", negative sign '-', and commas ','.
 * s is the serialization of valid NestedInteger.
 * All the values in the input are in the range [-10^6, 10^6].
 */

fun main() {
    val s = "324"

    val result = Solution().deserialize(s)

    println(result)
}

class NestedInteger {
    private var value: Int? = null
    private var list: MutableList<NestedInteger>? = null

    // Constructor initializes an empty nested list.
    constructor()

    // Constructor initializes a single integer.
    constructor(value: Int) {
        this.value = value
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    fun isInteger(): Boolean {
        return value != null
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    fun getInteger(): Int? {
        return value
    }

    // Set this NestedInteger to hold a single integer.
    fun setInteger(value: Int) {
        this.value = value
        this.list = null
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    fun add(ni: NestedInteger) {
        if (list == null) {
            list = mutableListOf()
        }

        list!!.add(ni)
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    fun getList(): List<NestedInteger>? {
        return list
    }
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun deserialize(s: String): NestedInteger {
        if (!s.startsWith("[")) {
            return NestedInteger(s.toInt())
        }

        val stack = ArrayDeque<NestedInteger>()
        var num = 0
        var negative = false
        var hasNum = false

        for (i in s.indices) {
            when (val ch = s[i]) {
                '[' -> {
                    stack.addLast(NestedInteger())
                }
                ']' -> {
                    if (hasNum) {
                        val ni = NestedInteger(if (negative) -num else num)

                        stack.last().add(ni)
                    }

                    val last = stack.removeLast()

                    if (stack.isNotEmpty()) {
                        stack.last().add(last)
                    } else {
                        return last
                    }

                    num = 0
                    negative = false
                    hasNum = false
                }
                ',' -> {
                    if (hasNum) {
                        val ni = NestedInteger(if (negative) -num else num)

                        stack.last().add(ni)

                        num = 0
                        negative = false
                        hasNum = false
                    }
                }
                '-' -> {
                    negative = true
                }
                in '0'..'9' -> {
                    num = num * 10 + (ch - '0')
                    hasNum = true
                }
            }
        }

        return NestedInteger(if (negative) -num else num)
    }
}
