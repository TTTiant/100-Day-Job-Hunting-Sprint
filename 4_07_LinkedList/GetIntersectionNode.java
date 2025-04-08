public class GetIntersectionNode {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;

        ListNode PA = headA;
        ListNode PB = headB;

        while(PA!=PB){
            PA = (PA == null) ? headB : PA.next;
            PB = (PB == null) ? headA : PB.next;
        }

        return PA;
    }

}
