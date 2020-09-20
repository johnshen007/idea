public class SolutionNumOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int resMax = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    resMax = Math.max(resMax, dfs(i, j, grid));
                }
            }
        }
        return resMax;
    }

    private int dfs(int i, int j, int[][] grid){
        if(i<0 || j<0 || i>=grid.length || j >= grid[i].length || grid[i][j]==0){
            return 0;
        }
        grid[i][j] = 0;
        int num =1;
        num += dfs(i-1, j, grid);
        num += dfs(i+1, j, grid);
        num += dfs(i, j-1, grid);
        num += dfs(i, j+1, grid);
        return num;
    }

}

