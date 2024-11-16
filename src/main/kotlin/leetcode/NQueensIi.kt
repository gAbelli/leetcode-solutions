package leetcode

class NQueensIi {
    fun totalNQueens(n: Int): Int {
        var result = 0
        val acc = mutableListOf<Int>()

        val occupiedCols = mutableSetOf<Int>()
        val occupiedDiags = mutableSetOf<Int>()
        val occupiedReverseDiags = mutableSetOf<Int>()

        fun helper(i: Int) {
            if (i == n) result += 1
            for (col in 0..<n) {
                if (
                    col !in occupiedCols
                    && (col - i) !in occupiedDiags
                    && (col + i) !in occupiedReverseDiags
                ) {
                    acc.add(col)
                    occupiedCols.add(col)
                    occupiedDiags.add(col - i)
                    occupiedReverseDiags.add(col + i)
                    helper(i + 1)
                    acc.removeLast()
                    occupiedCols.remove(col)
                    occupiedDiags.remove(col - i)
                    occupiedReverseDiags.remove(col + i)
                }
            }
        }

        helper(0)
        return result
    }
}
