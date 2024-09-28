package leetcode

class PermutationInString {
    fun (Map<Char, Int>).sameCounter(other: Map<Char, Int>): Boolean {
        val keySet = keys + other.keys
        for (key in keySet) {
            if (getOrDefault(key, 0) != other.getOrDefault(key, 0)) return false
        }
        return true
    }

    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false
        val s1Counter = s1.groupingBy { it }.eachCount()

        val slidingWindowCounter = mutableMapOf<Char, Int>()
        for (i in s1.indices) {
            val c = s2[i]
            if (!slidingWindowCounter.contains(c)) slidingWindowCounter[c] = 0
            slidingWindowCounter[c] = slidingWindowCounter[c]!! + 1
        }
        if (s1Counter.sameCounter(slidingWindowCounter)) return true

        for (start in 1..s2.length - s1.length) {
            slidingWindowCounter[s2[start - 1]] = slidingWindowCounter[s2[start - 1]]!! - 1

            val end = start + s1.length - 1
            if (!slidingWindowCounter.contains(s2[end])) slidingWindowCounter[s2[end]] = 0
            slidingWindowCounter[s2[end]] = slidingWindowCounter[s2[end]]!! + 1

            if (s1Counter.sameCounter(slidingWindowCounter)) return true
        }

        return false
    }
}
