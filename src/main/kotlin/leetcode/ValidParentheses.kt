package leetcode

class ValidParentheses {
    fun matching(opening: Char): Char = when (opening) {
        '(' -> ')'
        '[' -> ']'
        '{' -> '}'
        else -> throw Exception("")
    }

    fun isValid(s: String): Boolean {
        val stack = mutableListOf<Char>()
        for (c in s) {
            when (c) {
                '(', '[', '{' -> stack.add(c)
                else -> {
                    if (stack.isEmpty()) return false
                    val top = stack.removeLast()
                    if (matching(top) != c) return false
                }
            }
        }
        return stack.isEmpty()
    }
}
