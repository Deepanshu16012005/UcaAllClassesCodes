import java.util.*;
class Graph {
  //Adjacency list 
  Map<Integer, Set<Integer>> edgeMap;
  
  Graph(){
    edgeMap = new HashMap<>();
  }

  void add(int a, int b, boolean direction){
    // direction -> true =>  a->b
    // direction -> false =>  a->b , b->a
    if(direction){
      edgeMap.computeIfAbsent(a, k -> new HashSet<>()).add(b);
    }else{
      edgeMap.computeIfAbsent(a, k -> new HashSet<>()).add(b);
      edgeMap.computeIfAbsent(b, k -> new HashSet<>()).add(a);
    }
  }

  void printList(){
    for(Integer node : edgeMap.keySet()){
      System.out.print(node + "-> ");
      System.out.println(edgeMap.get(node));
    }
  }

  void BFS(int root){
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.add(root);
    visited.add(root);
    System.out.print("BFS -> ");
    while(!queue.isEmpty()){
      int parent = queue.poll();
      for(int childNode : edgeMap.getOrDefault(parent, Collections.emptySet())){
        if(!visited.contains(childNode)){
	  queue.add(childNode);
	  visited.add(childNode);
	}
      }
      System.out.print(parent + " ");
    }
    System.out.println();
  }

  void DFS(int root){
    Set<Integer> visited = new HashSet<>();
    System.out.print("DFS -> ");
    helperDFS(root, visited);
    System.out.println();
  }

  private void helperDFS(int parent, Set<Integer> visited){
    System.out.print(parent + " ");
    visited.add(parent);
    for(int childNode : edgeMap.getOrDefault(parent, Collections.emptySet())){
      if(!visited.contains(childNode))
        helperDFS(childNode, visited);
    }
  }

  public static void main(String args[]) {
    Graph g = new Graph();
    g.add(0, 1, true);
    g.add(0, 2, true);
    g.add(1, 3, true);
    g.add(2, 8, true);
    g.add(3, 4, true);
    g.add(8, 4, true);
    g.add(4, 5, true);
    g.add(4, 7, true);
    g.add(5, 6, true);
    g.add(7, 6, true);
    //g.printList();
    g.BFS(0);
    g.DFS(0);
  }
}
