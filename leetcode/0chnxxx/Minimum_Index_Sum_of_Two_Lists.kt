/**
 * Given two arrays of strings list1 and list2, find the common strings with the least index sum.
 * A common string is a string that appeared in both list1 and list2.
 * A common string with the least index sum is a common string such that if it appeared at list1[i] and list2[j] then i + j should be the minimum value among all the other common strings.
 * Return all the common strings with the least index sum. Return the answer in any order.
 *
 * Constraints:
 * 1 <= list1.length, list2.length <= 1000
 * 1 <= list1[i].length, list2[i].length <= 30
 * list1[i] and list2[i] consist of spaces ' ' and English letters.
 * All the strings of list1 are unique.
 * All the strings of list2 are unique.
 * There is at least a common string between list1 and list2.
 */

fun main() {
    val list1 = arrayOf(
        "Shogun",
        "Tapioca Express",
        "Burger King",
        "KFS"
    )
    val list2 = arrayOf(
        "Piatti",
        "The Grill at Torrey Pines",
        "Hungry Hunter Steakhouse",
        "Shogun"
    )

    val result = Solution().findRestaurant(list1, list2)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N + M)
    // 공간 복잡도 : O(N)
    fun findRestaurant(list1: Array<String>, list2: Array<String>): Array<String> {
        val map = mutableMapOf<String, Int>()

        for (i in list1.indices) {
            map[list1[i]] = i
        }

        var minSum = Int.MAX_VALUE
        val result = mutableListOf<String>()

        for (j in list2.indices) {
            val str = list2[j]

            if (map.containsKey(str)) {
                val sum = j + map[str]!!

                when {
                    sum < minSum -> {
                        minSum = sum
                        result.clear()
                        result.add(str)
                    }
                    sum == minSum -> {
                        result.add(str)
                    }
                }
            }
        }

        return result.toTypedArray()
    }
}