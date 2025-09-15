import java.util.*;
class LeetCode_759{
  class Interval {
     public int start;
     public int end;

     public Interval() {}

     public Interval(int _start, int _end) {
         start = _start;
         end = _end;
     }
  }
  public List<Interval> employeeFreeTime(List<List<Interval>> schedule){
    List<Interval> merged = flatten(schedule);
    List<Interval> result = new ArrayList<>();
    for(int i=0;i<merged.size()-1;i++){
      int start = merged.get(i).end ;
      int end = merged.get(i+1).start;
      if(start < end){
        result.add(new Interval(start,end));
      }
    }
    return result;
  }
  public List<Interval> flatten(List<List<Interval>> schedule){
    List<Interval> list = new ArrayList<>();
    for(int i=0;i<schedule.size();i++){
      for(int j=0;j<schedule.get(i).size();j++){
        list.add(schedule.get(i).get(j));
      }
    }
    Collections.sort(list,(a,b)->{
      if(a.start != b.start){
        return a.start - b.start;
      }
      return a.end - b.end;
    });
    return merged(list);   
  }
  public List<Interval> merged(List<Interval> flattenedList){
    List<Interval> list = new ArrayList<>();
    int start = flattenedList.get(0).start;
    int end = flattenedList.get(0).end;
    for(int i=1;i<flattenedList.size();i++){
      if(flattenedList.get(i).start <= end){
        // overlap -> merge
	end = Math.max(end,flattenedList.get(i).end);
      }else{
        // no overlap found
	list.add(new Interval(start,end));
	start = flattenedList.get(i).start;
	end = flattenedList.get(i).end;
      }
    }
    list.add(new Interval(start,end));
    return list;
  }
}
