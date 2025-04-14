/**
 * You are given a 0-indexed array of integers nums of length n.
 * You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i]
 * i + j < n
 *
 * Return the minimum number of jumps to reach nums[n - 1].
 * The test cases are generated such that you can reach nums[n - 1].
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * It's guaranteed that you can reach nums[n - 1].
 */

fun main() {
    val nums = intArrayOf(2, 3, 1, 1, 4)

    val solution = Solution().jump(nums)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun jump(nums: IntArray): Int {
        var count = 0
        var current = 0
        var farthest = 0

        // 모든 숫자를 순회하되 마지막 위치는 도달만하면 되기 때문에 제외
        for (i in 0 until nums.lastIndex) {
            // 가장 멀리 뛸 수 있는 거리 계산
            farthest = maxOf(farthest, i + nums[i])

            // 현재 위치인 경우
            if (i == current) {
                count++ // 점프
                current = farthest // 점프 후 현재 위치 갱신
            }
        }

        return count
    }

//    // 시간 복잡도 : O(K^N)
//    // 공간 복잡도 : O(N)
//    fun jump(nums: IntArray): Int {
//        var result = Int.MAX_VALUE
//
//        fun dfs(current: Int, count: Int) {
//            if (current == nums.size - 1) {
//                result = minOf(result, count)
//                return
//            }
//
//            for (i in 1..nums[current]) {
//                if (current + i < nums.size) {
//                    dfs(current + i, count + 1)
//                }
//            }
//        }
//
//        dfs(0, 0)
//
//        return result
//    }
}
