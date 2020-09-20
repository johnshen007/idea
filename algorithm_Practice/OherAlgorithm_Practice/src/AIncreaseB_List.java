public class AIncreaseB_List {


    static  class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1== null)
            return l2;
        if(l2==null)
            return l1;
        ListNode dummyNode = new ListNode(0);
        ListNode head = dummyNode;
        int addone = 0;
        while(l1!=null||l2!=null||addone !=0){
            if(l1 == null) l1= new ListNode(0);
            if(l2 == null) l2= new ListNode(0);
            int sum = l1.val+l2.val+addone;
            int value = sum % 10;
            head.next = new ListNode(value);
            addone = sum/10;

            head = head.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        addTwoNumbers(l1,l2);
    }

}