import java.util.*;
import java.io.*;

// 두 배열의 합
public class Main{
	
	public static int T;
	public static int N;
	public static int M;
	public static int cnt;
	public static long ans;
	public static boolean flag;
	
	public static int[] arrA;
	public static int[] arrB;
	public static ArrayList<Long> sumA;
	public static ArrayList<Long> sumB;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 함수
		Input();
		
		// A배열 B배열 부분합 구하기
		getSum(arrA, 1);
		getSum(arrB, 2);
		ans = 0;
		getCnt();
		System.out.println(ans);
	}

	private static void getCnt() {
		Collections.sort(sumA);
		Collections.sort(sumB);
		
		for(long sum : sumA) {
			long target = T - sum;
			long lb = lowerBound(target);
			long ub = upperBound(target);
			ans += ub - lb;
		}
	}
	
	private static int lowerBound(long target) {
		int start = 0;
		int end   = sumB.size();
		int mid;
		while(start < end) {
			mid   = (start + end) / 2;
			if(sumB.get(mid) < target) {
				start = mid + 1;
			}
			else{
				end = mid;
			}
		}
		return end;
	}
	
	private static int upperBound(long target) {
		int start = 0;
		int end   = sumB.size();
		int mid;
		while(start < end) {
			mid = (start + end) / 2;
			if(sumB.get(mid) <= target) {
				start = mid + 1;
			}
			else{
				end = mid;
			}
		}
		return end;
	}


	private static void getSum(int[] arr, int arrNum) {
		long sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum = 0;
			sum += arr[i];
			if(arrNum == 1) sumA.add(sum);
			else            sumB.add(sum);
			for(int j = i + 1; j < arr.length; j++) {
				sum += arr[j];
				if(arrNum == 1) sumA.add(sum);
				else            sumB.add(sum);
			}
		}
	}

	private static void Input() throws NumberFormatException, IOException {
        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
	
		N = Integer.parseInt(br.readLine());
		arrA = new int[N];
		sumA = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		arrB = new int[M];
		sumB = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}
	}
}