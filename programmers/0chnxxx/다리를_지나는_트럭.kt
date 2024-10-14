import java.util.*

/**
 * 트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 한다.
 * 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 한다.
 * 
 * 다리에는 트럭이 최대 bridge_length대 올라갈 수 있다.
 * 다리는 weight 이하까지의 무게를 견딜 수 있다.
 * truck_weight 배열 순서대로 해당 무게를 가진 트럭이 다리를 올라간다.
 * 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시한다.
 * 
 * 1 <= bridge_length <= 10000
 * 1 <= weight <= 10000
 * 1 <= truck_weights.length() <= 10000
 * 1 <= truck_weights[i] <= weight
 */

class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        val bridge = LinkedList<Int>()
        val positions = LinkedList<Int>()

        var currentWeight = 0
        var time = 0

        // 모든 트럭을 순회
        for (truck in truck_weights) {
            // 각 트럭마다 다리를 건넌다.
            while(true) {
                time++

                // 맨 앞의 트럭이 다리를 건넜으면 다리에서 제거
                if (positions.isNotEmpty() && positions.peek() == bridge_length) {
                    currentWeight -= bridge.poll()
                    positions.poll()
                }

                // 다리 위의 모든 트럭을 한 칸씩 앞으로 이동
                for (i in 0 until positions.size) {
                    positions[i] += 1
                }

                // 다리 무게에 여유가 있다면 새로운 트럭 추가
                if (currentWeight + truck <= weight) {
                    bridge.add(truck)
                    positions.add(1)
                    currentWeight += truck
                    break
                }
            }
        }

        return time + bridge_length
    }
}

fun main(args: Array<String>) {
    val bridgeLength = 2
    val weight = 10
    val truckWeights = intArrayOf(7, 4, 5, 6)
    
    val answer = Solution().solution(bridgeLength, weight, truckWeights)
    
    println(answer)
}
