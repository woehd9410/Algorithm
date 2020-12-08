import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int Answer;
	static char[][] board;
	static int[][] visit;
	static int[] dx = { -1, 0, 1, 0};
	static int[] dy = { 0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		visit = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			board[i] = str.toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visit[i][j] = 987654321;
			}
		}
		
		BFS();
		
	}

	private static void BFS() {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(0,0));
		visit[0][0] = 1;
		boolean flag = false;
		int cnt = 0; Answer = 0;
		while(!q.isEmpty() && !flag) {
			cnt++;
			if(cnt > N * M * 4) {
				Answer = -1;
				break;
			}
			int size = q.size();
			for(int i = 0; i < size; i++) {
				if(flag) break;
				int x = q.peek().x; int y = q.peek().y;
				q.poll();
				char now = board[x][y];
				
				for(int k = 0; k < 4; k++) {
					// 위, 아래, 좌, 우 네번 돌려야함...
					int nx = x + dx[k] * (now - '0');
					int ny = y + dy[k] * (now - '0');
					
					
					if((nx < 0 || ny < 0 || nx >= N || ny >= M) || board[nx][ny] == 'H') {
						if(Answer < cnt) Answer = cnt;
						continue;
					}
					
					if(visit[nx][ny] == 987654321 || visit[nx][ny] < cnt) {
						visit[nx][ny] = cnt;
						q.add(new Pos(nx,ny));
					}
				}
			}
		}
		System.out.println(Answer);
	}
	
	private static class Pos{
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
