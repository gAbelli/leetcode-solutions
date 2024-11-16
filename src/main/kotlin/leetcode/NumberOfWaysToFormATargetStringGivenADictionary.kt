package leetcode

class NumberOfWaysToFormATargetStringGivenADictionary {
    private val MOD = 1_000_000_000 + 7
    private fun (Long).trim(): Long = (this % MOD + MOD) % MOD

    fun numWays(words: Array<String>, target: String): Int {
        val n = words[0].length
        val matrix = Array(n) { IntArray('z' - 'a' + 1) { 0 } }
        for (word in words) for (i in word.indices) matrix[i][word[i] - 'a'] += 1

        val cur = LongArray(target.length + 1) { 0 }
        val prev = LongArray(target.length + 1) { 0 }
        cur[target.length] = 1
        prev[target.length] = 1

        for (i in n - 1 downTo 0) {
            for (j in target.indices) {
                val count = matrix[i][target[j] - 'a']
                cur[j] = (prev[j] + if (count != 0) count * prev[j + 1] else 0).trim()
            }
            for (j in target.indices) prev[j] = cur[j]
        }

        return cur[0].trim().toInt()
    }

    fun numWaysMemo(words: Array<String>, target: String): Int {
        val n = words[0].length
        val matrix = Array(n) { IntArray('z' - 'a' + 1) { 0 } }
        for (word in words) for (i in word.indices) matrix[i][word[i] - 'a'] += 1
        val memo = Array(n) { LongArray(target.length) { -1 } }

        fun helper(i: Int, j: Int): Long {
            if (j == target.length) return 1
            if (i == n) return 0
            if (memo[i][j] != -1L) return memo[i][j]
            val count = matrix[i][target[j] - 'a']
            val result = (helper(i + 1, j) +
                    if (count != 0) count * helper(i + 1, j + 1)
                    else 0).trim()
            memo[i][j] = result
            return result
        }

        return helper(0, 0).trim().toInt()
    }
}
