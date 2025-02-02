/**
 * 당신은 이진트리를 수로 표현하는 것을 좋아합니다.
 *
 * 이진트리를 수로 표현하는 방법은 다음과 같습니다.
 * 1. 이진수를 저장할 빈 문자열을 생성합니다.
 * 2. 주어진 이진트리에 더미 노드를 추가하여 포화 이진트리로 만듭니다. 루트 노드는 그대로 유지합니다.
 * 3. 만들어진 포화 이진트리의 노드들을 가장 왼쪽 노드부터 가장 오른쪽 노드까지, 왼쪽에 있는 순서대로 살펴봅니다. 노드의 높이는 살펴보는 순서에 영향을 끼치지 않습니다.
 * 4. 살펴본 노드가 더미 노드라면, 문자열 뒤에 0을 추가합니다. 살펴본 노드가 더미 노드가 아니라면 문자열 뒤에 1을 추가합니다.
 * 5. 문자열에 저장된 이진수를 십진수로 변환합니다.
 *
 * 이진트리에서 리프 노드가 아닌 노드는 자신의 왼쪽 자식이 루트인 서브트리의 노드들보다 오른쪽에 있으며, 자신의 오른쪽 자식이 루트인 서브트리의 노드들보다 왼쪽에 있다고 가정합니다.
 * 당신은 수가 주어졌을때, 하나의 이진트리로 해당 수를 표현할 수 있는지 알고 싶습니다.
 *
 * 이진트리로 만들고 싶은 수를 담은 1차원 정수 배열 numbers가 주어집니다.
 * numbers에 주어진 순서대로 하나의 이진트리로 해당 수를 표현할 수 있다면 1을, 표현할 수 없다면 0을 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 *
 * 1 <= numbers의 길이 <= 10000
 * 1 <= numbers의 원소 <= 10^15
 */

fun main() {
    val numbers = longArrayOf(7, 42, 5)

    val solution = Solution().solution(numbers)

    println(solution.joinToString(", "))
}

class Solution {
    fun solution(numbers: LongArray): IntArray {
        val result = MutableList(numbers.size) { 1 }

        for ((i, number) in numbers.withIndex()) {
            // 10진수를 2진수로 변환
            var bit = number.toString(2)

            // 포화 이진트리를 만들기 위해 앞에 0을 추가
            var length = 1

            while (bit.length >= length) {
                length *= 2
            }

            bit = bit.padStart(length - 1, '0')

            // 비논리적 트리인지 확인
            dfs(i, bit, result)
        }

        return result.toIntArray()
    }

    private fun dfs(index: Int, bit: String, numbers: MutableList<Int>) {
        val length = bit.length
        val mid = length / 2

        // 해당 bit가 비논리적인 트리라고 이미 확인되었다면 얼리 리턴
        if (numbers[index] != 1) {
            return
        }

        // 비논리적인 트리인지 확인
        // 루트 노드가 0인데 자식 노드 중에 1을 포함할 수 없음
        if (bit[mid] == '0' && bit.contains('1')) {
            numbers[index] = 0
            return
        }

        // 확인할 서브트리가 더 남았다면 좌, 우 자식 서브트리를 슬라이싱하여 재귀 호출
        if (mid > 0) {
            dfs(index, bit.substring(0, mid), numbers)
            dfs(index, bit.substring(mid + 1), numbers)
        }
    }
}
