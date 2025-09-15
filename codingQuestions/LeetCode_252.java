import java.util.*;
class LeetCode_252{
    public static boolean validMeetingRooms(int [][]intervals){
    if(intervals.length == 0 || intervals.length == 1){
      return true;
    }
    Arrays.sort(intervals, (a, b)->{
      if(a[0] != b[0]){
        return a[0] - b[0];
      }
      return a[1] - b[1];
    });

    int last = intervals[0][1];
    for(int i = 1; i<intervals.length; i++){
      if(intervals[i][0] < last){
        return false;
      }
      last = intervals[i][1];
    }
    return true;
  }
}
