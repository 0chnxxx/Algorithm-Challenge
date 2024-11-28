/**
 * 구름이는 10 이상 99 이하의 서로 다른 수 Ai가 적힌 카드를 N개 가지고 있다.
 * 구름이는 카드의 순서를 임의로 정한 다음, 카드에 적힌 수들을 순서대로 이어 붙여서 하나의 큰 수를 만드는 놀이를 하고자 한다.
 * 예를 들어서 12가 적힌 카드와 34가 적힌 카드를 순서대로 이어 붙이면 1234를 만들 수 있다.
 * 물론 저대로는 너무 시시하기에, 구름이는 앞에 있는 카드의 일의 자리 숫자와 뒤에 있는 카드의 십의 자리 숫자가 같다면 숫자 하나를 겹쳐서 이어 붙일 수 있다는 규칙을 추가했다.
 * 예를 들어서 38이 적힌 카드와 84가 적힌 카드를 이어 붙일 때 38의 일의 자리 숫자와 84의 십의 자리 숫자가 동일하므로 두 수를 384와 같이 겹쳐서 이어 붙일 수 있다.
 * 물론 3884와 같이 겹쳐서 이어 붙이지 않는 것도 가능하다.
 * 구름이가 놀이에서 만들 수 있는 수 중, 가작 작은 수를 찾아 출력하시오.
 * 단, 놀이를 할 때는 항상 모든 카드를 다 사용해야 함에 유의한다.
 *
 * 첫번째 줄에는 카드의 개수 N 입력
 * 두번째 줄에는 각 카드에 적혀 있는 수 Ai, ..., An이 공백을 두고 입력
 *
 * 1 <= N <= 8
 * 10 <= Ai <= 99
 */

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val numbers = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    fun swap(numbers: IntArray, i: Int, j: Int) {
        val temp = numbers[i]
        numbers[i] = numbers[j]
        numbers[j] = temp
    }

    fun permute(numbers: IntArray, start: Int, cases: MutableList<IntArray>) {
        if (start == numbers.size - 1) {
            cases.add(numbers.copyOf())
            return
        }

        for (i in start until numbers.size) {
            swap(numbers, start, i)
            permute(numbers, start + 1, cases)
            swap(numbers, start, i)
        }
    }

    val cases = mutableListOf<IntArray>()

    permute(numbers, 0, cases)

    fun merge(numbers: IntArray): Long {
        var mergedNumber = ""

        for (i in 0 until n) {
            val current = numbers[i]

            if (mergedNumber.isEmpty()) {
                mergedNumber += current
                continue
            }

            if (mergedNumber.toLong() % 10 == (current / 10).toLong()) {
                mergedNumber += current % 10
            } else {
                mergedNumber += current
            }
        }

        return mergedNumber.toLong()
    }

    val result = cases
        .map { merge(it) }
        .minOf { it }

    println(result)
}
