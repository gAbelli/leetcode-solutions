package leetcode

import kotlin.math.max

class LongestSubstringWithoutRepeatingCharacters {
    fun lengthOfLongestSubstring(s: String): Int {
        val pos = mutableMapOf<Char, Int>()
        var result = 0
        var start = 0
        for (end in s.indices) {
            if (s[end] in pos.keys) {
                val newStart = pos[s[end]]!! + 1
                for (i in start..<newStart) {
                    pos.remove(s[i])
                }
                start = newStart
            }
            pos[s[end]] = end
            result = max(result, end - start + 1)
        }
        return result
    }
}
