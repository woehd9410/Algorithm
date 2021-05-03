/*
 * TITLE            : [10868 | 최솟값] 
 * DATE             : 2021-05-03
 */

import java.util.*;
import java.io.*;

public class Main {
	
	static final int INF = 1000000007;
	static int N;
	static int M;
	
	static long arr[];
	static long seg[];
	
	public static void main(String[] args) throws IOException {
		input();
		
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		arr = new long[N + 1];
		for(int i = 1; i <= N; i++) {
			arr[i] = stol(br.readLine());
		}
		
		seg = new long[N * 4 + 1];
		init(1, N, 1);
		int a ,b = 0;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = stoi(st.nextToken());
			b = stoi(st.nextToken());
			
			sb.append(getMinNum(1, N, a, b, 1) + "\n");
		}
		System.out.println(sb);
	}

	private static long stol(String readLine) {
		return Long.parseLong(readLine);
	}

	private static long getMinNum(int start, int end, int left, int right, int index) {
		if(right < start || left > end) return INF;
		
		if( start >= left && right >= end) return seg[index];
		
		int mid = (start + end) / 2;
		long leftMin = getMinNum(start, mid, left, right, index << 1);
		long rightMin = getMinNum(mid + 1, end, left, right, (index << 1) + 1);
		
		if(leftMin == INF) return rightMin;
		else if(rightMin == INF) return leftMin;
		else return leftMin < rightMin ? leftMin : rightMin;		
	}

	private static long init(int start, int end, int index) {
		if(start == end) return seg[index] = arr[start];
		
		int mid = (start + end) / 2;
		return seg[index] = Math.min(init(start, mid, index * 2), init(mid + 1, end, index * 2 + 1));
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
}