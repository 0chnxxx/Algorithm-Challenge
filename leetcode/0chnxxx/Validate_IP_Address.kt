/**
 * Given a string queryIP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.
 *
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros.
 * For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses while "192.168.01.1", "192.168.1.00", and "192.168@1.1" are invalid IPv4 addresses.
 *
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 * 1 <= xi.length <= 4
 * xi is a hexadecimal string which may contain digits, lowercase English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
 * Leading zeros are allowed in xi.
 *
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 *
 * Constraints:
 * queryIP consists only of English letters, digits and the characters '.' and ':'.
 */

fun main() {
    val queryIP = "172.16.254.1"

    val result = Solution().validIPAddress(queryIP)

    println(result)
}

class Solution {
    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun validIPAddress(queryIP: String): String {
        return when {
            queryIP.contains('.') && !queryIP.contains(':') -> {
                if (isValidIPv4(queryIP)) "IPv4" else "Neither"
            }
            queryIP.contains(':') && !queryIP.contains('.') -> {
                if (isValidIPv6(queryIP)) "IPv6" else "Neither"
            }
            else -> "Neither"
        }
    }

    private fun isValidIPv4(ip: String): Boolean {
        val parts = ip.split(".")

        if (parts.size != 4) return false

        for (p in parts) {
            if (p.isEmpty()) return false
            if (p.length > 1 && p[0] == '0') return false
            if (!p.all { it.isDigit() }) return false

            val num = p.toIntOrNull() ?: return false
            if (num !in 0..255) return false
        }

        return true
    }

    private fun isValidIPv6(ip: String): Boolean {
        val parts = ip.split(":")

        if (parts.size != 8) return false

        val hexDigits = "0123456789abcdefABCDEF"

        for (p in parts) {
            if (p.isEmpty() || p.length > 4) return false
            if (!p.all { it in hexDigits }) return false
        }

        return true
    }
}
