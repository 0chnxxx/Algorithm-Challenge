import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 프렌즈 대학교 조교였던 제이지는 허드렛일만 시키는 네오 학과장님의 마수에서 벗어나, 카카오에 입사하게 되었다.
 * 평소에 관심있어 하던 검색에 마침 결원이 발생하여, 검색개발팀에 편입될 수 있었고, 대망의 첫 프로젝트를 맡게 되었다.
 *
 * 그 프로젝트는 검색어에 가장 잘 맞는 웹페이지를 보여주기 위해 아래와 같은 규칙으로 검색어에 대한 웹페이지의 매칭점수를 계산 하는 것이었다.
 * - 한 웹페이지에 대해서 기본 점수, 외부 링크 수, 링크 점수, 그리고 매칭 점수를 구할 수 있다.
 * - 한 웹페이지의 기본 점수는 해당 웹페이지의 텍스트 중, 검색어가 단어 단위로 등장하는 횟수이다. (대소문자 무시)
 * - 한 웹페이지의 외부 링크 수는 해당 웹페이지에서 다른 외부 페이지로 연결된 링크의 개수이다.
 * - 한 웹페이지의 링크 점수는 해당 웹페이지로 링크가 걸린 다른 웹페이지의 기본 점수 / 외부 링크 수의 총합이다.
 * - 한 웹페이지의 매칭 점수는 기본 점수와 링크 점수의 합으로 계산한다.
 *
 * 검색어 word
 * 웹페이지의 HTML 목록인 pages
 *
 * 매칭 점수가 가장 높은 웹페이지의 index를 구하라.
 * 만약 그런 웹페이지가 여러 개라면 그중 번호가 가장 작은 것을 구하라.
 *
 * 1 <= word의 길이 <= 12
 * 1 <= pages의 길이 <= 20
 * 1 <= pages[i]의 길이 <= 1500
 *
 * 한 웹페이지의 url은 HTML의 <head> 태그 내의 <meta> 태그의 값으로 주어진다.
 * 한 웹페이지의 모든 외부 링크는 <a href="https://careers.kakao.com/index">의 형태를 가진다.
 * 모든 url은 https://로만 시작한다.
 */

fun main() {
    val word = "blind"
    val pages = arrayOf(
        """
            <html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
            <head>
              <meta charset="utf-8">
              <meta property="og:url" content="https://a.com"/>
            </head>
            <body>
            Blind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. 
            <a href="https://b.com"> Link to b </a>
            </body>
            </html>
        """.trimIndent(),
        """
            <html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
            <head>
              <meta charset="utf-8">
              <meta property="og:url" content="https://b.com"/>
            </head>
            <body>
            Suspendisse potenti. Vivamus venenatis tellus non turpis bibendum, 
            <a href="https://a.com"> Link to a </a>
            blind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.
            <a href="https://c.com"> Link to c </a>
            </body>
            </html>
        """.trimIndent(),
        """
            <html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
            <head>
              <meta charset="utf-8">
              <meta property="og:url" content="https://c.com"/>
            </head>
            <body>
            Ut condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind
            <a href="https://a.com"> Link to a </a>
            </body>
            </html>
        """.trimIndent()
    )

    val solution = Solution().solution(word, pages)

    println(solution)
}

class Solution {
    data class Page(
        val index: Int,
        val selfLink: String,
        val externalLinks: List<String> = emptyList(),
        val basicScore: Int = 0,
        var LinkScore: Double = 0.0,
        var matchScore: Double = 0.0
    )

    fun solution(word: String, pages: Array<String>): Int {
        // 정규 표현식
        val metaPattern = Pattern.compile("<meta property=\"og:url\" content=\"https://.*?\"")
        val hrefPattern = Pattern.compile("<a href=\"https://.*?\">")
        val httpsPattern = Pattern.compile("https://[^\"]+")
        val wordPattern = Pattern.compile("(?<=[^A-Za-z])" + word + "(?=[^A-Za-z])", Pattern.CASE_INSENSITIVE)

        // 매칭 점수를 만들기 위한 데이터들을 구한 뒤 Page 객체로 매핑
        val mappedPages = pages.mapIndexed { index, html ->
            val metaMatcher = metaPattern.matcher(html)
            val hrefMatcher = hrefPattern.matcher(html)
            val wordMatcher = wordPattern.matcher(html)

            val selfLink = findSelfLink(metaMatcher, httpsPattern)
            val externalLinks = findExternalLinks(hrefMatcher, httpsPattern)
            val wordCount = findWordCount(wordMatcher)

            Page(
                index = index,
                selfLink = selfLink,
                externalLinks = externalLinks,
                basicScore = wordCount,
                LinkScore = 0.0,
                matchScore = 0.0
            )
        }

        // 링크 점수 계산
        for (page in mappedPages) {
            for (otherPage in mappedPages) {
                if (!otherPage.externalLinks.contains(page.selfLink)) continue

                page.LinkScore += (otherPage.basicScore).toDouble() / (otherPage.externalLinks.size).toDouble()
            }
        }

        // 매칭 점수 계산
        mappedPages.forEach {
            it.matchScore = it.basicScore + it.LinkScore
        }

        // 정렬 기준에 맞춰 반환
        return mappedPages
            .sortedWith(compareByDescending<Page> { it.matchScore }.thenBy { it.index })
            .first()
            .index
    }

    private fun findSelfLink(metaMatcher: Matcher, httpsPattern: Pattern): String {
        return if (metaMatcher.find()) {
            val httpsMatcher = httpsPattern.matcher(metaMatcher.group())

            if (httpsMatcher.find()) {
                httpsMatcher.group()
            } else {
                ""
            }
        } else {
            ""
        }
    }

    private fun findExternalLinks(hrefMatcher: Matcher, httpsPattern: Pattern): MutableList<String> {
        val externalLinks = mutableListOf<String>()

        while (hrefMatcher.find()) {
            val httpsMatcher = httpsPattern.matcher(hrefMatcher.group())

            if (httpsMatcher.find()) {
                externalLinks.add(httpsMatcher.group())
            }
        }

        return externalLinks
    }

    private fun findWordCount(wordMatcher: Matcher): Int {
        var wordCount = 0

        while (wordMatcher.find()) {
            wordCount++
        }

        return wordCount
    }
}
