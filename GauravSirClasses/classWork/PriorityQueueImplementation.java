import java.util.*;

class PriorityQueueImplementation {
  int []arr;
  int n;
  
  public PriorityQueueImplementation() {
    this.arr=new int[6];
    this.n=0;
  }
  
  private void resize(){
    if(n==arr.length-1){
      int [] newArray = new int [2*arr.length];
      for(int i = 1;i<=n;i++){
        newArray[i]=arr[i];
      }
      arr=newArray;
    }
    if(n==arr.length/4){
      int [] newArray = new int[arr.length/2];
      for(int i=1;i<=n;i++){
        newArray[i]=arr[i];
      }
      arr=newArray;
    }
  }

  public void add(int num) {
    resize();	  
    this.arr[++n]=num;
    swim(n);
  }
  
  private boolean less(int a, int b) {
    return a < b;
  }
  
  private void swap (int a,int b) {
    int temp=this.arr[a];
    this.arr[a]=this.arr[b];
    this.arr[b]=temp;
  } 

  private void swim(int idx) {
    while(idx > 1 && less(this.arr[idx/2],this.arr[idx])) {
      swap(idx/2,idx);
      idx=idx/2;
    }
  }

  public int poll() {
    int ele=this.arr[1];
    swap(n,1);
    n--;
    sink(1);
    resize();
    return ele;
  }

  private void sink(int idx){
    while(2*idx<=n){
      int left = 2*idx;
      int right = left+1;
      int swapIndex = left;
      if(right <= n && this.arr[right] > this.arr[left]){
        swapIndex=right;
      }

      if(!less(this.arr[idx],this.arr[swapIndex])){
        break;
      }
      swap(idx,swapIndex);
      idx=swapIndex;
    }
  }

  public boolean isEmpty() {
    return this.n==0;
  }
  public void printQueue(){
    for(int i=1;i<=n;i++){
      System.out.print(arr[i]+" ");
    }
  }
  
}
