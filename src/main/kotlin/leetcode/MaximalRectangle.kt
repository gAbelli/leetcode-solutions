package leetcode

import kotlin.math.max

class MaximalRectangle {
    // There is also a O(min(n,m)^2) solution based on largest rectangle in histogram:
    // https://leetcode.com/problems/maximal-rectangle/solutions/29064/a-o-n-2-solution-based-on-largest-rectangle-in-histogram/
    fun maximalRectangle(matrix: Array<CharArray>): Int {
        val m = matrix.size
        val n = matrix[0].size
        if (n <= m) {
            val prev = Array(n) { IntArray(n) { 0 } }
            val cur = Array(n) { IntArray(n) { 0 } }
            var result = 0
            for (row in 0..<m) {
                for (i in 0..<n) {
                    var allOnes = true
                    for (j in i..<n) {
                        if (matrix[row][j] == '0') allOnes = false
                        cur[i][j] =
                            if (allOnes) prev[i][j] + (j - i + 1)
                            else 0
                        result = max(result, cur[i][j])
                    }
                }
                for (i in 0..n) for (j in i..<n) prev[i][j] = cur[i][j]
            }
            return result
        } else {
            val prev = Array(m) { IntArray(m) { 0 } }
            val cur = Array(m) { IntArray(m) { 0 } }
            var result = 0
            for (col in 0..<n) {
                for (i in 0..<m) {
                    var allOnes = true
                    for (j in i..<m) {
                        if (matrix[j][col] == '0') allOnes = false
                        cur[i][j] =
                            if (allOnes) prev[i][j] + (j - i + 1)
                            else 0
                        result = max(result, cur[i][j])
                    }
                }
                for (i in 0..m) for (j in i..<m) prev[i][j] = cur[i][j]
            }
            return result
        }
    }
}
