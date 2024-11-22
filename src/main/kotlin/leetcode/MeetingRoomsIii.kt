package leetcode

import java.util.PriorityQueue
import kotlin.math.max

class MeetingRoomsIii {
    // There is a very sneaky trap here. We need to keep track of curTime. Quoting a LeetCode comment.
    // Say we have rooms {100, 1} and {100, 2} in busy, and two meetings starting at 50 and 60.
    // For the first meeting (50), we will use room 1 and adjust the starting time to 100. We also put room 2 in avail.
    // For the second meeting (60), we will take room 2 from avail, not knowing that the start time should be also adjusted to 100.
    fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
        meetings.sortBy { it[0] }
        val freeRooms = PriorityQueue<Int>().apply { addAll(0..<n) }
        val occupiedRooms = PriorityQueue<Pair</* room number */ Int, /* end time */ Long>>(compareBy { it.second })
        val meetingCounts = IntArray(n) { 0 }
        var curTime = meetings[0][0].toLong()
        for (meeting in meetings) {
            curTime = max(curTime, meeting[0].toLong())
            while (occupiedRooms.isNotEmpty() && occupiedRooms.peek().second <= curTime) freeRooms.add(occupiedRooms.poll().first)
            if (freeRooms.isEmpty()) curTime = occupiedRooms.peek().second
            while (occupiedRooms.isNotEmpty() && occupiedRooms.peek().second <= curTime) freeRooms.add(occupiedRooms.poll().first)
            val room = freeRooms.poll()
            meetingCounts[room] += 1
            occupiedRooms.add(Pair(room, curTime + meeting[1] - meeting[0]))
        }
        var result = 0
        for (i in 1..<n) if (meetingCounts[i] > meetingCounts[result]) result = i
        return result
    }
}
