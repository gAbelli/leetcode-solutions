package leetcode

class NumberOfSubmatricesThatSumToTarget {
    fun numSubmatrixSumTarget(matrix: Array<IntArray>, target: Int): Int {
        val m = matrix.size
        val n = matrix[0].size
        var result = 0

        for (x1 in 0..<m) {
            for (y1 in 0..<n) {
                val prevRow = IntArray(n - y1) { 0 }
                val curRow = IntArray(n - y1) { 0 }
                for (x2 in x1..<m) {
                    for (y2 in y1..<n) {
                        val left =
                            if (y2 > y1) curRow[y2 - y1 - 1]
                            else 0
                        val top = prevRow[y2 - y1]
                        val topLeft =
                            if (y2 > y1) prevRow[y2 - y1 - 1]
                            else 0
                        val sum = left + top - topLeft + matrix[x2][y2]
                        if (sum == target) result += 1
                        curRow[y2 - y1] = sum
                    }
                    for (i in curRow.indices) prevRow[i] = curRow[i]
                }
            }
        }

        return result
    }
}
