import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	//전역 변수
	static long[] tree;
	static long[] arr;
	static long[] ans;
	static long Answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		arr = new long[N + 1];
		tree = new long[N * 4];
		ans = new long[K];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		init(1, 1, (N + 1) / 2 ,N );
		
		int a; int b; long c;
		int idx = 0;
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Long.parseLong(st.nextToken());
			
			if(a == 1) {
				update(1, 1, N, b, c - arr[b]);
				arr[b] = c;
			}
			else {
				Answer = 0;
				getSum(1, b, c, 1, ( N + 1 )/ 2, N);
				ans[idx++] = Answer;
			}
		}
		for(int i = 0; i < K; i++) {
			System.out.println(ans[i]);
		}
		
	}

	private static void update(int node, int start, int end, int idx, long diff) {
		if(idx < start || idx > end) return;
		tree[node] += diff;
		if(start == end) return;
		int mid = (start + end) / 2;
		
		update(node * 2, start, mid, idx, diff);
		update(node * 2 + 1, mid + 1, end, idx, diff);		
	}

	private static void getSum(int node, int left, long right, int start, int mid, int end) {
		if(left > end || right < start) return;
		if(left <= start && right >= end) { Answer += tree[node]; return; }
		if(start == end) return;
		mid = (start + end) / 2;
		getSum(node * 2, left, right, start, mid, mid);
		getSum(node * 2 + 1, left, right, mid + 1, mid, end);
	}

	private static long init(int node, int start, int mid, int end) {
		if(start == end) return tree[node] = arr[start];
		mid = (start + end) / 2;
		return tree[node] = init(node * 2, start, mid, mid) + init(node * 2 + 1, mid + 1, mid, end);
	}


}