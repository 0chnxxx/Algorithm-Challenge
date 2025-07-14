/**
 * Given two version strings, version1 and version2, compare them.
 * A version string consists of revisions separated by dots '.'.
 * The value of the revision is its integer conversion ignoring leading zeros.
 * To compare version strings, compare their revision values in left-to-right order.
 * If one of the version strings has fewer revisions, treat the missing revision values as 0.
 *
 * Return the following:
 * If version1 < version2, return -1.
 * If version1 > version2, return 1.
 * Otherwise, return 0.
 *
 * Constraints:
 * 1 <= version1.length, version2.length <= 500
 * version1 and version2 only contain digits and '.'.
 * version1 and version2 are valid version numbers.
 * All the given revisions in version1 and version2 can be stored in a 32-bit integer.
 */

fun main() {
    val version1 = "1.2"
    val version2 = "1.10"

    val result = Solution().compareVersion(version1, version2)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(version1.length + version2.length)
    // 공간 복잡도 : O(1)
    fun compareVersion(version1: String, version2: String): Int {
        var i = 0
        var j = 0
        val n1 = version1.length
        val n2 = version2.length

        // version1, version2 둘 다 끝까지 탐색
        while (i < n1 || j < n2) {
            // version1을 .을 제거하고 순수 숫자로 변경
            var v1 = 0

            while (i < n1 && version1[i] != '.') {
                v1 = v1 * 10 + (version1[i] - '0')
                i++
            }

            // version2를 .을 제거하고 순수 숫자로 변경
            var v2 = 0

            while (j < n2 && version2[j] != '.') {
                v2 = v2 * 10 + (version2[j] - '0')
                j++
            }

            // version1, version2 비교
            if (v1 != v2) {
                return if (v1 > v2) 1 else -1
            }

            i++
            j++
        }

        return 0
    }

//    // 시간 복잡도 : O(version1.length + version2.length + splitVersion.length)
//    // 공간 복잡도 : O(splitVersion.length)
//    fun compareVersion(version1: String, version2: String): Int {
//        val splitVersion1 = version1.split(".").toMutableList()
//        val splitVersion2 = version2.split(".").toMutableList()
//
//        if (splitVersion1.size < splitVersion2.size) {
//            val diff = splitVersion2.size - splitVersion1.size
//
//            repeat(diff) {
//                splitVersion1.add("0")
//            }
//        } else if (splitVersion1.size > splitVersion2.size) {
//            val diff = splitVersion1.size - splitVersion2.size
//
//            repeat(diff) {
//                splitVersion2.add("0")
//            }
//        }
//
//        var result = 0
//        val length = splitVersion1.size
//
//        for (i in 0 until length) {
//            val version1 = splitVersion1[i].toInt()
//            val version2 = splitVersion2[i].toInt()
//
//            if (version1 > version2) {
//                result = 1
//                break
//            } else if (version1 < version2) {
//                result = -1
//                break
//            }
//        }
//
//        return result
//    }
}
