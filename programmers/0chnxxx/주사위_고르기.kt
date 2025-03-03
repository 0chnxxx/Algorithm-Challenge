/**
 * A와 B가 n개의 주사위를 가지고 승부를 합니다.
 * 주사위의 6개 면에 각각 하나의 수가 쓰여 있으며, 주사위를 던졌을 때 각 면이 나올 확률은 동일합니다.
 * 각 주사위는 1~n의 번호를 가지고 있으며, 주사위에 쓰인 수의 구성은 모두 다릅니다.
 *
 * A가 먼저 n / 2개의 주사위를 가져가면 B가 남은 n / 2개의 주사위를 가져갑니다.
 * 각각 가져간 주사위를 모두 굴린 뒤, 나온 수들을 모두 합해 점수를 계산합니다.
 * 점수가 더 큰 쪽이 승리하며, 점수가 같다면 무승부입니다.
 *
 * A는 자신이 승리할 확률이 가장 높아지도록 주사위를 가져가려 합니다.
 * 주사위에 쓰인 수의 구성을 담은 2차원 정수 배열 dice가 매개변수로 주어집니다.
 * 이때, 자신이 승리할 확률이 가장 높아지기 위해 A가 골라야 하는 주사위 번호를 오름차순으로 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해 주세요.
 * 승리할 확률이 가장 높은 주사위 조합이 유일한 경우만 주어집니다.
 *
 * 2 <= dice의 길이 = n <= 10
 * n은 2의 배수입니다.
 * dice[i]는 i + 1번 주사위에 쓰인 6개의 수를 담고 있습니다.
 * dice[i]의 길이 = 6
 * 1 <= dice[i]의 원소 <= 100
 */

fun main() {
    val dice = arrayOf(
        intArrayOf(1, 2, 3, 4, 5, 6),
        intArrayOf(3, 3, 3, 3, 4, 4),
        intArrayOf(1, 3, 3, 4, 4, 4),
        intArrayOf(1, 1, 4, 4, 5, 5)
    )

    val solution = Solution().solution(dice)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(dice: Array<IntArray>): IntArray {
        val combinations = generateCombinations(dice.indices.toList(), dice.size / 2)

        var bestCombination = emptyList<Int>()
        var maxWin = -1L

        for (combination in combinations) {
            val a = combination.map { dice[it] }
            val b = (dice.indices - combination.toSet()).map { dice[it] }

            val aScores = generateScores(a).sorted()
            val bScores = generateScores(b).sorted()

            var win = 0L

            for (aScore in aScores) {
                win += binarySearch(aScore, bScores)
            }

            if (win > maxWin) {
                maxWin = win
                bestCombination = combination
            }
        }

        return bestCombination.map { it + 1 }.toIntArray()
    }

    fun generateCombinations(arr: List<Int>, k: Int): MutableList<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun backtrack(start: Int, current: MutableList<Int>) {
            if (current.size == k) {
                result.add(ArrayList(current))
                return
            }

            for (i in start until arr.size) {
                current.add(arr[i])
                backtrack(i + 1, current)
                current.removeAt(current.lastIndex)
            }
        }

        backtrack(0, mutableListOf())

        return result
    }

    fun generateScores(arr: List<IntArray>): List<Int> {
        val result = mutableListOf<Int>()

        fun dfs(index: Int, sum: Int) {
            if (arr.size == index) {
                result.add(sum)
                return
            }

            for (number in arr[index]) {
                dfs(index + 1, sum + number)
            }
        }

        dfs(0, 0)

        return result
    }

    fun binarySearch(target: Int, case: List<Int>): Int {
        var low = 0
        var high = case.size - 1

        while (low <= high) {
            val mid = (low + high) / 2

            if (case[mid] < target) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        return low
    }
}
