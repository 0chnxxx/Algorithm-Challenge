/**
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station.
 * You begin the journey with an empty tank at one of the gas stations.
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 * If there exists a solution, it is guaranteed to be unique.
 *
 * Constraints:
 * n == gas.length == cost.length
 * 1 <= n <= 10^5
 * 0 <= gas[i], cost[i] <= 10^4
 * The input is generated such that the answer is unique.
 */

fun main() {
    val gas = intArrayOf(1, 2, 3, 4, 5)
    val cost = intArrayOf(3, 4, 5, 1, 2)

    val result = Solution().canCompleteCircuit(gas, cost)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        var startIndex = 0
        var totalTank = 0
        var currentTank = 0

        // 모든 station을 순회
        for (i in 0 until gas.size) {
            // gas - cost 만큼 연료 주입
            totalTank += gas[i] - cost[i]
            currentTank += gas[i] - cost[i]

            // 연료가 부족한 경우엔 다음 station에서 시작하도록 초기화
            if (currentTank < 0) {
                startIndex = i + 1
                currentTank = 0
            }
        }

        // totalTank는 음수 연료 주입도 누적합되기 때문에
        // 0보다 크거나 같다는 것은 한바퀴 순환이 가능하다는 것
        if (totalTank >= 0) {
            return startIndex
        } else {
            return  -1
        }
    }
}