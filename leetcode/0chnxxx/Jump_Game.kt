/**
 * You are given an integer array nums.
 * You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 105
 */

fun main() {
    val nums = intArrayOf(3, 2, 1, 0, 4)

    val solution = Solution().canJump(nums)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun canJump(nums: IntArray): Boolean {
        var maxDistance = 0

        // 각 위치를 순회하면서 도달 가능한 최대 거리를 구함
        for (i in nums.indices) {
            // 여태까지 도달 가능한 최대 거리보다 인덱스가 앞섰다면 해당 위치 이상으로 도달할 수 없다는 것으로 판단
            if (i > maxDistance) return false

            // 현재 위치에서 도달 가능한 최대 거리
            // i를 더하는 이유는 현재 위치까지 오기까지의 거리를 포함하기 위함
            val currentMaxDistance = i + nums[i]

            // 도달 가능한 최대 거리 갱신
            maxDistance = maxOf(maxDistance, currentMaxDistance)
        }

        // 끝까지 순회했다면 마지막 위치까지 도달 가능한 것으로 판단
        return true
    }
}
