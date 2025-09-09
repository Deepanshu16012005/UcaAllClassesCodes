import java.util.*;
class characterReplacement {
  public int characterReplacement (String s , int k){
    int start = 0;
    int end = 0;
    int result = 0;
    int maxFreq = 0;
    int [] freqArr = new int [26];
    while( end < s.length() ){

      freqArr[s.charAt(end) - 'A']++;
      maxFreq = Math.max(maxFreq , freqArr[s.charAt(end) - 'A']);

      while((end - start + 1) - maxFreq > k){
        freqArr[s.charAt(start) - 'A']--;
	start++;
      }

      result = Math.max(result,end - start + 1);
      end++;   
    }
    return result;
  }
}
