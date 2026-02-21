package leetcode

class PrimeNumberOfSetBitsInBinaryRepresentation {
    val primes = intArrayOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31)
    val isPrime = BooleanArray(33) { primes.contains(it) }
    val binomial = Array(33) { IntArray(it + 2) }.apply {
        this[0][0] = 1
        for (n in 1..32) for (k in 0..n) this[n][k] =
            (this[n - 1].getOrElse(k - 1) { 0 } + this[n - 1][k])
    }
    val precomputedSolutions = Array(32) { pos ->
        IntArray(32) { ones ->
            primes.asSequence().map { it - ones }.filter { it in 0..pos }.sumOf { binomial[pos][it] }
        }
    }

    fun countPrimeSetBits(left: Int, right: Int): Int =
        countPrimeSetBits(right) - countPrimeSetBits(left - 1)

    fun countPrimeSetBits(n: Int): Int {
        val memo = Array(32) { IntArray(33) { -1 } }
        fun helper(pos: Int, ones: Int): Int {
            if (pos == -1) return if (isPrime[ones]) 1 else 0
            memo[pos][ones].let { if (it != -1) return it }
            val result =
                if (n and (1 shl pos) == 0) helper(pos - 1, ones)
                else helper(pos - 1, ones + 1) + precomputedSolutions[pos][ones]

            memo[pos][ones] = result
            return result
        }
        return helper(31, 0)
    }
}
