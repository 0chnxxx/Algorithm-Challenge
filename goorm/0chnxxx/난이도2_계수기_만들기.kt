/**
 * 각 자리마다 최댓값이 다른 숫자판을 가진 계수기
 * 1. 계수기는 N개의 자릿수를 표시할 수 있다.
 * 2. i번째 자리에는 0부터 Ai까지의 숫자를 표시할 수 있다.
 * 3. 버튼을 한 번 누르면 가장 오른쪽 자리의 숫자가 1 증가한다.
 * 3-1. 만약 어떤 자리의 숫자가 표시할 수 있는 최댓값보다 커진다면 숫자를 최솟값 0으로 초기화하고 바로 왼쪽 자리의 숫자를 1 증가시킨다.
 * 3-2. 가장 왼쪽 자리의 숫자가 최댓값을 넘어갈 때는 왼쪽으로 올림을 전파하지 않는다.
 *
 * 계수기 초기 상태가 주어질 때 K번 버튼을 누른 뒤의 계수기를 출력
 *
 * 첫번째 줄에 숫자판의 갯수 N 입력
 * 두번째 줄에 각 숫자판의 최댓값 입력
 * 세번째 줄에 각 숫자판의 초기값 입력
 * 네번째 줄에 버튼을 누르는 횟수 K 입력
 *
 * 1<= N <= 8
 * 0 <= Ai <= 9
 * 0 <= Bi <= Ai
 * 0 <= K <= 100000
 */

fun main(args: Array<String>) {
    val length = readLine()!!.toInt()
    val maximumValues = readLine()!!.split(" ").map { it.toInt() }
    val initialValues = readLine()!!.split(" ").map { it.toInt() }.toMutableList()
    val count = readLine()!!.toInt()

    initialValues[initialValues.lastIndex] = initialValues[initialValues.lastIndex] + count

    for (j in length - 1 downTo  0) {
        val value = initialValues[j]

        if (j > 0) {
            initialValues[j] = value % (maximumValues[j] + 1)
            initialValues[j - 1] += value / (maximumValues[j] + 1)
        } else {
            initialValues[j] = value % (maximumValues[j] + 1)
        }
    }

    println(initialValues.joinToString(""))
}
