/*
 * TITLE  : 합이 0인 네 정수
 * NUMBER : 7453
 * GRADE  : GOLD 2
 * DATE   : 2021-03-22
 * */
import java.util.*;
import java.io.*;

public class Main{
	static int N;
	
	static int[] A, B, C, D;
	static int[] sumAB, sumCD;
	static long answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		Input();
	}

	private static void Input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		A = new int[N];
		B = new int[N];
		C = new int[N];
		D = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		int sum1 = 0; int idx1 = 0;
		int sum2 = 0; int idx2 = 0;
		
		sumAB = new int[N * N];
		sumCD = new int[N * N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sum1 = A[i] + B[j];
				sum2 = C[i] + D[j];
				sumAB[idx1++] = sum1;
				sumCD[idx2++] = sum2;
			}
		}
		Arrays.sort(sumAB);
		Arrays.sort(sumCD);
		for(int i = 0; i < sumAB.length; i++) {
			int target = sumAB[i] * -1;
			answer += upper_bound(0, sumCD.length, target) - lower_bound(0, sumCD.length, target);
		}
		System.out.println(answer);
		
	}

	private static long lower_bound(int left, int right, int target) {
		int mid = 0;
		while(left < right) {
			mid = (left + right) / 2;
			if(sumCD[mid] >= target) {
				right = mid;
			}
			else if(sumCD[mid] < target) {
				left = mid + 1;
			}
		}
		return right;
	}

	private static long upper_bound(int left, int right, int target) {
		int mid = 0;
		while(left < right) {
			mid = (left + right) / 2;
			if(sumCD[mid] > target) {
				right = mid;
			}
			else if(sumCD[mid] <= target) {
				left = mid + 1;
			}
		}
		return right;
	}


}