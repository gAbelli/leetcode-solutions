package leetcode

class MinStack {
    val stack = mutableListOf<Int>()
    val minStack = mutableListOf<Int>()

    fun push(`val`: Int) = stack.add(`val`).also {
        if (minStack.isEmpty() || `val` <= minStack.last()) minStack.add(`val`)
    }


    fun pop() = stack.removeLast().also {
        if (it == minStack.last()) minStack.removeLast()
    }


    fun top(): Int = stack.last()

    fun getMin(): Int = minStack.last()
}
