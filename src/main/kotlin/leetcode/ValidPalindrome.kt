package leetcode

class ValidPalindrome {
    fun (Char).isAlphanumeric(): Boolean = isDigit() || isLetter()

    fun isPalindrome(s: String): Boolean {
        val n = s.length
        if (n == 0) return true

        var i = -1
        var j = n

        fun updateIndices(): Boolean {
            i += 1
            j -= 1

            while (!s[i].isAlphanumeric()) {
                i += 1
                if (i >= j) return false
            }
            while (!s[j].isAlphanumeric()) {
                j -= 1
                if (i >= j) return false
            }

            return i < j
        }

        while (true) {
            val ok = updateIndices()
            if (!ok) break

            if (s[i].lowercaseChar() != s[j].lowercaseChar()) return false
        }

        return true
    }
}
