package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PaintingTheWallsTest {
    @Test
    fun testPaintWalls() {
        val p = PaintingTheWalls()
        val cost = intArrayOf(9, 9, 5)
        val time = intArrayOf(1, 1, 1)
        assertEquals(14, p.paintWalls(cost, time))
    }
}
