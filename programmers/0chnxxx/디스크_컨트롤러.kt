import java.util.PriorityQueue

/**
 * 하드디스크는 한 번에 하나의 작업만 수행할 수 있습니다.
 * 디스크 컨트롤러를 구현하는 방법은 여러 가지가 있습니다.
 * 이 문제에서는 우선순위 디스크 컨트롤러라는 가상의 장치를 이용한다고 가정합니다.
 *
 * 우선순위 디스크 컨트롤러는 다음과 같이 동작합니다.
 * 1. 어떤 작업 요청이 들어왔을 때 작업의 번호, 작업의 요청 시각, 작업의 소요 시간을 저장해 두는 대기 큐가 있습니다. (처음에 이 큐는 비어있습니다.)
 * 2. 디스크 컨트롤러는 하드디스크가 작업을 하고 있지 않고 대기 큐가 비어있지 않다면 가장 우선순위가 높은 작업을 대기 큐에서 꺼내서 하드디스크에 그 작업을 시킵니다.
 * 이때, 작업의 소요시간이 짧은 것, 작업의 요청 시각이 빠른 것, 작업의 번호가 작은 것 순으로 우선순위가 높습니다.
 * 3. 하드디스크는 작업을 한 번 시작하면 작업을 마칠 때까지 그 작업만 수행합니다.
 * 4. 하드디스크가 어떤 작업을 마치는 시점과 다른 작업 요청이 들어오는 시점이 겹친다면
 * 하드디스크가 작업을 마치자마자 디스크 컨트롤러는 요청이 들어온 작업을 대기 큐에 저장한 뒤 우선순위가 높은 작업을 대기 큐에서 꺼내서 하드디스크에 그 작업을 시킵니다.
 * 또, 하드디스크가 어떤 작업을 마치는 시점에 다른 작업이 들어오지 않더라도 그 작업을 마치자마자 또 다른 작업을 시작할 수 있습니다.
 * 이 과정에서 걸리는 시간은 없다고 가정합니다.
 *
 * 각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간] 을 담은 2차원 정수 배열 jobs가 매개변수로 주어질 때
 * 우선순위 디스크 컨트롤러가 이 작업을 처리했을 때 모든 요청 작업의 반환 시간의 평균의 정수부분을 return 하는 solution 함수를 작성해 주세요.
 *
 * 1 <= jobs의 길이 <= 500
 * jobs[i] = [s, l]
 * 0 <= s <= 1000
 * 1 <= l <= 1000
 */

fun main() {
    val jobs = arrayOf(
        intArrayOf(0, 3),
        intArrayOf(1, 9),
        intArrayOf(3, 5)
    )

    val solution = Solution().solution(jobs)

    println(solution)
}

class Solution {
    fun solution(jobs: Array<IntArray>): Int {
        // 최초 작업을 찾기 위해 요청 시간 기준 오름차순 정렬
        jobs.sortBy { it[0] }

        // 대기 큐는 소요 시간 기준 오름차순 정렬, 동일한 소요 시간은 요청 시간 기준 오름차순 정렬
        val queue = PriorityQueue(compareBy<IntArray> { it[1] }.thenBy { it[0] })

        // 요청 순서를 보장하기 위한 index
        var index = 0

        // 작업 시간을 계산하기 위한 time, totalTime
        var time = 0
        var totalTime = 0

        // 모든 작업을 처리하기 위한 루프
        while (queue.isNotEmpty() || index < jobs.size) {
            // 이미 작업을 처리 중일 때 대기하고 있던 작업을 큐에 넣기 위한 루프
            while (index < jobs.size && jobs[index][0] <= time) {
                queue.offer(jobs[index])
                index++
            }

            // 대기 큐에 있는 작업을 꺼내서 처리
            if (queue.isNotEmpty()) {
                val job = queue.poll()

                time += job[1]
                totalTime += (time - job[0])
            // 최초 작업과 0ms의 간격을 줄이기 위한 부분
            } else {
                time = jobs[index][0]
            }
        }

        return totalTime / jobs.size
    }
}
