package leetcode

class ValidAnagram {
    fun toCharMap(s: String): Map<Char, Int> = s.groupingBy { it }.eachCount()

    fun isAnagram(s: String, t: String): Boolean = toCharMap(s) == toCharMap(t)
}
