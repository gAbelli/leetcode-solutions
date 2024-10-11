package leetcode

class LongestIncreasingPathInAMatrix {
    fun longestIncreasingPath(matrix: Array<IntArray>): Int {
        val m = matrix.size
        val n = matrix[0].size
        val memo = Array(m) { IntArray(n) { -1 } }
        val seen = Array(m) { BooleanArray(n) { false } }

        fun helper(i: Int, j: Int, prev: Int): Int {
            if (
                i !in 0..<m
                || j !in 0..<n
                || seen[i][j]
                || matrix[i][j] <= prev
            ) return 0
            if (memo[i][j] != -1) return memo[i][j]

            seen[i][j] = true
            val result = 1 + maxOf(
                helper(i - 1, j, matrix[i][j]),
                helper(i + 1, j, matrix[i][j]),
                helper(i, j - 1, matrix[i][j]),
                helper(i, j + 1, matrix[i][j])
            )

            memo[i][j] = result
            seen[i][j] = false
            return result
        }

        return (0..<m).maxOf { i -> (0..<n).maxOf { j -> helper(i, j, Int.MIN_VALUE) } }
    }
}
