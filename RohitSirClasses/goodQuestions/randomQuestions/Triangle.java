import java.util.*;
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for(int k = nums.length -1; k>=2; k--){
            int i = 0;
	    int j = k-1;
	    while(i < j){
	        if((nums[i]+nums[j])>nums[k]){
		    result+=j-i;
		    j--;
		}else{
		    j++;
		}
	    }
        }
        return result;
    }
}


