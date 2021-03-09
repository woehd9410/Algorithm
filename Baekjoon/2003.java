import java.util.*;
import java.io.*;

public class Main {
	static int   N;
	static int   M;
	static int[] arr;
	static int   ans;
	
	public static void main(String[] args) throws IOException {
		input();
		solve();
	}
	private static void solve() {
		int left = 0; int right = 0;
		int sum  = arr[0];
		
		while(left <= right && right < N) {
			if (sum < M) 
			{
				sum += arr[++right];
			}
			else if (sum == M) 
			{
				sum += arr[++right];
				ans++;
			}
			else if (sum > M) 
			{
				sum -= arr[left++];
				if (left > right && left < N) {
					right = left;
					sum = arr[left];
				}
			}
		}
		System.out.println(ans);
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
	
}
