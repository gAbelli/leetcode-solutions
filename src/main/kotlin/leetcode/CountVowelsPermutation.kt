package leetcode

class CountVowelsPermutation {
    fun (Long).trim(): Long = this % (1_000_000_000 + 7)

    fun countVowelPermutation(n: Int): Int {
        val memo = mutableMapOf<Pair<Int, Char>, Long>()
        fun helper(n: Int, c: Char): Long = memo.getOrPut(Pair(n, c)) {
            if (n <= 1) n.toLong()
            else when (c) {
                'a' -> helper(n - 1, 'e')
                'e' -> helper(n - 1, 'a') + helper(n - 1, 'i')
                'i' -> listOf('a', 'e', 'o', 'u').sumOf { helper(n - 1, it) }
                'o' -> helper(n - 1, 'i') + helper(n - 1, 'u')
                'u' -> helper(n - 1, 'a')
                else -> throw Exception("Unreachable")
            }.trim()
        }
        return listOf('a', 'e', 'i', 'o', 'u').sumOf { helper(n, it) }.trim().toInt()
    }
}
