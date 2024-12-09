/**
 * 구름이는 각각 일정 주기로 작업을 반복하는 시스템을 운영하고 있다.
 * 그러나 구름이는 자주 일정을 놓쳐 여러 작업이 동시에 시작되는 시점을 알기 어렵다.
 * 구름이가 운영하는 시스템의 주기는 각각 A, B, C이다.
 * 세 작업이 동시에 시작하는 최소 주기를 계산한다.
 *
 * 첫번째 줄에 세 개의 숫자 A, B, C가 공백을 두고 입력
 *
 * 1 <= A, B, C <= 5000
 */

fun main(args: Array<String>) {
    val (a, b, c) = readLine()!!.split(" ").map{ it.toLong() }

    fun gcd(x: Long, y: Long): Long {
        return if (y == 0L) {
            x
        } else {
            gcd(y, x % y)
        }
    }

    fun lcm(x: Long, y: Long): Long {
        return (x * y) / gcd(x, y)
    }

    println(lcm(lcm(a, b), c))
}
