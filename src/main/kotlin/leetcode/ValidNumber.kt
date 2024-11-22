package leetcode

class ValidNumber {
    fun isNumber(s: String): Boolean {
        fun parseUnsignedInteger(i: Int): Int? {
            var j = i
            while (j < s.length && s[j].isDigit()) j += 1
            return if (j == i) null else j
        }

        fun parseInteger(i: Int): Int? =
            if (i >= s.length) null
            else if (s[i] == '+' || s[i] == '-') parseUnsignedInteger(i + 1)
            else parseUnsignedInteger(i)

        fun parseUnsignedDecimal(i: Int): Int? {
            var j = i
            while (j < s.length && s[j].isDigit()) j += 1
            if (j < s.length && s[j] == '.') j += 1
            else return null
            while (j < s.length && s[j].isDigit()) j += 1
            return if (j > i + 1) j else null
        }

        fun parseDecimal(i: Int): Int? =
            if (i >= s.length) null
            else if (s[i] == '+' || s[i] == '-') parseUnsignedDecimal(i + 1)
            else parseUnsignedDecimal(i)

        fun parseExponent(i: Int): Int? =
            if (i < s.length && (s[i] == 'e' || s[i] == 'E')) parseInteger(i + 1)
            else null

        return parseInteger(0)?.let { parseExponent(it) ?: it } == s.length
                || parseDecimal(0)?.let { parseExponent(it) ?: it } == s.length
    }
}
