import java.util.*;
class KthLargestElement{
    public static int findKthLargest(int[] nums,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(
	    (a,b)->{
	        return Integer.compare(b,a);
	    }
	);
	for(int num : nums){
	    pq.add(num);
	} 
        for(int i=1;i<k;i++){
	    pq.poll();
	}
	return pq.poll();
    }
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
	System.out.print("enter size of array: ");
	int n= sc.nextInt();
	int []arr= new int [n];
	for(int i=0;i<n;i++){
	    arr[i]=sc.nextInt();
	}
	System.out.print("enter the value of k: ");
	int k=sc.nextInt();
	System.out.println(findKthLargest(arr,k));
    }
}
