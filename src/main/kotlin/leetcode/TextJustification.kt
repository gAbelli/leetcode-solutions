package leetcode

class TextJustification {
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        fun toStr(row: List<String>): String {
            if (row.size == 1) return row.first() + " ".repeat(maxWidth - row.first().length)
            val totalWhitespaces = maxWidth - row.sumOf { it.length }
            val div = totalWhitespaces / (row.size - 1)
            var rem = totalWhitespaces % (row.size - 1)
            var result = ""
            for ((index, word) in row.withIndex()) {
                result += word
                if (index != row.indices.last)
                    result += " ".repeat(div + (if (rem != 0) 1 else 0))
                if (rem != 0) rem--
            }
            return result
        }

        val result = mutableListOf<String>()
        var row = mutableListOf<String>()
        var rowLength = 0
        for (word in words) {
            var extraCharsCount = word.length + (if (rowLength == 0) 0 else 1)
            if (rowLength + extraCharsCount > maxWidth) {
                result.add(toStr(row))
                row = mutableListOf()
                rowLength = 0
            }
            extraCharsCount = word.length + (if (rowLength == 0) 0 else 1)
            row.add(word)
            rowLength += extraCharsCount
        }
        var lastLine = row.joinToString(separator = " ")
        lastLine += " ".repeat(maxWidth - lastLine.length)
        result.add(lastLine)

        return result
    }
}
