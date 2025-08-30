import java.util.*;
class LeetCode_340 {
    public int atMostK(String s , int k) {
        int start = 0;
        int end = 0;
        int length = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        while(end < s.length()){
            map.put(s.charAt(end) , map.getOrDefault(s.charAt(end),0)+1);
            while(map.size()>k){
                map.put(s.charAt(start),map.get(s.charAt(start))-1);
                if(map.get(s.charAt(start))==0){
                    map.remove(s.charAt(start));
                }
                start++;
            }
            length = Math.max(length , end - start + 1);
            
            end++;
        }
        return length;
    }
}
