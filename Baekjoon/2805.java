import java.util.*;
import java.io.*;

public class Main {
	static int   N; // 1 <= N <= 1,000,000
	static int   M; // 1 <= M <= 2,000,000,000
	static int   Min;
	static int   Max;
	static long   answer;
	static boolean flag;
	static int[] trees;
	public static void main(String[] args) throws IOException {
		
		Input(); // 값 입력받는 함수
		answer = -1;
		binarySearch(0, Max);
		if(answer == 987654321) answer = 0;
		
		System.out.println(answer);
		
	}
	private static void binarySearch(int left, int right) {
		if(left > right) return;
		int mid    = ( left + right ) / 2;
		long result = getTree(mid); 
		if(result < M) {
			binarySearch(left , mid - 1);
		}
		else if(result == M) {
			answer = mid;
		}
		else if(result > M) {
			if(answer < mid) answer = mid;
			binarySearch(mid + 1 , right);
		}
	}
	private static long getTree(int mid) {
		long sum = 0;
		for(int tree : trees) {
			if(tree > mid) sum += tree - mid;
		}
		return sum;
	}
	private static void Input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Max = 0; Min = 987654321;
		trees = new int[N];
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			trees[i] = num;
			if(num > Max) Max = num;
			if(num < Min) Min = num;
		}
		
	}

	
}
