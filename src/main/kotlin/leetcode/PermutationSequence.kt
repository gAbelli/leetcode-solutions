package leetcode

class PermutationSequence {
    companion object {
        val factorials = IntArray(9) { 1 }.apply {
            for (i in 1..8) set(i, get(i - 1) * i)
        }
    }

    fun getPermutation(n: Int, k: Int): String {
        var n = n
        var k = k - 1
        val result = mutableListOf<Int>()
        val remainingDigits = (1..n).toMutableList()
        while (n != 0) {
            val div = k / factorials[n - 1]
            result.add(remainingDigits.removeAt(div))
            k %= factorials[n - 1]
            n -= 1
        }
        return result.joinToString("")
    }
}
