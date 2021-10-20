import java.util.*;
class Solution {
	
	static class Node implements Comparable<Node>{
		int house;
		int dist;
		int prev;
		
		Node(int house, int dist, int prev){
			this.house = house;
			this.dist = dist;
			this.prev = prev;
		}

		@Override
		public int compareTo(Node arg0) {
			if(this.dist < arg0.dist) return 1;
			else return -1;
		}
		
		
	}
	
	static int[][] dist;
	static ArrayList<Node>[] list;
	static final int INF = 987654321;
	
	public static void main(String[] args) {
		int[][] fares = {{2, 6, 6}, 
						 {6, 3, 7}, 
						 {4, 6, 7}, 
						 {6, 5, 11}, 
						 {2, 5, 12},
						 {5, 3, 20}, 
						 {2, 4, 8}, 
						 {4, 3, 9}};
		System.out.println((solution(6,4,5,6,fares)));
	}
	
    @SuppressWarnings("unchecked")
	public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 987654321;
        dist = new int[3][n + 1];
        list = new ArrayList[n + 1];
        
        for(int i = 1; i <= n; i++) {
        	list[i] = new ArrayList<>();
        }
        for(int i = 0; i < fares.length; i++) {
        	int x = fares[i][0];
        	int y = fares[i][1];
        	int d = fares[i][2];
        	
        	list[x].add(new Node(y,d,0));
        	list[y].add(new Node(x,d,0));
        }
        dijkstra(0,s,n);
        dist[1][s] = dist[0][a];
        dist[2][s] = dist[0][b];
        dijkstra(1,a,n);
        dist[2][a] = dist[1][b];
        dijkstra(2,b,n);
        if(dist[0][a] != INF && dist[0][b] != INF) {
        	answer = Math.min(answer, dist[0][a] + dist[0][b]);
        }
        if(dist[0][a] != INF && dist[1][b] != INF) {
        	answer = Math.min(answer, dist[0][a] + dist[0][b]);
        }
        if(dist[2][a] != INF && dist[0][b] != INF) {
        	answer = Math.min(answer, dist[0][a] + dist[0][b]);
        }
        for(int i = 1; i <= n; i++) {
        	if(dist[0][i] == INF || dist[1][i] == INF || dist[2][i] == INF) continue;
        	answer = Math.min(answer, dist[0][i] + dist[1][i] + dist[2][i]);
        }
        return answer;
    }

	private static void dijkstra(int idx, int start, int n) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i = 1; i <= n; i++) {
			dist[idx][i] = INF;
		}
		pq.add(new Node(start,0,0));
		dist[idx][start] = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			for(int i = 0; i < list[now.house].size(); i++) {
				Node next = list[now.house].get(i);
				if(now.prev == next.house) continue;
				if(dist[idx][next.house] < dist[idx][now.house] + next.dist) continue;
				dist[idx][next.house] = dist[idx][now.house] + next.dist;  
				pq.add(new Node(next.house, dist[idx][next.house], now.house));
			}
		}
	}
}