import java.util.*;
class LeetCode_253{
  public static int conferenceRooms(int [][]intervals){
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    if(intervals.length == 0){
      return 0;
    }
    if(intervals.length == 1){
      return 1;
    }

    Arrays.sort(intervals, (a, b)->{
      if(a[0] != b[0]){
        return a[0] - b[0];
      }
      return a[1] - b[1];
    });
    int result = 1; 
    int last = intervals[0][1];
    pq.add(last);
    // we can also track max no of rooms by changing values acc to max room in use at a current time   result = Math.max(result,pq.size());
    for(int i = 1; i<intervals.length; i++){
      if(intervals[i][0] < pq.peek()){
        result++;
	pq.add(intervals[i][1]);
      }else{
        pq.poll();
        pq.add(intervals[i][1]);	
      }
    }
    return result;
  }
}

