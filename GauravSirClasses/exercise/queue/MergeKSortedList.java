import java.util.*;
class MergeKSortedList {
  public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }	
  public static ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue <ListNode> pq = new PriorityQueue<>((a,b)->{
      return a.val - b.val ;      
    });
    for(int i=0;i<lists.length;i++){
      ListNode head = lists[i];
      while(head!=null){
        pq.add(head);
	head=head.next;
      }
    }
    ListNode temp = new ListNode(0);
    ListNode dummy=temp;
    while(!pq.isEmpty()){
      temp.next=pq.poll();
      temp=temp.next;
    }
    return dummy.next;
  }


  // main func ai generated 
  
  public static void main(String[] args) {
       // Solution solution = new Solution();

        // --- Test Case 1: Standard Case ---
        System.out.println("--- Test Case 1: Standard Case ---");
        ListNode l1 = createLinkedList(new int[]{1, 4, 5});
        ListNode l2 = createLinkedList(new int[]{1, 3, 4});
        ListNode l3 = createLinkedList(new int[]{2, 6});
        ListNode[] lists1 = {l1, l2, l3};
        ListNode result1 = mergeKLists(lists1);
        printLinkedList("Merged list: ", result1);
        System.out.println("Expected:    [1, 1, 2, 3, 4, 4, 5, 6]");

        System.out.println("\n----------------------------------------\n");

        // --- Test Case 2: Input with empty/null lists ---
        System.out.println("--- Test Case 2: Contains Empty/Null Lists ---");
        ListNode l4 = createLinkedList(new int[]{});
        ListNode l5 = createLinkedList(new int[]{1, 2, 9});
        ListNode[] lists2 = {l4, l5, null};
        ListNode result2 = mergeKLists(lists2);
        printLinkedList("Merged list: ", result2);
        System.out.println("Expected:    [1, 2, 9]");

        System.out.println("\n----------------------------------------\n");

        // --- Test Case 3: Empty input array ---
        System.out.println("--- Test Case 3: Empty Input Array ---");
        ListNode[] lists3 = {};
        ListNode result3 = mergeKLists(lists3);
        printLinkedList("Merged list: ", result3);
        System.out.println("Expected:    []");

        System.out.println("\n----------------------------------------\n");

        // --- Test Case 4: All lists are empty/null ---
        System.out.println("--- Test Case 4: All Lists Are Empty/Null ---");
        ListNode[] lists4 = {null, createLinkedList(new int[]{}), null};
        ListNode result4 = mergeKLists(lists4);
        printLinkedList("Merged list: ", result4);
        System.out.println("Expected:    []");

        System.out.println("\n----------------------------------------\n");

        // --- Test Case 5: Single list in the array ---
        System.out.println("--- Test Case 5: Single List ---");
        ListNode l7 = createLinkedList(new int[]{5, 10, 15});
        ListNode[] lists5 = {l7};
        ListNode result5 = mergeKLists(lists5);
        printLinkedList("Merged list: ", result5);
        System.out.println("Expected:    [5, 10, 15]");
    }

    /**
     * Helper function to create a linked list from an integer array.
     */
    public static ListNode createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * Helper function to print a linked list to the console.
     */
    public static void printLinkedList(String prefix, ListNode head) {
        System.out.print(prefix);
        if (head == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
