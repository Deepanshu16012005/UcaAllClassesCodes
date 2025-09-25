public class SearchMatrix {
    /**
     * Search a 2D matrix.
     * 
     * You are given an m x n integer matrix matrix with the following two properties:
     * 1. Each row is sorted in non-decreasing order.
     * 2. Each column is sorted in non-decreasing order.
     * 
     * Given an integer target, return true if target is in matrix or false otherwise.
     * 
     * Constraints:
     * 1. m == matrix.length
     * 2. n == matrix[i].length
     * 3. 1 <= m, n <= 300
     * 4. -10^9 <= matrix[i][j] <= 10^9
     * 
     * @param matrix - A 2D list of integers representing the matrix.
     * @param target - The integer to search for in the matrix.
     * @returns boolean - True if target is found in the matrix, otherwise false.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
       // return helper(matrix,target,0);
       return helper2(matrix,0,matrix[0].length-1,target);
    }
    public boolean helper2(int[][] matrix ,int row, int col, int target){
	if(row >= matrix.length || col < 0)return false;
        int value = matrix[row][col];
	
	if(value == target)return true;
	//elimate entire coloumn
	if(value > target){
	    return helper2(matrix,row,col-1,target);
	}
	// elimate entire row
	else{
	    return helper2(matrix,row+1,col,target);
	}
    }
    public boolean helper(int[][] matrix , int target, int row){
	if(matrix.length <= row){
	    return false;
	}
        if(binarySearch(matrix,target,row,0,matrix[row].length-1)){
	    return true;
	}
	return helper(matrix , target , row+1);
    }
    public boolean binarySearch(int [][]matrix , int target, int row , int start , int end ){
	if(start > end){
	    return false;
	}
        int mid = (start+end)/2;

	if(matrix[row][mid] == target) return true;
        
	if(matrix[row][mid] < target){
	   return binarySearch(matrix,target,row,mid+1,end);
	}
	else{
	    return binarySearch(matrix,target,row,start,mid-1);
	}	
    }

    /**
     * Main method for testing the SearchMatrix class.
     */
    public static void main(String[] args) {
        SearchMatrix sm = new SearchMatrix();
        
        int[][] matrix1 = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        assert sm.searchMatrix(matrix1, 5) == true : "Test case 1 failed";
        assert sm.searchMatrix(matrix1, 20) == false : "Test case 2 failed";

        int[][] matrix2 = {
            {-1, 3}
        };
        assert sm.searchMatrix(matrix2, 3) == true : "Test case 3 failed";
        assert sm.searchMatrix(matrix2, -1) == true : "Test case 4 failed";
        assert sm.searchMatrix(matrix2, 0) == false : "Test case 5 failed";
	System.out.println("All Testcases Passed");
    }
}
