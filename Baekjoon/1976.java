import java.io.*; 
import java.util.*; 

public class Main {
	
	static int N;
	static int M;
	
	static int[][]        map;
	static boolean[][] visited;
	static int[]          parent;
	static int[]          rank;
	static int[]          cities;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
    	//System.setIn(new FileInputStream("C:\\workspace_algo\\Algorithm\\src\\input.txt"));
    	
    	// 입력
    	input();
    	
    	// 주요로직
    	solve();
    	
    }
	private static void solve() {
		for(int i = 1; i <= N; i++) {
    		for(int j = 1; j <= N; j++) {
    			if(map[i][j] == 1 && !visited[i][j]) {
    				visited[i][j] = true;
    				visited[j][i] = true;
    				
    				setUnion(i, j);
    			}
    		}
    	}
		
		int value = getFind(cities[0]);
		boolean flag = false;
		for(int i = 1; i < M; i++)
		{
			if(getFind(cities[i]) != value) {
				flag = true;
				break;
			}
		}
		if(!flag) System.out.println("YES");
		else      System.out.println("NO");
	}
	
	private static void setUnion(int x, int y) {
		int px = getFind(x);
		int py = getFind(y);
		
		if(px != py) {
			if(rank[px] <= rank[py]) 	{
				parent[x] = py;
				rank[py] += rank[px];
				rank[px] = 0;
			}
			else {
				parent[y] = px;
				rank[px] += rank[py];
				rank[py] = 0;
			}
		}
		
	}
	private static int getFind(int x) {
		if(x == parent[x]) return x;
		int y = getFind(parent[x]);  
		parent[x] = y;
		return y;
	}
	private static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
    	M = Integer.parseInt(br.readLine());
    	
		map    = new int[N + 1][N + 1];
    	visited = new boolean[N + 1][N + 1];
    	parent = new int[N + 1];
    	rank    = new int[N + 1];
		for(int i = 1; i <= N; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		for(int j = 1; j <= N; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		cities = new int[M];
		for(int i = 0; i < M; i++) {
			cities[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 1; i <= N; i++) {
    		parent[i] = i;
    		rank[i] = 1;
    	}
	}
}
