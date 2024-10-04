package leetcode

class LetterCombinationsOfAPhoneNumber {
    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return listOf()
        val result = mutableListOf<String>()
        val acc = mutableListOf<Char>()

        fun helper(i: Int) {
            if (i == digits.length) {
                result.add(acc.joinToString(""))
                return
            }
            for (char in charsByDigit[digits[i]]!!) {
                acc.add(char)
                helper(i + 1)
                acc.removeLast()
            }
        }

        helper(0)
        return result
    }

    companion object {
        val charsByDigit = mapOf(
            '2' to listOf('a', 'b', 'c'),
            '3' to listOf('d', 'e', 'f'),
            '4' to listOf('g', 'h', 'i'),
            '5' to listOf('j', 'k', 'l'),
            '6' to listOf('m', 'n', 'o'),
            '7' to listOf('p', 'q', 'r', 's'),
            '8' to listOf('t', 'u', 'v'),
            '9' to listOf('w', 'x', 'y', 'z'),
        )
    }
}
