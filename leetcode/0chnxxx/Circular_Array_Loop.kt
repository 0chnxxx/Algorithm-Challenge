/**
 * You are playing a game involving a circular array of non-zero integers nums.
 *
 * Each nums[i] denotes the number of indices forward/backward you must move if you are located at index i:
 * If nums[i] is positive, move nums[i] steps forward, and
 * If nums[i] is negative, move abs(nums[i]) steps backward.
 * Since the array is circular, you may assume that moving forward from the last element puts you on the first element, and moving backwards from the first element puts you on the last element.
 *
 * A cycle in the array consists of a sequence of indices seq of length k where:
 * Following the movement rules above results in the repeating index sequence seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * Every nums[seq[j]] is either all positive or all negative.
 * k > 1
 * Return true if there is a cycle in nums, or false otherwise.
 *
 * Constraints:
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 * nums[i] != 0
 *
 * Follow up:
 * Could you solve it in O(n) time complexity and O(1) extra space complexity?
 */

fun main() {
    val nums = intArrayOf(2, -1, 1, 2, 2)

    val result = Solution().circularArrayLoop(nums)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun circularArrayLoop(nums: IntArray): Boolean {
        val n = nums.size

        fun next(i: Int): Int {
            var ni = (i + nums[i]) % n

            if (ni < 0) ni += n

            return ni
        }

        for (i in 0 until n) {
            if (nums[i] == 0) continue

            val dir = nums[i] > 0
            var slow = i
            var fast = i

            while (true) {
                val ns = next(slow)

                if (nums[ns] == 0 || (nums[ns] > 0) != dir) break

                val nf1 = next(fast)

                if (nums[nf1] == 0 || (nums[nf1] > 0) != dir) break

                val nf2 = next(nf1)

                if (nums[nf2] == 0 || (nums[nf2] > 0) != dir) break

                slow = ns
                fast = nf2

                if (slow == fast) {
                    if (slow == next(slow)) break

                    return true
                }
            }

            var currnet = i

            while (nums[currnet] != 0 && (nums[currnet] > 0) == dir) {
                val nc = next(currnet)

                nums[currnet] = 0
                currnet = nc
            }
        }

        return false
    }
}
