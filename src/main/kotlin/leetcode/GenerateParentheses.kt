package leetcode

class GenerateParentheses {
    fun generateParenthesis(n: Int): List<String> {
        val acc = mutableListOf<Char>()
        val result = mutableListOf<String>()

        fun helper(open: Int, closed: Int) {
            if (open == n && closed == n) {
                result.add(acc.joinToString(""))
                return
            }

            if (open < n) {
                acc.add('(')
                helper(open + 1, closed)
                acc.removeLast()
            }
            if (closed < open) {
                acc.add(')')
                helper(open, closed + 1)
                acc.removeLast()
            }
        }

        helper(0, 0)
        return result
    }
}
