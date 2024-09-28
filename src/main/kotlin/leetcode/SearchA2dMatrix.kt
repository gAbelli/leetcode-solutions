package leetcode

class SearchA2dMatrix {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val m = matrix.size
        val n = matrix[0].size

        fun toPair(k: Int): Pair<Int, Int> = k / n to k % n

        var left = 0
        var right = m * n - 1

        while (left <= right) {
            if (left == right) {
                val (i, j) = toPair(left)
                return matrix[i][j] == target
            }
            val mid = (left + right) / 2
            val (i, j) = toPair(mid)
            val cmp = matrix[i][j].compareTo(target)
            when {
                cmp == 0 -> return true
                cmp < 0 -> left = mid + 1
                else -> right = mid - 1
            }
        }
        return false
    }
}
