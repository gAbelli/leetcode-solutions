package leetcode

class RemoveAllOccurrencesOfASubstring {
    fun removeOccurrences(s: String, part: String): String {
        val stack = mutableListOf<Char>()

        fun endsWithPart(): Boolean {
            if (stack.size < part.length) return false
            for (i in 1..part.length) if (stack[stack.size - i] != part[part.length - i]) return false
            return true
        }

        for (c in s) {
            stack.add(c)
            if (endsWithPart()) for (i in 0..<part.length) stack.removeLast()
        }

        return stack.joinToString("")
    }
}
