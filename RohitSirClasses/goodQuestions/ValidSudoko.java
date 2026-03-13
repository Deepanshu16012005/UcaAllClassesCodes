class ValidSudoko{
  public static void main(String[] args){
    char[][] board={
      {'5','3','.','.','7','.','.','.','.'},
      {'6','.','.','1','9','5','.','.','.'},
      {'.','9','8','.','.','.','.','6','.'},
      {'8','.','.','.','6','.','.','.','3'},
      {'4','.','.','8','.','3','.','.','1'},
      {'7','.','.','.','2','.','.','.','6'},
      {'.','6','.','.','.','.','2','8','.'},
      {'.','.','.','4','1','9','.','.','5'},
      {'.','.','.','.','8','.','.','7','9'},
    };
    solve(board);
  }
  public static void solve(char[][] board){
    for(int i=0;i<9;i++){
      for(int j=0;j<9;j++){
        if(board[i][j] == '.'){
	  for(char k = '1'; k <= '9';k++){
	    if(isValid(board,i,j,k)){
	      board[i][j]=k;

	    }
	  }
	}
      }
    }
    for(int i=0;i<9;i++){
      for(int j=0;j<9;j++){
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }
  public static boolean isValid(char [][] board , int row , int col , char c){
    return RowColCheck(board,row,col,c) && gridCheck(board,row,col,c);
  }
  public static boolean RowColCheck(char[][] board, int row, int col ,char c){
    for(int i = 0;i < 9;i++){
      if(board[row][i] == c){
        return false;
      }
      if(board[i][col] == c){
        return false;
      }
    }
    return true;
  }
  public static boolean gridCheck(char[][] board ,int row , int col , char c){
    int startRow = (row/3) * 3;
    int startCol = (col/3) * 3;
    for(int i=startRow;i<startRow+3;i++){
      for(int j=startCol;j<startCol+3;j++){
        if(board[i][j]==c){
          return false;
	}
      }
    }
    return true;
  }
}
