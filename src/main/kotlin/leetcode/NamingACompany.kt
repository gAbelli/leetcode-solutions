package leetcode

class NamingACompany {
    fun distinctNames(ideas: Array<String>): Long {
        val charsBySuffix = buildMap<String, BooleanArray> {
            ideas.forEach {
                getOrPut(it.slice(1..<it.length)) { BooleanArray('z' - 'a' + 1) { false } }[it.first() - 'a'] = true
            }
        }
        var result = 0L
        for (first in 'a'..'z') {
            for (second in first + 1..'z') {
                val containingFirstButNotSecond =
                    charsBySuffix.count { it.value[first - 'a'] && !it.value[second - 'a'] }
                val containingSecondButNotFirst =
                    charsBySuffix.count { it.value[second - 'a'] && !it.value[first - 'a'] }
                result += containingFirstButNotSecond * containingSecondButNotFirst
            }
        }
        return result * 2
    }
}
