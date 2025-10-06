/**
 * Calculates the total Hamming distance between all pairs of integers in the array.
 * The Hamming distance between two integers is the number of bit positions in which they differ.
 * @param nums an array of non-negative integers
 * @return the total Hamming distance between all unique pairs in the array
 */
public class TotalHammingDistance {

   // public static int totalHammingDistance(int[] nums) {
        // method stu
	// brute force 
//	int ans = 0;
//	for(int i = 0; i < nums.length; i++){
//	  for(int j = i + 1; j < nums.length; j++){
//	    ans += hammingDistance(nums[i],nums[j]);
//	  } 
//	}
//        return ans;
//    }
    public static int totalHammingDistance(int[] nums){
      // 32 bit integers
      // count no of ones and zeros from right one by one total contribtion will be ones * zeros from that bit
      int total = 0; 
      for(int i = 0; i < 32; i++){
        int ones = 0;
	int zeroes = 0;
	for(int num : nums){
	  if(((num >> i) & 1) == 1){
	    ones++;
	  }
	}
	zeroes = nums.length - ones;
	total += ones * zeroes;
      }
      return total;
    }
    private static int hammingDistance(int a, int b){
      int xor = a ^ b;
      int ans = 0;
      while(xor > 0){
	if((xor & 1) == 1)
	  ans++;
        xor =  xor >> 1;
      }
      return ans;
    }
    public static void main(String[] args) {
        System.out.println("Expected 8: " + totalHammingDistance(new int[]{4, 14, 4, 14}));
        System.out.println("Expected 4: " + totalHammingDistance(new int[]{1, 2, 3}));
        System.out.println("Expected 0: " + totalHammingDistance(new int[]{0, 0, 0}));
    }
}



