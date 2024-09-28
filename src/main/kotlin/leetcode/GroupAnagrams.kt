package leetcode

class GroupAnagrams {
    fun groupAnagrams(strs: Array<String>): List<List<String>> =
        strs.groupBy { String(it.toCharArray().sorted().toCharArray()) }.values.toList()
}
