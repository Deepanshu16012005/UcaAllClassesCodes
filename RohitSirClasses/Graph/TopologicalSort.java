import java.util.*;
class TopologicalSort{
  // inorder map
  HashMap<Integer, Integer> inOrder;
  boolean cycle = false;
  public List<Integer> topoSortBfs(Graph g){
    inOrder = new HashMap<>();
    // all nodes for iteration
    Set<Integer> allNodes = new HashSet<>();
    for(int key: g.edgeMap.keySet()){
      allNodes.add(key);
      allNodes.addAll(g.edgeMap.get(key));
    }


    findInOrder(g.edgeMap, inOrder, allNodes);


    List<Integer> ans = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    
    
    for(int node: allNodes){
      if(inOrder.get(node) == 0){
        queue.add(node);
      }
    }


    while(!queue.isEmpty()){
      int removed = queue.poll();

      //values of removed in edgeMap
      Set<Integer> values = g.edgeMap.get(removed);
      if(values != null){
       for(int value: values){
        inOrder.put(value, inOrder.get(value) - 1);
	if(inOrder.get(value) == 0){
	  queue.add(value);
	}
       }
      }
      ans.add(removed);
    }
    if(ans.size() < allNodes.size()){
      cycle = true;
    }
    return ans; 
  }
  
  public boolean checkCycle(){
    return cycle;
  }

  private void findInOrder(Map<Integer, Set<Integer>> edgeMap, HashMap<Integer, Integer> inOrder, Set<Integer> allNodes){
    for(Set<Integer> values : edgeMap.values()){
      for(int value : values){
        if(!inOrder.containsKey(value)){
	  inOrder.put(value, 0);
	}
	inOrder.put(value, inOrder.get(value) + 1);
      }
    }
    for(int value : allNodes){
      if(!inOrder.containsKey(value)){
        inOrder.put(value, 0);
      }
    }
  }
  



    public static void main(String[] args) {

        // Create graph
        Graph g = new Graph();
        g.add(0,6,true);
        g.add(0,1,true);
        g.add(5,6,true);
        g.add(5,4,true);
	g.add(1,2,true);
	g.add(4,3,true);
	g.add(3,2,true);
	g.add(2,0,true);
        // Run topological sort
        TopologicalSort ts = new TopologicalSort();
        List<Integer> order = ts.topoSortBfs(g);
	System.out.println(ts.checkCycle());
        // Print result
        System.out.println("Topological Sort Order:");
        for (int x : order) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

}
