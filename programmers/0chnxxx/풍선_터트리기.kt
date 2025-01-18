/**
 * 일렬로 나열된 n개의 풍선이 있습니다.
 * 모든 풍선에는 서로 다른 숫자가 써져 있습니다.
 * 당신은 다음 과정을 반복하면서 풍선들을 단 1개만 남을 때까지 계속 터트리려고 합니다.
 * 
 * 1. 임의의 인접한 두 풍선을 고른 뒤, 두 풍선 중 하나를 터트립니다.
 * 2. 터진 풍선으로 인해 풍선들 사이에 빈 공간이 생겼다면, 빈 공간이 없도록 풍선들을 중앙으로 밀착시킵니다.
 * 
 * 여기서 조건이 있습니다.
 * 인접한 두 풍선 중에서 번호가 더 작은 풍선을 터트리는 행위는 최대 1번만 할 수 있습니다.
 * 즉, 어떤 시점에서 인접한 두 풍선 중 번호가 더 작은 풍선을 터트렸다면, 그 이후에는 인접한 두 풍선을 고른 뒤 번호가 더 큰 풍선만을 터트릴 수 있습니다.
 * 
 * 당신은 어떤 풍선이 최후까지 남을 수 있는지 알아보고 싶습니다.
 * 위에 서술된 조건대로 풍선을 터트리다 보면, 어떤 풍선은 최후까지 남을 수도 있지만, 어떤 풍선은 무슨 수를 쓰더라도 마지막까지 남기는 것이 불가능할 수도 있습니다.
 * 
 * 일렬로 나열된 풍선들의 번호가 담긴 배열 a가 주어집니다.
 * 위에 서술된 규칙대로 풍선들을 1개만 남을 때까지 터트렸을 때 최후까지 남기는 것이 가능한 풍선들의 개수를 return 하도록 solution 함수를 완성해주세요.
 * 
 * 1 <= a 의 길이 <= 1000000
 * -1000000000 <= a[i] <= 1000000000
 * a[i] 는 i + 1번째 풍선에 써진 숫자를 의미합니다.
 */

fun main() {
    val a = intArrayOf(9, -1, -5)

    val solution = Solution().solution(a)

    println(solution)
}

class Solution {
    fun solution(a: IntArray): Int {
        val lefts = IntArray(a.size)
        val rights = IntArray(a.size)

        lefts[0] = a[0]

        for (i in 1 until a.size) {
            lefts[i] = minOf(lefts[i - 1], a[i])
        }

        rights[a.size - 1] = a[a.size - 1]

        for (i in a.size - 2 downTo 0) {
            rights[i] = minOf(rights[i + 1], a[i])
        }

        var count = 0

        for (i in a.indices) {
            if (a[i] <= lefts[i] || a[i] <= rights[i]) {
                count++
            }
        }

        return count
    }
}
