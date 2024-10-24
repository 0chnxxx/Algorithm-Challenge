/**
 * 사진별로 추억 점수를 매기려고 한다.
 * 사진 속에 나오는 인물의 그리움 점수를 모두 합산한 값이 해당 사진의 추억 점수가 된다.
 *
 * 그리워하는 사람의 이름을 담은 문자열 배열 name
 * 각 사람별 그리움 점수를 담은 정수 배열 yearning
 * 각 사진에 찍힌 인물의 이름을 담은 이차원 배열 photo
 *
 * 사진들의 추억 점수를 photo에 주어진 순서대로 배열에 담아 return하라.
 *
 * 3 <= name의 길이 = yearning의 길이 <= 100
 * 3 <= name의 원소의 길이 <= 7
 * 1 <= yearning의 원소의 크기 <= 100
 * 3 <= photo의 길이 <= 100
 * 3 <= photo의 원소의 길이 <= 7
 */

class Solution {
    fun solution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray {
        val scoreMap = name.mapIndexed { index, it -> Pair(it, yearning[index]) }.toMap()
        val result = IntArray(photo.size)
        var totalScore = 0

        for (i in 0 until photo.size) {
            val names = photo[i]

            for (j in 0 until names.size) {
                val name = names[j]
                val score = scoreMap[name]

                totalScore += score ?: 0
            }

            result[i] = totalScore
            totalScore = 0
        }

        return result
    }
}

fun main(args: Array<String>) {
    println(Solution().solution(
        arrayOf("may", "kein", "kain", "radi"),
        intArrayOf(5, 10, 1, 3),
        arrayOf(
            arrayOf("may", "kein", "kain", "radi"),
            arrayOf("may", "kein", "brin", "deny"),
            arrayOf("kon", "kain", "may", "coni")
        )).joinToString(", ")
    )
    println(Solution().solution(
        arrayOf("kali", "mari", "don"),
        intArrayOf(11, 1, 55),
        arrayOf(
            arrayOf("kali", "mari", "don"),
            arrayOf("pony", "tom", "teddy"),
            arrayOf("con", "mona", "don")
        )).joinToString(", ")
    )
    println(Solution().solution(
        arrayOf("may", "kein", "kain", "radi"),
        intArrayOf(5, 10, 1, 3),
        arrayOf(
            arrayOf("may"),
            arrayOf("kein", "deny", "may"),
            arrayOf("kon", "coni")
        )).joinToString(", ")
    )
}
