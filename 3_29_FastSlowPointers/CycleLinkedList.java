public class CycleLinkedList {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {this.val = x;}
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public boolean CycleDetect(ListNode head) {
        if (head == null  || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) return true;
        }
        return false;
    }

    public ListNode findCycleNode(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        if(fast == null || fast.next == null) return null;
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    public static void main(String[] args) {
        CycleLinkedList cycleLinkedList = new CycleLinkedList();
        ListNode head2 = cycleLinkedList.new ListNode(1);
        head2.next = cycleLinkedList.new ListNode(2);
        head2.next.next = cycleLinkedList.new ListNode(3);
        head2.next.next.next = cycleLinkedList.new ListNode(4);
        head2.next.next.next.next = head2.next; // 4 指向 2，形成环
        System.out.println("有环链表检测结果: " + cycleLinkedList.CycleDetect(head2)); // 应返回 true
        System.out.println("有环链表环起始点: " + cycleLinkedList.findCycleNode(head2).val);
    }


}
