package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TaskSchedulerTest {
    @Test
    fun testTaskSchelduler() {
        assertEquals(
            8,
            TaskScheduler().leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 2)
        )

    }

    @Test
    fun testTaskSchelduler2() {
        assertEquals(
            12,
            TaskScheduler().leastInterval(charArrayOf('A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'), 1)
        )

    }
}
