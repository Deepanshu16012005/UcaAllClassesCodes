public class CustomStack2 {
    //using linked list
    private Node head;
    private int size;
    public CustomStack2(){
        head=null;
        size=0;
    }
    public void push(int value){
        Node temp=new Node(value);
        temp.next= head;
        temp=head;
        size++;
    }
    public int peek(){
	if(head==null){
	    return Integer.MIN_VALUE;
	}
        return head.value;
    }
    public int pop(){
	if(isEmpty()){
	    return Integer.MIN_VALUE;
	}
        int val= head.value;
        if(head==null){
            return hashCode();
        }
        head=head.next;
        size--;
        return val;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
 
    private class Node{
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
    public static void main(String[] args) {
        System.out.println("Creating a new custom stack with linked list...");
        CustomStack2 myStack = new CustomStack2();

        // Test isEmpty and size on an empty stack
        System.out.println("Is stack empty? " + myStack.isEmpty());
        System.out.println("Stack size: " + myStack.size());

        System.out.println("\n--- Pushing elements ---");
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);

        // Test isEmpty and size after pushing
        System.out.println("\nIs stack empty? " + myStack.isEmpty());
        System.out.println("Stack size: " + myStack.size());

        System.out.println("\n--- Peeking at the top element ---");
        System.out.println("Top element is: " + myStack.peek());

        System.out.println("\n--- Displaying stack elements ---");

        System.out.println("\n--- Popping elements ---");
        myStack.pop();
        System.out.println("Top element is now: " + myStack.peek());
        myStack.pop();
        System.out.println("Top element is now: " + myStack.peek());
        myStack.pop();

        // Test isEmpty and size on an empty stack again
        System.out.println("\nIs stack empty? " + myStack.isEmpty());
        System.out.println("Stack size: " + myStack.size());
    }
}

