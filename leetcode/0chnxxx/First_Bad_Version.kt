/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad.
 * Implement a function to find the first bad version.
 * You should minimize the number of calls to the API.
 *
 * Constraints:
 * 1 <= bad <= n <= 2^31 - 1
 */

fun main() {
    val n = 5

    val result = Solution().firstBadVersion(n)

    println(result)
}

abstract class VersionControl {
    private val bad = 4

    fun isBadVersion(version: Int): Boolean {
        return version >= bad
    }

    abstract fun firstBadVersion(n: Int): Int
}

class Solution: VersionControl() {
    // 시간 복잡도 : O(log N)
    // 공간 복잡도 : O(1)
    override fun firstBadVersion(n: Int) : Int {
        var left = 1
        var right = n

        while (left < right) {
            val mid = left + (right - left) / 2

            if (isBadVersion(mid)) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        return left
    }
}