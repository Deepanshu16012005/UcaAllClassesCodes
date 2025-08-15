import java.util.*;
class MinMaxStack{
  
  class Node{
    int value;
    int minValue;
    int maxValue;
    Node next;
    Node(int value){
      this.value=value;
    }
  }
  private Node head;
  MinMaxStack(){
    head=null;
  }
  public boolean isEmpty(){
    return head==null;
  }
  public void push(int val){
    Node node = new Node(val);
    if(head==null){
      node.minValue=val;
      node.maxValue=val;
      head=node;
      return;
    }
    node.next=head;
    node.minValue=Math.min(val,head.minValue);
    node.maxValue=Math.max(val,head.maxValue);
    head=node;
  }
  public int pop(){
    int val=head.value;
    head=head.next;
    return val;
  }
  public int getMin(){
    return head.minValue;
  }
  public int getMax(){
    return head.maxValue;
  }
}
