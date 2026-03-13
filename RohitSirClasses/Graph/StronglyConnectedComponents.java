import java.util.*;

public class StronglyConnectedComponents {

    private int V;
    private List<Integer>[] graph;
    private List<Integer>[] reverseGraph;

    public StronglyConnectedComponents(int V) {
        this.V = V;
        graph = new ArrayList[V];
        reverseGraph = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }
    }

    // Add edge u -> v
    public void addEdge(int u, int v) {
        graph[u].add(v);
        reverseGraph[v].add(u); // reverse edge
    }

    // Step 1: DFS to fill stack by finish time
    private void fillOrder(int node, boolean[] visited, Stack<Integer> stack) {
        // Complete this.\
        visited[node] = true;
            for(Integer value : graph[node]){
                if(!visited[value]){
                    fillOrder(value,visited,stack);
                }
            }
        stack.push(node);
    }

    // Step 2: DFS on reversed graph
    private void dfsOnReverse(int node, boolean[] visited, List<Integer> component) {
        // Complete this.
        visited[node] = true;
        component.add(node);
            for(Integer value : reverseGraph[node]){
                if(!visited[value]){
                    dfsOnReverse(value,visited,component);
                }
            }
    }

    // Main function to find SCCs
    public List<List<Integer>> getSCCs() {
        // Complete this.
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visited= new boolean[graph.length];
        Stack<Integer>stack = new Stack<>();
        for(int i = 0;i<graph.length;i++){
            if(!visited[i]){
                fillOrder(i,visited,stack);
            }
        }
        visited= new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!visited[i]){
                List<Integer>Component = new ArrayList<>();
                dfsOnReverse(i,visited,Component);
                ans.add(Component);
            }
        }
        return ans;
    }

    // Driver
    public static void main(String[] args) {
        StronglyConnectedComponents scc = new StronglyConnectedComponents(5);

        scc.addEdge(1, 0);
        scc.addEdge(0, 2);
        scc.addEdge(2, 1);
        scc.addEdge(0, 3);
        scc.addEdge(3, 4);

        List<List<Integer>> result = scc.getSCCs();

        System.out.println("Strongly Connected Components:");
        for (List<Integer> comp : result) {
            System.out.println(comp);
        }
    }
}


