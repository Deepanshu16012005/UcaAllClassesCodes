import java.util.*;
class Cycle{
  public static boolean checkCycleUndirectedDfs(Graph g){
    Set<Integer> visited = new HashSet<>();
    for(Integer childNode : g.edgeMap.keySet()){
      if(!visited.contains(childNode)){
        if(dfsHelperUn(childNode, null, visited, g)){
	  return true;
	}
      }
    }
    return false;
  }

  private static boolean dfsHelperUn(Integer current, Integer parent, Set<Integer> visited, Graph g){
    visited.add(current);
    for(Integer childNode : g.edgeMap.getOrDefault(current, Collections.emptySet())){
      if(!visited.contains(childNode)){
        if(dfsHelperUn(childNode, current, visited, g)){
	  return true;
	}
      }else if(childNode != parent){
        return true;
      }
    }
    return false;
  }
  
  public static boolean checkCycleUndirectedBfs(Graph g){
    Set<Integer> visited = new HashSet<>();
    for(Integer node : g.edgeMap.keySet()){
      if(!visited.contains(node)){
        if(bfsHelperUn(node, visited, g)){
	  return true;
	}
      }
    }
    return false;
  }
  private static boolean bfsHelperUn(Integer current, Set<Integer>visited, Graph g){
    Queue<Integer[]> queue = new LinkedList<>();
    queue.add(new Integer[] {current, null});
    visited.add(current);
    while(!queue.isEmpty()){
      Integer[] pair = queue.poll();
      Integer node = pair[0];
      Integer parent = pair[1];
      for(Integer childNode : g.edgeMap.getOrDefault(node, Collections.emptySet())){
        if(childNode != parent){
	  if(visited.contains(childNode)){
	    return true;
	  }
	  visited.add(childNode);
	  queue.add(new Integer[] {childNode, node});
	}
      }
    }
    return false;
  }

  public static boolean checkCycleDirectedDfs(Graph g){
    Set<Integer> visited = new HashSet<>();
    Set<Integer> stack = new HashSet<>();
    for(Integer node : g.edgeMap.keySet()){
      if(!visited.contains(node)){
        if(dfsHelperDi(node, visited, stack, g)){
          return true;
        }
      }
    }
    return false;
  }
  private static boolean dfsHelperDi(Integer parent, Set<Integer> visited, Set<Integer> stack, Graph g){
    visited.add(parent);
    stack.add(parent);
    for(Integer childNode : g.edgeMap.getOrDefault(parent, Collections.emptySet())){
      if(!visited.contains(childNode)){
        if(dfsHelperDi(childNode, visited, stack, g)){
	  return true;
	}
      }
      else if(stack.contains(childNode)){
        return true;
      }  
    }
    stack.remove(parent);
    return false;
  }

  public static void main(String[] args){
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
    System.out.println(checkCycleDirectedDfs(g));
  }
}


