/**
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 *
 * For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
 * More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container.
 * If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 *
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 *
 * The replacement must be in place and use only constant extra memory.
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */

fun main() {
    val nums = intArrayOf(6, 7, 5, 3, 5, 6, 2, 9, 1, 2, 7, 0, 9)

    val solution = Solution().nextPermutation(nums)

    println(solution)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun nextPermutation(nums: IntArray): Unit {
        val n = nums.size
        var i = n - 2

        // 뒤에서 2자리씩 탐색하면서 숫자가 감소하는 지점을 찾음
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--
        }

        // i가 0 이상이면 지점을 찾은걸로 판단
        if (i >= 0) {
            var j = n - 1

            // 가장 마지막 숫자부터 i보다 큰 값을 탐색
            while (nums[j] <= nums[i]) {
                j--
            }

            // swap
            nums[i] = nums[j].also { nums[j] = nums[i] }
        }

        // i 이후 숫자들을 오름차순으로 정렬함으로써 swap을 통해 만든 수열이 nums의 다음 수열이 되게끔 함
        // i 이후 숫자들은 swap으로 인해 내림차순 상태가 됐기 때문에 단순히 i 이후 숫자들을 swap하기만해도 오름차순 정렬이 됨
        fun reverse(start: Int, end: Int) {
            var left = start
            var right = end

            while (left < right) {
                nums[left] = nums[right].also { nums[right] = nums[left] }
                left++
                right--
            }
        }

        reverse(i + 1, n - 1)
    }

//    // 시간 복잡도 : O(N! log N!)
//    // 공간 복잡도 : O(N!)
//    fun nextPermutation(nums: IntArray): Unit {
//        val permutations = mutableSetOf<List<Int>>()
//        val visited = BooleanArray(nums.size)
//
//        fun backtrack(current: MutableList<Int>) {
//            if (current.size == nums.size) {
//                permutations.add(ArrayList(current))
//                return
//            }
//
//            for (i in nums.indices) {
//                if (!visited[i]) {
//                    visited[i] = true
//                    current.add(nums[i])
//                    backtrack(current)
//                    current.removeAt(current.size - 1)
//                    visited[i] = false
//                }
//            }
//        }
//
//        backtrack(mutableListOf())
//
//        val sortedPermutations = permutations
//            .map { it.joinToString("") }
//            .sortedBy { it.toInt() }
//
//        val num = nums.joinToString("")
//        val index = sortedPermutations.indexOfFirst { it == num }
//
//        val nextPermutation = if (index == sortedPermutations.size - 1) {
//            sortedPermutations[0].map { it.digitToInt() }.toList()
//        } else {
//            sortedPermutations[index + 1].map { it.digitToInt() }.toList()
//        }
//
//        for (i in nums.indices) {
//            nums[i] = nextPermutation[i]
//        }
//    }
}
