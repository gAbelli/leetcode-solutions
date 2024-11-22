package leetcode

class StudentAttendanceRecordIi {
    enum class LastTwoLetters {
        AA, AL, AP, LA, LL, LP, PA, PL, PP
    }

    private val MOD = 1_000_000_000 + 7
    private fun (Long).trim(): Long = (this % MOD + MOD) % MOD

    fun checkRecord(n: Int): Int {
        if (n == 1) return 3
        if (n == 2) return 8
        val prev = Array(2) { LongArray(9) { 0 } }
        // "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
        prev[0][LastTwoLetters.PP.ordinal] = 1
        prev[1][LastTwoLetters.AP.ordinal] = 1
        prev[1][LastTwoLetters.PA.ordinal] = 1
        prev[0][LastTwoLetters.LP.ordinal] = 1
        prev[0][LastTwoLetters.PL.ordinal] = 1
        prev[1][LastTwoLetters.AL.ordinal] = 1
        prev[1][LastTwoLetters.LA.ordinal] = 1
        prev[0][LastTwoLetters.LL.ordinal] = 1
        val cur = Array(2) { LongArray(9) { 0 } }
        for (i in 3..n) {
            cur[0][LastTwoLetters.AA.ordinal] = 0
            cur[0][LastTwoLetters.AL.ordinal] = 0
            cur[0][LastTwoLetters.AP.ordinal] = 0
            cur[0][LastTwoLetters.LA.ordinal] = 0
            cur[0][LastTwoLetters.LL.ordinal] = prev[0][LastTwoLetters.PL.ordinal]
            cur[0][LastTwoLetters.LP.ordinal] =
                (prev[0][LastTwoLetters.LL.ordinal] + prev[0][LastTwoLetters.PL.ordinal]).trim()
            cur[0][LastTwoLetters.PA.ordinal] = 0
            cur[0][LastTwoLetters.PL.ordinal] =
                (prev[0][LastTwoLetters.LP.ordinal] + prev[0][LastTwoLetters.PP.ordinal]).trim()
            cur[0][LastTwoLetters.PP.ordinal] =
                (prev[0][LastTwoLetters.LP.ordinal] + prev[0][LastTwoLetters.PP.ordinal]).trim()

            cur[1][LastTwoLetters.AA.ordinal] = 0
            cur[1][LastTwoLetters.AL.ordinal] =
                (prev[1][LastTwoLetters.LA.ordinal] + prev[1][LastTwoLetters.PA.ordinal]).trim()
            cur[1][LastTwoLetters.AP.ordinal] =
                (prev[1][LastTwoLetters.LA.ordinal] + prev[1][LastTwoLetters.PA.ordinal]).trim()
            cur[1][LastTwoLetters.LA.ordinal] =
                (prev[0][LastTwoLetters.LL.ordinal] + prev[0][LastTwoLetters.PL.ordinal]).trim()
            cur[1][LastTwoLetters.LL.ordinal] =
                (prev[1][LastTwoLetters.PL.ordinal] + prev[1][LastTwoLetters.AL.ordinal]).trim()
            cur[1][LastTwoLetters.LP.ordinal] =
                (prev[1][LastTwoLetters.LL.ordinal] + prev[1][LastTwoLetters.PL.ordinal] + prev[1][LastTwoLetters.AL.ordinal]).trim()
            cur[1][LastTwoLetters.PA.ordinal] =
                (prev[0][LastTwoLetters.PP.ordinal] + prev[0][LastTwoLetters.LP.ordinal]).trim()
            cur[1][LastTwoLetters.PL.ordinal] =
                (prev[1][LastTwoLetters.LP.ordinal] + prev[1][LastTwoLetters.PP.ordinal] + prev[1][LastTwoLetters.AP.ordinal]).trim()
            cur[1][LastTwoLetters.PP.ordinal] =
                (prev[1][LastTwoLetters.LP.ordinal] + prev[1][LastTwoLetters.PP.ordinal] + prev[1][LastTwoLetters.AP.ordinal]).trim()

            for (j in 0..1) for (k in 0..<9) prev[j][k] = cur[j][k]
        }
        return prev.sumOf { it.sum().trim() }.trim().toInt()
    }
}
