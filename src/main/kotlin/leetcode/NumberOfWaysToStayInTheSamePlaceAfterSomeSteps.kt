package leetcode

class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
    val MOD = 1_000_000_000 + 7
    fun (Long).trim(): Long = (this % MOD + MOD) % MOD

    // I really wanted to do this with a generalized version of the ballot's problem, but I cannot figure out
    // the formula for how many paths don't touch either y=x+1 and y=x-arrLen
    fun numWays(steps: Int, arrLen: Int): Int {
        val prev = LongArray(arrLen) { 0 }
        val cur = LongArray(arrLen) { 0 }
        prev[0] = 1

        for (i in 0..<steps) {
            for (j in cur.indices) cur[j] = (prev.getOrElse(j - 1) { 0 } + prev[j] + prev.getOrElse(j + 1) { 0 }).trim()
            for (j in cur.indices) prev[j] = cur[j]
        }

        return cur[0].trim().toInt()
    }
}
