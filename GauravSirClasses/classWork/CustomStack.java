public class CustomStack {
    private int []arr;
    private int i;
    private int size;
    public CustomStack(){
        arr=new int [100];
        i=0;
        size=0;
    }
    public void push(int value){
        arr[i]=value;
        i++;
        size++;
    }
    public int peek(){
        return arr[i-1];
    }
    public int pop(){
        int val=peek();
        arr[i-1]=0;
        i--;
        size--;
        return val;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void display(){
        for(int j=0;j<i;j++){
            System.out.print(arr[j]+" ");
        }
    }
    // main func is ai generated
    public static void main(String[] args) {
        System.out.println("Creating a new custom stack...");
        CustomStack myStack = new CustomStack();

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
        myStack.display();

        System.out.println("\n\n--- Popping elements ---");
        System.out.println("Popped: " + myStack.pop()); // Pops 30
        System.out.println("Top element is now: " + myStack.peek()); // 20
        System.out.println("Popped: " + myStack.pop()); // Pops 20
        System.out.println("Top element is now: " + myStack.peek()); // 10
        System.out.println("Popped: " + myStack.pop()); // Pops 10

        // Test isEmpty and size on an empty stack again
        System.out.println("\nIs stack empty? " + myStack.isEmpty());
        System.out.println("Stack size: " + myStack.size());
    }
}

