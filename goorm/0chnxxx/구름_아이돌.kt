/**
 * 3인조 구름 아이돌 그룹을 만들기 위해서 N명의 지원자들의 오디션을 진행했다.
 * 오디션이 진행되는 순서대로 1번 지원자부터 N번 지원자로 부르기로 한다.
 * 평가 위원들은 지원자들을 모두 다른 점수로 평가했고, i번 지원자의 점수는 Si이다.
 * 지원자 중에서 성적이 우수한 3명을 선발하기로 한다.
 * 성적이 가장 우수한 3명의 지원자를 점수 순서대로 출력하라.
 *
 * 첫번째 줄에 지원자 수 N 입력
 * 두번째 줄에 지원자의 점수 S1, S2, ..., SN이 공백을 두고 입력
 *
 * 1 <= N <= 100000
 * 1 <= Si <= 2 * N
 */

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val scores = readLine()!!.split(" ").map { it.toInt() }
    val applicants = scores.withIndex().map { it.index + 1 to it.value }
    val sortedApplicants = applicants.sortedByDescending { it.second }
    val result = sortedApplicants.subList(0, 3).map { it.first }.joinToString(" ")

    println(result)
}
