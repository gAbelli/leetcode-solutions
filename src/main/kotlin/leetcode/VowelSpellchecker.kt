package leetcode

class VowelSpellchecker {
    fun spellchecker(wordlist: Array<String>, queries: Array<String>): Array<String> {
        val index1 = wordlist.groupBy { it.replaceVowelsAndMakeLowercase() }
        val index2 = wordlist.groupBy { it.lowercase() }
        val index3 = wordlist.toSet()
        return Array<String>(queries.size) { i ->
            val query = queries[i]
            val result1 = index1[query.replaceVowelsAndMakeLowercase()] ?: return@Array ""
            val result2 = index2[query.lowercase()] ?: return@Array result1.first()
            if (index3.contains(query)) query else result2.first()
        }
    }

    private fun String.replaceVowelsAndMakeLowercase(): String =
        this.asSequence()
            .map { it.lowercaseChar() }
            .map {
                when (it) {
                    'a', 'e', 'i', 'o', 'u' -> '_'
                    else -> it
                }
            }
            .joinToString("")
}
