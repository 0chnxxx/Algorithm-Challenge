/**
 * 당신은 동영상 재생기를 만들고 있습니다.
 * 당신의 동영상 재생기는 10초 전으로 이동, 10초 후로 이동, 오프닝 건너뛰기 3가지 기능을 지원합니다.
 *
 * 각 기능이 수행하는 작업은 다음과 같습니다.
 * - 10초 전으로 이동
 *      사용자가 "prev" 명령을 입력할 경우 동영상의 재생 위치를 현재 위치에서 10초 전으로 이동합니다.
 *      현재 위치가 10초 미만인 경우 영상의 처음 위치로 이동합니다.
 *      영상의 처음 위치는 0분 0초입니다.
 * - 10초 후로 이동
 *      사용자가 "next" 명령을 입력할 경우 동영상의 재생 위치를 현재 위치에서 10초 후로 이동합니다.
 *      동영상의 남은 시간이 10초 미만일 경우 영상의 마지막 위치로 이동합니다.
 *      영상의 마지막 위치는 동영상의 길이와 같습니다.
 * - 오프닝 건너뛰기
 *      현재 재생 위치가 오프닝 구간(op_start <= 현재 재생 위치 <= op_end)인 경우 자동으로 오프닝이 끝나는 위치로 이동합니다.
 *
 * 동영상의 길이를 나타내는 문자열 video_len
 * 기능이 수행되기 직전의 재생위치를 나타내는 문자열 pos
 * 오프닝 시작 시각을 나타내는 문자열 op_start
 * 오프닝이 끝나는 시각을 나타내는 문자열 op_end
 * 사용자의 입력을 나타내는 1차원 문자열 배열 commands
 *
 * 사용자의 입력이 모두 끝난 후 동영상의 위치를 "mm:ss" 형식으로 return 하도록 solution 함수를 완성해 주세요.
 *
 * video_len의 길이 = pos의 길이 = op_start의 길이 = op_end의 길이 = 5
 * video_len, pos, op_start, op_end는 "mm:ss" 형식으로 mm분 ss초를 나타냅니다.
 * 0 <= mm <= 59
 * 0 <= ss <= 59
 * 분, 초가 한 자리일 경우 0을 붙여 두 자리로 나타냅니다.
 * 1 <= commands의 길이 <= 100
 * commands의 원소는 "prev" 혹은 "next" 입니다.
 */

fun main() {
    val video_len = "07:22"
    val pos = "07:05"
    val op_start = "00:15"
    val op_end = "04:07"
    val commands = arrayOf("next", "next")

    val solution = Solution().solution(video_len, pos, op_start, op_end, commands)

    println(solution)
}

class Solution {
    fun solution(video_len: String, pos: String, op_start: String, op_end: String, commands: Array<String>): String {
        var position = pos

        // 명령어 실행
        for (command in commands) {
            val opStart = op_start.split(":").map { it.toInt() }
            val opEnd = op_end.split(":").map { it.toInt() }

            // 명령어 수행 전 오프닝 구간 확인
            if (isOpening(position, opStart, opEnd)) {
                position = String.format("%02d:%02d", opEnd[0], opEnd[1])
            }

            // 명령어 수행
            when (command) {
                "prev" -> position = prev(position, opStart, opEnd)
                "next" -> position = next(position, video_len, opStart, opEnd)
            }

            // 명령어 수행 후 오프닝 구간 확인
            if (isOpening(position, opStart, opEnd)) {
                position = String.format("%02d:%02d", opEnd[0], opEnd[1])
            }
        }

        return position
    }

    private fun next(position: String, video_len: String, opStart: List<Int>, opEnd: List<Int>): String {
        var m = position.split(":")[0].toInt()
        var s = position.split(":")[1].toInt()

        val videoLength = video_len.split(":").map { it.toInt() }

        // 10초 이후로
        s += 10

        if (s >= 60) {
            m += 1
            s -= 60
        }

        // 동영상 길이를 넘어간 경우 보정
        if (videoLength[0] * 60 + videoLength[1] <= m * 60 + s) {
            m = videoLength[0]
            s = videoLength[1]
        }

        return String.format("%02d:%02d", m, s)
    }

    private fun prev(position: String, opStart: List<Int>, opEnd: List<Int>): String {
        var m = position.split(":")[0].toInt()
        var s = position.split(":")[1].toInt()

        // 10초 이전으로
        if (s < 10) {
            m -= 1
            s = 60 + (s - 10)
        } else {
            s -= 10
        }

        // 0분 0초 이전인 경우 보정
        if (m * 60 + s < 0) {
            m = 0
            s = 0
        }

        return String.format("%02d:%02d", m, s)
    }

    private fun isOpening(position: String, opStart: List<Int>, opEnd: List<Int>): Boolean {
        val m = position.split(":")[0].toInt()
        val s = position.split(":")[1].toInt()

        val current = (m * 60) + s
        val start = (opStart[0] * 60) + opStart[1]
        val end = (opEnd[0] * 60) + opEnd[1]

        // 초 단위로 변경하여 구간 안에 들어오는지 판단
        return current in start..end
    }
}
