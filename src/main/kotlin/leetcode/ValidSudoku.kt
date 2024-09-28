package leetcode

class ValidSudoku {
    fun clearFound(found: BooleanArray) {
        for (i in 0..8) {
            found[i] = false
        }
    }

    fun toInt(digit: Char): Int = digit - '1'

    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val found = BooleanArray(9) { false }

        // rows
        for (i in 0..8) {
            for (j in 0..8) {
                if (board[i][j] == '.') continue
                if (found[toInt(board[i][j])]) {
                    return false
                }
                found[toInt(board[i][j])] = true
            }
            clearFound(found)
        }

        // columns
        for (j in 0..8) {
            for (i in 0..8) {
                if (board[i][j] == '.') continue
                if (found[toInt(board[i][j])]) {
                    return false
                }
                found[toInt(board[i][j])] = true
            }
            clearFound(found)
        }

        // boxes
        for (boxRow in 0..2) {
            for (boxColumn in 0..2) {
                val startI = boxRow * 3
                val startJ = boxColumn * 3
                for (i in startI..startI + 2) {
                    for (j in startJ..startJ + 2) {
                        if (board[i][j] == '.') continue
                        if (found[toInt(board[i][j])]) {
                            return false
                        }
                        found[toInt(board[i][j])] = true
                    }
                }
                clearFound(found)
            }
        }

        return true
    }
}
