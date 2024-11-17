/**
 * 폴더폰의 자판은 최근의 휴대폰의 입력 방식과는 차이가 있다.
 * 폴더폰의 자판은 9개의 버튼으로 이루어져 있다.
 * 1. 버튼을 한 번 누르면 그 버튼에 해당하는 숫자를 입력할 수 있다.
 * 2. 버튼을 k번 누르면 그 버튼의 k - 1번째에 해당하는 문자를 입력할 수 있다.
 * 예를 들면 5번 버튼을 두 번 누르면 J가 입력된다.
 * 3. 그 버튼에 적혀있는 숫자와 문자의 개수보다 더 많이 버튼을 누르면 다시 숫자, 첫번째 문자, 두번째 문자, ... 순으로 입력되는 문자가 반복된다.
 * 예를 들어 6번 버튼을 다섯 번 누르면 6이 입력되고 여섯 번 누르면 M이 입력된다.
 *
 * 폴더폰의 숫자 버튼을 누른 순서가 주어진다.
 * 이 순서를 보고 원래 입력하려고 했던 문장을 유추해보자.
 * 조건을 만족하는 문장이 여러 가지라면 그 중 가장 짧은 문장을 출력한다.
 *
 * 첫번째 줄에 버튼을 누른 횟수 N 입력
 * 두번째 줄에 버튼을 누른 순서를 나타내는 문자열 S 입력
 *
 * 1 <= N <= 10^5
 * S는 1과 9 사이의 숫자로만 구성되어 있다.
 */

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val buttons = readLine()!!.split("").filter { it.isNotEmpty() }

    val map = mutableMapOf<Int, String>(
        1 to "1.,?!",
        2 to "2ABC",
        3 to "3DEF",
        4 to "4GHI",
        5 to "5JKL",
        6 to "6MNO",
        7 to "7PQRS",
        8 to "8TUV",
        9 to "9WXYZ"
    )

    var result = StringBuilder()
    var count = 0

    for (i in 0 until n) {
        if (i == n - 1 || buttons[i + 1] != buttons[i]) {
            count %= map[buttons[i].toInt()]!!.length
            result.append(map[buttons[i].toInt()]!![count])
            count = 0
        } else {
            count++
        }
    }

    println(result.toString())
}
