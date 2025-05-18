package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BasicCalculatorTest {
    @Test
    fun testBasicCalculator() {
        val basicCalculator = BasicCalculator()
        assertEquals(7, basicCalculator.calculate("1 + 4 * 3 / 2"))
        assertEquals(9, basicCalculator.calculate("(1 + --5) * -3 / -2"))
        assertEquals(7, basicCalculator.calculateInefficient("1 + 4 * 3 / 2"))
        assertEquals(9, basicCalculator.calculateInefficient("(1 + --5) * -3 / -2"))
    }
}
