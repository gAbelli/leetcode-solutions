package leetcode

class CountAllValidPickupAndDeliveryOptions {
    fun (Long).trim(): Long = this % (1_000_000_000 + 7)

    // There is also the much easier mathematical solution (2n)! / (2^n)
    fun countOrders(n: Int): Int {
        val memo = mutableMapOf<Pair<Int, Int>, Long>()
        fun helper(open: Int, closed: Int): Long = memo.getOrPut(Pair(open, closed)) {
            if (closed == n) 1
            else {
                var result = 0L
                if (open < n) result += (n - open) * helper(open + 1, closed)
                if (closed < open) result += (open - closed) * helper(open, closed + 1)
                result.trim()
            }
        }
        return helper(0, 0).trim().toInt()
    }
}
