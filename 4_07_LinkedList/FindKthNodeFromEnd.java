public class FindKthNodeFromEnd {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {

            val = x;
        }
    }
    public ListNode findKthNodeFromEnd(ListNode head, int k) {
        if (head == null) return null;
        ListNode left = head;
        ListNode right = head;
        for (int i = 0; i < k; i++) {
            right = right.next;
        }
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        return left;
    }
}
