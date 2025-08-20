import java.util.*;
class Main{
    public static void main (String[] args){
        ProductTracker pt = new ProductTracker();
        pt.wishlit("a");
        pt.wishlit("a");
        pt.wishlit("a");
        pt.wishlit("a");
        pt.wishlit("b");pt.wishlit("b");pt.wishlit("b");
        pt.wishlit("c");pt.wishlit("c");
        System.out.println(pt.getMaxProduct());
        System.out.println(pt.getMinProduct());
        pt.delist("a");pt.delist("a");pt.delist("a");pt.delist("a");
        pt.delist("a");pt.delist("a");pt.delist("a");pt.delist("a");
        System.out.println(pt.getMaxProduct());
        System.out.println(pt.getMinProduct());
    }
}

//best everything in o(1) for o(1) always we use linked list
 
class ProductTracker {
    class Node{
        int freq;
	Node next;
	Node prev;
	List<String>products;
    }
    Node head;
    Node tail;
    public ProductTracker() {
        head=null;
	tail=null;
    }
    
    public void wishlit(String productName)  {
        
    }
    
    public void delist(String productName)  {
        
    }
    
    public String getMaxProduct()  {
    }
    
    public String getMinProduct()  {
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
