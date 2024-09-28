package leetcode

class EvaluateReversePolishNotation {
    fun evalRPN(tokens: Array<String>): Int {
        val stack = mutableListOf<Int>()
        for (token in tokens) {
            when (token) {
                "+" -> stack.add(stack.removeLast() + stack.removeLast())
                "-" -> {
                    val second = stack.removeLast()
                    val first = stack.removeLast()
                    stack.add(first - second)
                }

                "*" -> stack.add(stack.removeLast() * stack.removeLast())
                "/" -> {
                    val second = stack.removeLast()
                    val first = stack.removeLast()
                    stack.add(first / second)
                }

                else -> stack.add(token.toInt())
            }
        }
        return stack.last()
    }
}
