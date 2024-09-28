package leetcode

import kotlin.math.max

class LongestRepeatingCharacterReplacement {
    fun characterReplacement(s: String, k: Int): Int {
        fun (Map<Char, Int>).isViable(): Boolean = values.sum() - values.max() <= k

        val counter = mutableMapOf<Char, Int>()
        var result = 0
        var start = 0
        for (end in s.indices) {
            if (!counter.contains(s[end])) counter[s[end]] = 0
            counter[s[end]] = counter[s[end]]!! + 1
            while (!counter.isViable()) {
                counter[s[start]] = counter[s[start]]!! - 1
                start += 1
            }
            result = max(result, end - start + 1)
        }

        return result
    }
}
