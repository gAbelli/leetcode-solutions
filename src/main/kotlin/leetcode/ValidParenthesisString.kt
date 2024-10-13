package leetcode

import kotlin.math.max

class ValidParenthesisString {
    fun checkValidString(s: String): Boolean {
        var minOpen = 0
        var maxOpen = 0
        for (c in s) when (c) {
            '(' -> {
                minOpen++
                maxOpen++
            }

            ')' -> {
                if (maxOpen == 0) return false
                minOpen = max(0, minOpen - 1)
                maxOpen--
            }

            else -> {
                minOpen = max(0, minOpen - 1)
                maxOpen++
            }
        }

        return minOpen == 0
    }
}
