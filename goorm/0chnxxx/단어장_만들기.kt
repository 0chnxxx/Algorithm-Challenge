/**
 * 구름이는 영어 공부를 위해서 개인 단어장을 만들어 컴퓨터에 저장하고 있다.
 * 그런데 구름이가 공부를 너무 열심히 한 나머지, 단어장에 저장된 단어들이 너무 많아져서 어떤 단어가 어디에 위치해 있는지를 빠르게 파악하는 것이 어려워졌다.
 * 그래서 구름이는 단어장에서 효율적으로 단어를 찾는 기능을 개발하려고 한다.
 *
 * 그러기 위해서 우선 구름이는 단어장에 있는 단어들을 길이가 오름차순이 되도록 정렬한다.
 * 이 때 길이가 같은 단어들은 사전 순으로 정렬한다.
 * 그런 다음 사전의 앞에서 K번째에 위치한 단어를 출력하도록 기능을 설계했다.
 *
 * 1 <= N <= 1000000
 * 1 <= K <= N
 * 1 <= 단어의 길이 <= 20
 */

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val words = mutableListOf<String>()

    repeat(n) {
        words.add(readLine()!!)
    }

    // 단어 길이 오름차순 -> 단어 사전순
    words.sortWith(compareBy<String> { it.length }.thenBy { it })

    println(words[k - 1])
}
