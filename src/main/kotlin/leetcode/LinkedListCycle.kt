package leetcode

import leetcode.datastructures.ListNode

class LinkedListCycle {
    fun hasCycle(head: ListNode?): Boolean {
        var slow = head
        var fast = head
        while (fast != null && fast.next != null) {
            fast = fast.next!!.next
            slow = slow!!.next
            if (fast == slow) return true
        }
        return false
    }
}
