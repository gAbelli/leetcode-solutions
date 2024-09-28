package leetcode

import leetcode.datastructures.ListNode

class RemoveNthNodeFromEndOfList {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0).apply { next = head }
        var slow: ListNode? = dummy
        var fast: ListNode? = dummy
        for (i in 0..<n) fast = fast!!.next
        while (fast!!.next != null) {
            fast = fast.next
            slow = slow!!.next
        }
        slow!!.next = slow.next!!.next
        return dummy.next
    }
}
