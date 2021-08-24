import java.util.*;
class Solution {
    static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static class Node {
        int x;
        int y;
        
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Node> q = new LinkedList<Node>();
    static boolean[][] visited;
    static int area = 0;
    
    public int[] solution(int m, int n, int[][] picture) {
         int numberOfArea = 0;
	        int maxSizeOfOneArea = 0;

	        int[] answer = new int[2];
	        visited = new boolean[m][n];
	        int size = 0;
	        for(int i = 0; i < m; i++) {
	        	for(int j = 0; j < n; j++) {
	        		 if(picture[i][j] > 0 && visited[i][j] != true){
	        			 numberOfArea++;
	                     area = 1;
	                     bfs(picture, i, j, m, n);
	                     if(maxSizeOfOneArea < area)
	                         maxSizeOfOneArea = area;
	                 }
	        	}
	        }
	        answer[0] = numberOfArea;
	        answer[1] = maxSizeOfOneArea;
	        return answer;
    }
	 static void bfs(int[][] picture, int x, int y, int m, int n){
	        q.add(new Node(x, y));
	        visited[x][y] = true;
	        
	        while(!q.isEmpty()){
	            Node now = q.poll();
	            
	            for(int i = 0; i < 4; i++){
	                int nx = now.x + dx[i];
	                int ny = now.y + dy[i];
	                
	                if(0 <= nx && nx < m && 0 <= ny && ny < n){
	                    if(picture[nx][ny] == picture[x][y] && visited[nx][ny] != true){
	                    	visited[nx][ny] = true;
	                        q.add(new Node(nx, ny));
	                        area++; 
	                    }
	                }
	            }
	        }
	    }
}