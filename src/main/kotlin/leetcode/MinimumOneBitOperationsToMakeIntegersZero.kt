package leetcode

class MinimumOneBitOperationsToMakeIntegersZero {
    // See https://en.wikipedia.org/wiki/Gray_code
    fun minimumOneBitOperations(n: Int): Int {
        var mask = n
        var n = n
        while (mask != 0) {
            mask = mask shr 1
            n = n xor mask
        }
        return n
    }
}
