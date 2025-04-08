public class FindCycleLinkedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode (){};
        ListNode(int x) {
            val = x;
        }
    }
    public ListNode detectCycle(ListNode head) {
        //边界检测
        if(head == null || head.next == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null&&fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }

        if(fast == null || fast.next == null) return null;

        slow = head;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;

    }
}