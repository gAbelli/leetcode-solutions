package leetcode

class PalindromePartitioning {
    fun partition(s: String): List<List<String>> {
        val result = mutableListOf<List<String>>()
        val acc = mutableListOf<String>()

        fun helper(i: Int) {
            if (i == s.length) {
                result.add(acc.toList())
            }
            for (j in i..<s.length) {
                val substring = s.substring(i..j)
                if (substring.isPalindrome()) {
                    acc.add(substring)
                    helper(j + 1)
                    acc.removeLast()
                }
            }
        }

        helper(0)
        return result
    }

    private fun (String).isPalindrome() = this == this.reversed()
}
