/**
 * 카카오TV에서 유명한 크리에이터로 활동 중인 죠르디는 환경 단체로부터 자신의 가장 인기있는 동영상에 지구온난화의 심각성을 알리기 위한 공익광고를 넣어 달라는 요청을 받았습니다.
 * 평소에 환경 문제에 관심을 가지고 있던 죠르디는 요청을 받아들였고 광고효과를 높이기 위해 시청자들이 가장 많이 보는 구간에 공익광고를 넣으려고 합니다.
 * 죠르디는 시청자들이 해당 동영상의 어떤 구간을 재생했는지 알 수 있는 재생구간 기록을 구했고, 해당 기록을 바탕으로 공익광고가 삽입될 최적의 위치를 고를 수 있습니다.
 * 참고로 광고는 재생 중인 동영상의 오른쪽 아래에서 원래 영상과 동시에 재생되는 PIP(Picture in Picture) 형태로 제공됩니다.
 *
 * 죠르디의 동영상 재생시간 길이 play_time
 * 공익광고의 재생시간 길이 adv_time
 * 시청자들이 해당 동영상을 재생했던 구간 정보 logs
 *
 * 시청자들의 누적 재생시간이 가장 많이 나오는 곳에 공익광고를 삽입하려고 합니다.
 * 이때, 공익광고가 들어갈 시작 시각을 구해서 return 하도록 solution 함수를 완성해주세요.
 * 만약, 시청자들의 누적 재생시간이 가장 많은 곳이 여러 곳이라면, 그 중에서 가장 빠른 시작 시각을 return 하도록 합니다.
 *
 * play_time, adv_time은 길이 8로 고정된 문자열입니다.
 * HH:MM:SS 형식이며, 00:00:01 이상 99:59:59 이하입니다.
 * adv_time <= play_time
 * 1 <= logs의 크기 <= 300000
 */

fun main() {
    val playTime = "02:03:55"
    val advTime = "00:14:15"
    val logs = arrayOf(
        "01:20:15-01:45:14",
        "00:40:31-01:00:00",
        "00:25:50-00:48:29",
        "01:30:59-01:53:29",
        "01:37:44-02:02:30"
    )

    val solution = Solution().solution(playTime, advTime, logs)

    println(solution)
}

class Solution {
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val playSeconds = convert(play_time)
        val advSeconds = convert(adv_time)
        val playArray = LongArray(playSeconds + 1) { 0L }

        // 시청 기록 시작과 끝 마킹
        logs.map { log ->
            val (start, end) = log.split("-").map { convert(it) }

            playArray[start] += 1L

            if (end < playSeconds) {
                playArray[end] -= 1L
            }
        }

        // 시청자 수 계산
        for (i in 1..playSeconds) {
            playArray[i] += playArray[i - 1]
        }

        // 누적 시청자 수 계산
        for (i in 1..playSeconds) {
            playArray[i] += playArray[i - 1]
        }

        var maxViewers = playArray[advSeconds - 1]
        var insertSeconds = 0L

        for (i in advSeconds..playSeconds) {
            // i초는 삽입 종료 시간
            // viewers는 i초에서 광고 시간만큼 뺀 구간의 누적 시청자 수
            val viewers = playArray[i] - playArray[i - advSeconds]

            if (viewers > maxViewers) {
                maxViewers = viewers
                insertSeconds = i - advSeconds + 1L // 삽입 시작 시간
            }
        }

        return format(insertSeconds)
    }

    // HH:MM:SS 를 초로 변환하는 함수
    fun convert(time: String): Int {
        val (hour, minute, seconds) = time.split(":").map { it.toInt() }

        return (hour * 3600) + (minute * 60) + seconds
    }

    // 초를 HH:MM:SS로 변환하는 함수
    fun format(seconds: Long): String {
        val hour = seconds / 3600
        val minute = (seconds % 3600) / 60
        val second = seconds % 60

        return String.format("%02d:%02d:%02d", hour, minute, second)
    }
}
