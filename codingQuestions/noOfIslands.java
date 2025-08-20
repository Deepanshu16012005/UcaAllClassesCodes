import java.util.*;
class noOfIslands {
    public int numIslands(char[][] grid) {
	int count=0;    
        boolean [][]visited= new boolean[grid.length][];
	for(int i=0;i<grid.length;i++){
	  for(int j=0;j<grid[i].length;j++){
	    if(grid[i][j]=='1' && !visited[i][j]){
	      dfs(i,j,visited,grid);
	      count++;
	    }
	  }
	}
	return count;
    }
    public void dfs(int i , int j,boolean[][]visited,char[][]grid){
      if( i<0 || j<0 || i>=grid.length || j>=grid[i].length || visited[i][j] || grid[i][j]=='0'){
        return;
      }
      visited[i][j]=true;
      dfs(i+1,j,visited,grid);
      dfs(i-1,j,visited,grid);
      dfs(i,j+1,visited,grid);
      dfs(i,j-1,visited,grid);
    }
}
