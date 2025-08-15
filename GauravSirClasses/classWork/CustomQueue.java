

public class CustomQueue {
    public static void main(String[] args) {
       // CustomQueue queue = new CustomQueue();
       // System.out.println(queue.isEmpty());
        //queue.add(45);
        //queue.add(60);
        //queue.add(45);
        //queue.add(60);
        //queue.add(45);
        //queue.add(60);
        //System.out.println(queue.peek());
        //queue.add(45);
        //queue.add(60);
        //queue.add(45);
        //queue.add(60);
        //System.out.println(queue.remove());
        //System.out.println("front element "+queue.peek());
        //System.out.println(queue.remove())
	//;
	System.out.println("Creating a new custom queue...");
        CustomQueue myQueue = new CustomQueue();

        // Test isEmpty and size on an empty queue
        System.out.println("Is queue empty? " + myQueue.isEmpty()); // true
        System.out.println("Queue size: " + myQueue.size());       // 0

        System.out.println("\n--- Enqueuing elements ---");
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(30);

        // Test isEmpty and size after enqueuing
        System.out.println("\nIs queue empty? " + myQueue.isEmpty()); // false
        System.out.println("Queue size: " + myQueue.size());       // 3

        System.out.println("\n--- Peeking at the front element ---");
        System.out.println("Front element is: " + myQueue.peek()); // 10

        System.out.println("\n--- Dequeuing elements ---");
        myQueue.dequeue(); // Dequeues 10
        System.out.println("Front element is now: " + myQueue.peek()); // 20
        myQueue.dequeue(); // Dequeues 20
        System.out.println("Front element is now: " + myQueue.peek()); // 30
        myQueue.dequeue(); // Dequeues 30

        // Test isEmpty and size on an empty queue again
        System.out.println("\nIs queue empty? " + myQueue.isEmpty()); // true
        System.out.println("Queue size: " + myQueue.size()); 
    }
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head; // Head of the queue
    private Node tail;  // Tail of the queue
    private int size ;
    public CustomQueue() {
        head = null;
        tail = null;
	size=0;
    }

    // Add element to tail of queue
    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            // Empty queue
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
	size++;
    }

    // Remove element from head of queue
    public int dequeue() {
        int result = head.data;
        head = head.next;
        if (head == null) {
            // Queue became empty
            tail = null;
        }
	size--;
        return result;
    }
    public int size(){
        return size;	    
    }

    // Get head element without removing
    public int peek() {
        return head.data;
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return head == null;
    }

}


