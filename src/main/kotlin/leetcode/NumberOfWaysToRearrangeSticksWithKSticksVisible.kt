package leetcode

class NumberOfWaysToRearrangeSticksWithKSticksVisible {
    private val MOD = 1_000_000_000 + 7
    private fun (Long).trim(): Long = (this % MOD + MOD) % MOD

    // f(n, k)   = sum_{i=0..n-1}{f(i, k-1) * bin(n-1, i) * (n-1-i)!}
    //           = sum_{i=0..n-1}{f(i, k-1) * (n-1)*(n-2)*...*(i+1) }
    // f(n-1, k) = sum_{i=0..n-2}{f(i, k-1) *       (n-2)*...*(i+1) }
    // f(n, k)   = f(n-1, k) * (n-1) + f(n-1, k-1)
    fun rearrangeSticks(n: Int, k: Int): Int {
        val prev = LongArray(k + 1) { 0 }
        val cur = LongArray(k + 1) { 0 }
        prev[0] = 1

        for (m in 1..n) {
            for (h in 1..k) cur[h] = (prev[h] * (m - 1) + prev[h - 1]).trim()
            for (h in cur.indices) prev[h] = cur[h]
        }

        return cur[k].trim().toInt()
    }
}
