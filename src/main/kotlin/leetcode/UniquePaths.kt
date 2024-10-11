package leetcode

import java.math.BigInteger
import kotlin.math.min

class UniquePaths {
    private fun bin(a: Int, b: Int): Int = (
            (a downTo (a - b + 1)).fold(BigInteger.valueOf(1), ::product) /
                    (b downTo 1).fold(BigInteger.valueOf(1), ::product)
            ).toInt()

    private fun product(a: BigInteger, b: Int): BigInteger = a * b.toBigInteger()

    fun uniquePaths(m: Int, n: Int): Int = bin(m + n - 2, min(m, n) - 1)
}
