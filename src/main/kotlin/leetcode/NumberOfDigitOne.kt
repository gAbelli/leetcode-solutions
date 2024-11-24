package leetcode

import kotlin.math.min

class NumberOfDigitOne {
    companion object {
        val memo = mutableMapOf(0 to 0)
    }

    // f(3456) = 1000 + f(456) + 3 * f(999)
    // f(1456) = 457 + f(456) + 1 * f(999)
    fun countDigitOne(n: Int): Int = memo.getOrPut(n) {
        var maxPowerOfTen = 1L
        while (maxPowerOfTen * 10 <= n) maxPowerOfTen *= 10
        val div = (n / maxPowerOfTen).toInt()
        val remainder = (n % maxPowerOfTen).toInt()
        min(n - maxPowerOfTen.toInt() + 1, maxPowerOfTen.toInt()) +
                countDigitOne(remainder) +
                div * countDigitOne(maxPowerOfTen.toInt() - 1)
    }
}
