/**
 * Finds the maximum XOR of any two elements in the array.
 * Key constraints: The array contains at least two non-negative integers.
 * @param nums an array of non-negative integers
 * @return the maximum XOR value of any two distinct elements in the array
 */
public class MaximumXorInArray {

    public static int findMaximumXor(int[] nums) {
        // method stub
	// brute 
	// optimize by tries (not studied yet)
	int xor = 0;
	int n = nums.length;
	for(int i = 0; i < n; i++){
	  for(int j = i + 1; j < n; j++){
	    xor = Math.max(xor, nums[i] ^ nums[j]);
	  } 
	}
        return xor;
    }

    public static void main(String[] args) {
        System.out.println("Expected 126: " + findMaximumXor(new int[]{26, 100, 25, 13, 4, 14}));
        System.out.println("Expected 7: " + findMaximumXor(new int[]{1, 2, 3, 4, 5, 6, 7}));
        System.out.println("Expected 0: " + findMaximumXor(new int[]{0, 0, 0}));
    }
}



