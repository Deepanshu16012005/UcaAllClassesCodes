class MainPriorityQueue {
  public static void main(String[] args) {
  System.out.println("--- Initializing Priority Queue ---");
  PriorityQueueImplementation pq = new PriorityQueueImplementation();

  // Test isEmpty() on a new queue
  System.out.println("Is queue empty? " + pq.isEmpty()); // Should be true
  System.out.println("Initial array capacity: " + pq.arr.length);
  pq.printQueue(); // Should print nothing
  System.out.println("\n");

  // --- Test 1: Basic `add` operations ---
  System.out.println("--- Testing Basic Add Operations ---");
  System.out.println("Adding 10, 20, 5...");
  pq.add(10);
  pq.add(20); // 20 should become the new root
  pq.add(5);

  System.out.print("Queue state: ");
  pq.printQueue(); // Expected: 20 10 5 (or 20 5 10)
  System.out.println("\nIs queue empty? " + pq.isEmpty()); // Should be false
  System.out.println("Current element count (n): " + pq.n);
  System.out.println("Array capacity: " + pq.arr.length);
  System.out.println("\n");

  // --- Test 2: Triggering the "Grow" Resize ---
  // The initial array has a length of 6, so it can hold 5 elements (at indices 1-5).
  // Adding two more elements will fill it. The third one will trigger the resize.
  System.out.println("--- Testing Grow Resize ---");
  System.out.println("Filling the array to capacity...");
  pq.add(15);
  pq.add(18);

  System.out.println("Array is now full (n = 5).");
  System.out.print("Queue state: ");
  pq.printQueue(); // Expected: 20 18 5 10 15 (or similar valid heap)
  System.out.println("\nCurrent element count (n): " + pq.n);
  System.out.println("Array capacity BEFORE resize: " + pq.arr.length);

  System.out.println("\nAdding element 30, which will trigger resize...");
  pq.add(30); // This call to add() will resize the array from 6 to 12.

  System.out.print("Queue state: ");
  pq.printQueue();
  System.out.println("\nCurrent element count (n): " + pq.n);
  System.out.println("Array capacity AFTER resize: " + pq.arr.length); // Should be 12
  System.out.println("\n");

  // --- Test 3: Triggering the "Shrink" Resize ---
  // The array length is now 12. The shrink condition is n == arr.length / 4,
  // which is n == 3. We currently have n = 6.
  // We will poll() elements until n becomes 3, which will trigger the shrink.
  System.out.println("--- Testing Shrink Resize ---");
  System.out.println("Polling elements to trigger shrink...");

  int max1 = pq.poll(); // n becomes 5
  System.out.println("Polled: " + max1 + ". New count (n): " + pq.n);
  int max2 = pq.poll(); // n becomes 4
  System.out.println("Polled: " + max2 + ". New count (n): " + pq.n);

  System.out.println("\nQueue now has 4 elements. Capacity is still 12.");
  System.out.print("Queue state: ");
  pq.printQueue();
  System.out.println("\nArray capacity BEFORE resize: " + pq.arr.length);

  System.out.println("\nPolling one more element. n will become 3, triggering resize...");
  int max3 = pq.poll(); // This call to poll() will resize the array from 12 to 6.
  System.out.println("Polled: " + max3 + ".");

  System.out.print("Queue state: ");
  pq.printQueue();
  System.out.println("\nCurrent element count (n): " + pq.n);
  System.out.println("Array capacity AFTER resize: " + pq.arr.length); // Should be 6
  System.out.println("\n");

  // --- Test 4: Emptying the Queue ---
  System.out.println("--- Emptying the Queue ---");
  while (!pq.isEmpty()) {
    System.out.println("Polled: " + pq.poll() + ". Remaining elements: " + pq.n);
  }

  System.out.println("\nIs queue empty? " + pq.isEmpty()); // Should be true
  System.out.print("Final queue state: ");
  pq.printQueue();
  System.out.println("\nTest complete.");
  }
  
}

