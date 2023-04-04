package TryLeetCode.preiod2;


class MyLinkedList {
    private int size;
    private ListNode head;
    // private MyLinkedList prev;
    public MyLinkedList() {
        size=0;
        head=new ListNode(0);
    }

    public int get(int index) {
        if(index < 0 || index > size){
            return -1;
        }
        int temp=index;
        ListNode tempNode=head;
        while(temp>0){
            tempNode=tempNode.next;
            temp--;
        }
        return tempNode.val;
    }

    public void addAtHead(int val) {
        if(size==0){
            head=new ListNode(val);
            size++;
            return;
        }
        size++;
        ListNode temp=new ListNode(val);
        temp.next=head;
        head=temp;
    }

    public void addAtTail(int val) {
        if(size==0){
            size++;
            head=new ListNode(val);
        }
        size++;
        ListNode temp=new ListNode(val);
        ListNode cur=head;
        while(cur.next!=null){
            cur=cur.next;
        }
        cur.next=temp;
    }

    public void addAtIndex(int index, int val) {
        if(size<index){
            return;
        }
        int temp=index;
        ListNode cur=head;
        while(temp>0){
            cur=cur.next;
            temp--;
        }
        size++;
        ListNode end=cur.next;
        cur.next=new ListNode(val);
        cur.next.next=end;
        cur=head;
        while (cur.next!=null){
            System.out.println(cur.val);
            cur=cur.next;
        }
    }

    public void deleteAtIndex(int index) {
        if(size<index){
            return;
        }
        int temp=index;
        ListNode cur=head;
        while(temp>0){
            cur=cur.next;
            temp--;
        }
        size--;
        cur.next= cur.next.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */