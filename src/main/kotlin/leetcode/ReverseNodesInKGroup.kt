package leetcode

import leetcode.datastructures.ListNode

class ReverseNodesInKGroup {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        tailrec fun helper(head: ListNode?, prevTail: ListNode) {
            var fast = head
            for (i in 0..<k) {
                if (fast == null) {
                    prevTail.next = head
                    return
                }
                fast = fast.next
            }
            var prev: ListNode? = null
            var cur = head
            var next = head
            while (cur != fast) {
                next = cur!!.next
                cur.next = prev
                prev = cur
                cur = next
            }
            prevTail.next = prev
            helper(cur, head!!)
        }

        val dummy = ListNode(0).apply { next = head }
        helper(dummy.next, dummy)
        return dummy.next
    }
}
