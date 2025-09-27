public class NQueens {
    /**
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
     * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
     * 
     * Example: 
     * Input n = 4
     * Output: 2
     * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
     *
     * [
     *   [".Q..",  // Solution 1
     *    "...Q",
     *    "Q...",
     *    "..Q."],
     *   ["..Q.",  // Solution 2
     *    "Q...",
     *    "...Q",
     *    ".Q.."
     *    ]
     * ]
     * 
     * Constraints:
     * 1. 1 <= n <= 9
     * 2. You may assume that n is a positive integer.
     *
     * @param n - The size of the chessboard and the number of queens to place.
     * @returns int - The number of distinct solutions to the n-queens puzzle.
     **/
    public int totalNQueens(int n) {
      boolean[][] mat = new boolean[n][n];
      int []ans = new int[1];
      helper(mat,0,0,ans);
      return ans[0];
    }
    public void helper(boolean[][] mat,int row, int count,int [] ans){
      if(count == mat.length){
        ans[0]++;
	return;
      }
      for(int i = 0; i < mat[0].length; i++){
        if(isSafe(mat, row, i)){
	  mat[row][i] = true;
          helper(mat,row+1,count+1,ans);
	  mat[row][i] = false; 
	}
      }
    }
    public boolean isSafe(boolean[][] mat , int row ,int col){
      for(int i = 0; i < mat.length; i++){
        if(mat[i][col]){
	  return false;
	}
      }
      
      for(int i = row , j = col ; i >=0 && j < mat.length; i--, j++){
        if(mat[i][j]){
	  return false;
	}
      }

      for(int i = row , j = col ; i >=0 && j >=0; i--, j--){
        if(mat[i][j]){
          return false;
        }
      }
      return true;
    }

    /**
     * Main method for testing the NQueens class.
     */
    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        assert nQueens.totalNQueens(4) == 2 : "Test case 1 failed";
        assert nQueens.totalNQueens(1) == 1 : "Test case 2 failed";
        assert nQueens.totalNQueens(5) == 10 : "Test case 3 failed";
    }
}
