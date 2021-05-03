/*
 * TITLE            : [1149 | RGB 거리] 
 * DATE             : 2021-05-03
 */

import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	
	static int[] R;
	static int[] G;
	static int[] B;
	
	static int[][] DP;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = stoi(br.readLine());
		
		R = new int[1000 + 1];
		G = new int[1000 + 1];
		B = new int[1000 + 1];
		
		DP = new int[1000 + 1][3];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			R[i] = stoi(st.nextToken());
			G[i] = stoi(st.nextToken());
			B[i] = stoi(st.nextToken());
		}
		
		DP[0][0] = R[0]; DP[0][1] = G[0]; DP[0][2] = B[0];
		for(int i = 1; i < N; i++) {
			DP[i][0] += R[i] + (DP[i - 1][1] < DP[i - 1][2] ? DP[i - 1][1] : DP[i - 1][2]);
			DP[i][1] += G[i] + (DP[i - 1][0] < DP[i - 1][2] ? DP[i - 1][0] : DP[i - 1][2]);
			DP[i][2] += B[i] + (DP[i - 1][0] < DP[i - 1][1] ? DP[i - 1][0] : DP[i - 1][1]);
			
		}
		
		int min = 987654321;
		for(int i = 0; i < 3; i++) {
			if(DP[N - 1][i] < min) min = DP[N - 1][i];
		}
		System.out.println(min);
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
}