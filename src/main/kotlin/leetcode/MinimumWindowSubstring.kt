package leetcode

class MinimumWindowSubstring {
    fun minWindow(s: String, t: String): String {
        val tCounts = t.groupingBy { it }.eachCount()
        val sCounts = s.groupingBy { it }.eachCountTo(mutableMapOf())

        val sIsValid = tCounts.asSequence().fold(true) { acc, (c, count) ->
            acc && (sCounts.getOrDefault(c, 0) >= count)
        }
        if (!sIsValid) return ""

        var bestStart = 0
        while (bestStart in s.indices) {
            if (sCounts[s[bestStart]]!! == tCounts.getOrDefault(s[bestStart], 0)) break
            sCounts[s[bestStart]] = sCounts[s[bestStart]]!! - 1
            bestStart += 1
        }

        var result = s.substring(bestStart..<s.length)

        for (end in s.length - 2 downTo 0) {
            val removedChar = s[end + 1]
            sCounts[removedChar] = sCounts[removedChar]!! - 1
            while (bestStart >= 0 && sCounts[removedChar]!! < tCounts.getOrDefault(removedChar, 0)) {
                bestStart -= 1
                if (bestStart != -1) {
                    sCounts[s[bestStart]] = sCounts[s[bestStart]]!! + 1
                }
            }
            if (bestStart == -1) break
            if (end - bestStart + 1 < result.length) {
                result = s.substring(bestStart..end)
            }
        }

        return result
    }
}
