import java.util.*;
class Huffman{
  public static class Node{
    Character data;
    int cost;
    Node left;
    Node right;
    Node(Character data,int cost){
      this.data = data;
      this.cost = cost;
    }
  }
  public static void encoding(Map<Character,String> encoder, Node root, StringBuilder sb){
    if(root.left == null && root.right == null){
      encoder.put(root.data, sb.toString());
      return;
    }
    if(root.left != null){
      sb.append(0);
      encoding(encoder,root.left,sb);
      sb.deleteCharAt(sb.length()-1);
    }
    if(root.right != null){
      sb.append(1);
      encoding(encoder,root.right,sb);
      sb.deleteCharAt(sb.length()-1);
    }
  }
  public static void main(String[] args){
    String input = "aabbcd";
    //building freq map
    Map<Character,Integer> map = new HashMap<>();
    for(char ch : input.toCharArray()){
      map.put(ch,map.getOrDefault(ch,0)+1);
    }

    //build tree using heap
    PriorityQueue<Node> heap = new PriorityQueue<>((a,b)->{
      return Integer.compare(a.cost,b.cost);
    });
    for(char key : map.keySet()){
      heap.add(new Node(key,map.get(key)));
    }
    // making tree by removing and adding
    while(heap.size() > 1){
      Node first = heap.poll();
      Node second = heap.poll();
      Node newNode =new Node(null,first.cost+second.cost);
      newNode.left = first;
      newNode.right = second;
      heap.add(newNode);
    }
    Node root = heap.peek();

    //generating encoder
    Map<Character,String> encoder = new HashMap<>();
    Map<String,Character> decoder = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    encoding(encoder, root, sb);
    for(char key: encoder.keySet()){
      decoder.put(encoder.get(key),key);
    }
    StringBuilder codedMsg = new StringBuilder();
    for(char ch: input.toCharArray()){
      codedMsg.append(encoder.get(ch));
    }
    System.out.println(codedMsg);
    StringBuilder decodedMsg = new StringBuilder();
    StringBuilder temp = new StringBuilder();
    for(char ch : codedMsg.toString().toCharArray()){
      temp.append(ch);
      if(decoder.containsKey(temp.toString())){
        decodedMsg.append(decoder.get(temp.toString()));
	temp = new StringBuilder();
      }
    }
    System.out.println(decodedMsg);
  }
}
