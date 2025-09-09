import java.util.*;
//best everything in o(1) for o(1) always we use linked list
class Main {
    public static void main(String[] args) {
        ProductTracker pt = new ProductTracker();

        // Add products
        pt.wishlit("apple");
        pt.wishlit("apple");
        pt.wishlit("banana");
        pt.wishlit("banana");
        pt.wishlit("banana");
        pt.wishlit("orange");

        // Check max/min after inserts
        System.out.println("Max product (expect banana): " + pt.getMaxProduct());
        System.out.println("Min product (expect orange): " + pt.getMinProduct());

        // Add more apples to change order
        pt.wishlit("apple");
        pt.wishlit("apple");

        System.out.println("Max product (expect apple): " + pt.getMaxProduct());
        System.out.println("Min product (expect orange): " + pt.getMinProduct());

        // Remove oranges completely
        pt.delist("orange");
        pt.delist("orange"); // removing non-existing should do nothing

        System.out.println("After removing orange:");
        System.out.println("Max product: " + pt.getMaxProduct());
        System.out.println("Min product (expect banana): " + pt.getMinProduct());

        // Reduce banana
        pt.delist("banana");
        pt.delist("banana");

        System.out.println("After reducing banana:");
        System.out.println("Max product (expect apple): " + pt.getMaxProduct());
        System.out.println("Min product (expect banana): " + pt.getMinProduct());

        // Remove everything
        pt.delist("apple");
        pt.delist("apple");
        pt.delist("apple");
        pt.delist("apple");
        pt.delist("banana");

        System.out.println("After removing all:");
        System.out.println("Max product: " + pt.getMaxProduct());
        System.out.println("Min product: " + pt.getMinProduct());
    }
}
 
class ProductTracker {
    class Node{
        int freq;
	Node next;
	Node prev;
	List<String>products;
	Node(int i){
	  freq=i;
	  products = new ArrayList<>();
	}
    }
    HashMap<Integer,Node>nodeMap;
    HashMap<String,Integer>freqMap;
    Node head;
    Node tail;
    public ProductTracker() {
        head=null;
	tail=null;
	nodeMap= new HashMap<>();
	freqMap= new HashMap<>();
    }
    private void removeNode(Node node){
      if(node.prev==null){
        head=head.next;
        head.prev=null;
      }else{
        node.prev.next=node.next;
      }
      if(node.next==null){
        tail=tail.prev;
	tail.next=null;
      }else{
        node.next.prev=node.prev;
      }
    }
    private void addNode(Node ref , Node node){
      if(ref==null){
        node.next=head;
	if(head!=null){
	  head.prev=node;
	}
	head=node;
	if(tail==null){
	  tail=head;
	}
	return;
      }
      node.next=ref.next;
      ref.next=node;
      node.prev=ref;
      if(node.next!=null){
        node.next.prev=node;
      }else{
        tail=node;
      }
    } 
    public void wishlit(String productName)  {
      int oldFreq = freqMap.getOrDefault(productName,0);
      int newFreq = oldFreq + 1 ;
      if(head==null){
        Node node = new Node(newFreq);
	node.products.add(productName);
        head=node;
	if(tail==null){
	  tail=node;
	}
	freqMap.put(productName,newFreq);
        nodeMap.put(newFreq,node);
        return;
      }
      if(oldFreq>0){
        Node oldNode = nodeMap.get(oldFreq);
	oldNode.products.remove(productName);
	if(oldNode.products.isEmpty()){
	  removeNode(oldNode);
	  nodeMap.remove(oldFreq);
	}
      }
      freqMap.put(productName,newFreq);
      Node newNode = nodeMap.get(newFreq);
      if(newNode==null){
        newNode = new Node(newFreq);
	Node prevNode=(oldFreq>0) ? nodeMap.get(oldFreq) : null;
	addNode(prevNode,newNode);
      }
      newNode.products.add(productName);
      nodeMap.put(newFreq,newNode);
    }



    public void delist(String productName){
      if(head==null){
        return ;
      }
      if(!freqMap.containsKey(productName)){
        return ;
      }
      int oldFreq = freqMap.get(productName);
      int newFreq = oldFreq-1;
      Node oldNode = nodeMap.get(oldFreq);
      oldNode.products.remove(productName);
      if(oldNode.products.isEmpty()){
        removeNode(oldNode);
	nodeMap.remove(oldFreq);
      }
      if(newFreq>0){
        freqMap.put(productName,newFreq);
	Node newNode = nodeMap.get(newFreq);
	if(newNode==null){
	  newNode = new Node(newFreq);
	  Node prevNode = (newFreq-1>0) ? nodeMap.get(newFreq-1) : null;
	  addNode(prevNode,newNode);
	}
	newNode.products.add(productName);
	nodeMap.put(newFreq,newNode);
      }
    }

    
    
    public String getMaxProduct()  {
      if(tail==null){
        return null;
      }
      return tail.products.get(0);
    }
    
    public String getMinProduct()  {
      if(head==null){
        return null;
      }
      return head.products.get(0);
    }
}


// simple approach is better than this
class ProductTrackerWithPriorityQueue {

    Map<String, Integer> productCounter;
    PriorityQueue<String> maxHeap;
    PriorityQueue<String> minHeap;
    
    
    public ProductTrackerWithPriorityQueue() {
        productCounter = new HashMap<>();
        maxHeap = new PriorityQueue<String>((a,b) -> {
            return Integer.compare(productCounter.getOrDefault(b, 0), productCounter.getOrDefault(a, 0));
        });
        
        minHeap = new PriorityQueue<String>((a,b)-> {
            return Integer.compare(productCounter.getOrDefault(a, 0), productCounter.getOrDefault(b, 0));
        });
    }
    
    public void wishlit(String productName)  {
        
        if (productCounter.containsKey(productName)) {
            maxHeap.remove(productName); // N
            minHeap.remove(productName); // N
            
        }
    
        productCounter.merge(productName, 1, Integer::sum); // 0(1)
        maxHeap.add(productName); // logN 
        minHeap.add(productName); // logN
    }
    
    public void delist(String productName)  {
        if (productCounter.containsKey(productName)) {
            
            maxHeap.remove(productName); // N
            minHeap.remove(productName); // N
            
            int currCount = productCounter.merge(productName, -1, Integer::sum);
            if (currCount == 0) {
                productCounter.remove(productName);
            } else {
                 maxHeap.add(productName); // logN 
                minHeap.add(productName); // lo
            }
        }
    }
    
    public String getMaxProduct()  {
        return maxHeap.peek();
    }
    
    public String getMinProduct()  {
        return minHeap.peek();
    }
}

// good approach // simple 
class ProductTrackerSimpleHash {

    Map<String, Integer> productCounter;
    
    public ProductTrackerSimpleHash() {
        productCounter = new HashMap<>();
    }
    
    public void wishlit(String productName) {
        productCounter.merge(productName, 1, Integer::sum);
    }
    
    public void delist(String productName) {
        
        if (productCounter.containsKey(productName)) {
            int currCount = productCounter.merge(productName, -1, Integer::sum);
            if (currCount == 0) {
                productCounter.remove(productName);
            }
        }
    }
    
    public String getMaxProduct() {
        String maxKey = "";
        int maxCount = -1;
        for (Map.Entry<String, Integer> entry : productCounter.entrySet()) {
            if (maxCount < entry.getValue()) {
                maxCount = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        
        return maxKey;
    }
    
    public String getMinProduct() {
        String minKey = "";
        int minCount = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : productCounter.entrySet()) {
            if (minCount > entry.getValue()) {
                minCount = entry.getValue();
                minKey = entry.getKey();
            }
        }
        
        return minKey;
    }
}

// my approach worst i think
class ProductTrackerWorst {
    HashMap<String,Integer> map;
    PriorityQueue<Map.Entry<String,Integer>> minHeap;
    PriorityQueue<Map.Entry<String,Integer>> maxHeap;
    public ProductTrackerWorst() {
        map = new HashMap<>();
        minHeap = new PriorityQueue<>((a,b)->{
            return a.getValue() - b.getValue();
        });
        maxHeap = new PriorityQueue<>((a,b)->{
            return b.getValue() - a.getValue();
        });
    }
    
    public void wishlit(String productName) {
        map.put(productName,map.getOrDefault(productName,0)+1);
        addHeaps();
    }
    private void addHeaps(){
        minHeap.clear();
        maxHeap.clear();
        minHeap.addAll(map.entrySet());
        maxHeap.addAll(map.entrySet());
    }
    public void delist(String productName) {
        if(!map.containsKey(productName)){
            return;
        }
        
        map.put(productName,map.get(productName)-1);
        if(map.get(productName)==0){
            map.remove(productName);
        }
        addHeaps();
    }
    
    public String getMaxProduct() {
        return maxHeap.peek().getKey();
    }
    
    public String getMinProduct() {
        return minHeap.peek().getKey();
    }
}
