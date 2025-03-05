/**
 * 가상의 배틀로얄 카 레이싱 대회 Dead or Arrive에 출전한 팀 모비스는 이 대회를 완주하기 위해 가장 안전한 차량을 만들려고 한다.
 * 79GHz 코너 레이더와 ADAS 센서를 설치해 장애물과 경쟁자 차량을 피해 안전하게 결승선까지 도달할 수 있도록 하였다.
 *
 * 하지만 팀 모비스의 경쟁팀인 팀 Mars Killaz는 DOA 대회에서 모든 반칙이 허용되는 만큼 n대의 차량을 풀어 팀 모비스의 차량을 추격하기로 한다.
 * 수소연료전지 파워팩을 부착하고 있는 팀 모비스의 차량은 Mars Killaz의 차량을 모두 피해갔지만, Mars Killaz의 차량은 서로 부딪쳐 아수라장이 된 채로 결승선에 도착했다.
 * 대회가 종료되고, Mark Killaz는 모든 차량이 결승선에 도착하지 못했다는 사실을 발견했다.
 * 아수라장 속에서 살아 남은 차량을 찾아 실험을 하기로 했다.
 * Mark Killaz의 차량은 각 차량마다 v의 속도와 w의 내구도를 가지고 있다.
 *
 * 때문에 차량들의 서로 다른 속도의 차량은 부딪침없이 결승선에 들어올 수 있다.
 * 그러나 속도가 같은 차량이 여러 대가 있다면, 그 중 내구도가 가장 높은 차량만 결승선에 들어올 수 있다.
 * 속도와 내구도가 모두 같은 차량도 여러 대가 존재한다면, 그 중 차량 번호가 가장 높은 차량만 결승선에 들어올 수 있다.
 *
 * Mars Killaz의 차량 중 결승선에 들어온 차량의 번호의 합을 출력하시오.
 *
 * 1 <= n <= 200000
 * 1 <= v <= 1000
 * 1 <= w <= 1000000000
 */

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val cars = mutableMapOf<Int, Pair<Int, Int>>()

    // O(n) 코드
    repeat(n) {
        val i = it + 1
        val (v, w) = readLine()!!.split(" ").map { value -> value.toInt() }

        cars[v] = cars[v]?.let { (number, durability) ->
            when {
                w > durability || (w == durability && i > number) -> i to w
                else -> number to durability
            }
        } ?: (i to w)
    }

    val result = cars.values.sumOf { it.first }

    println(result)
}

// O(n log n) 코드
//    repeat(n) {
//        val (v, w) = readLine()!!.split(" ").map { value -> value.toInt() }
//
//        cars.add(Triple(it + 1, v, w))
//    }
//
//    val sortedCars = cars.sortedWith(compareByDescending<Triple<Int, Int, Int>> { it.second }.thenByDescending { it.third }.thenByDescending { it.first })
//
//    val result = sortedCars.groupBy { it.second }.map { it -> it.value.first().first }.sum()
