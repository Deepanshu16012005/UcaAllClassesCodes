import java.util.*;
class RockabyeTobby{
  public static class Pair{
    String name;
    int interval;
    int orignalTime;
    int index;
    Pair(String name , int interval,int orignalTime,int index){
      this.name=name;
      this.interval=interval;
      this.orignalTime=orignalTime;
      this.index=index;
    } 
  }
  public static void printOrder(Pair[] arr,int limit){
    PriorityQueue<Pair>pq = new PriorityQueue<>((a,b)->{
      if(a.interval!=b.interval){
        return Integer.compare(a.interval,b.interval);
      }else{
        return Integer.compare(a.index,b.index);
      }  
    });
    for(Pair p:arr){
      pq.add(p);
    }
    while(!pq.isEmpty()&&limit>0){
      Pair taked = pq.poll();
      System.out.println(taked.interval+" "+taked.name);
      pq.add(new Pair(taked.name,taked.interval+taked.orignalTime,taked.orignalTime,taked.index));      
      limit--;
    }  
  }
  public static void main(String[] args){
    Scanner sc = new Scanner (System.in);	  
    int T = sc.nextInt();
    while(T>0){	    
      int noOfMedicine=sc.nextInt();
      int medicineToTake=sc.nextInt();
      sc.nextLine();
      Pair []arr= new Pair[noOfMedicine];
      for(int i=0;i<noOfMedicine;i++){
	String line = sc.nextLine();
	String[] fullMsg = line.split(" ");
	String name = fullMsg[0];
        int time = Integer.parseInt(fullMsg[1]);	
        arr[i]=new Pair(name,time,time,i);
      }
      printOrder(arr,medicineToTake);
      T--;	    
    }
    System.out.println("Success");
  }
}
