/*
 * TTILE : Á¤¼ö »ï°¢Çü
 * GRADE : Silver 1
 * DATE  : 2021-04-05 
 */

import java.util.*;
import java.io.*;

public class Main{
	static int[][] triangle; 
	static int[][] DP;
	static int     answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		triangle = new int[n][n];
		for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j <= i; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		DP = new int[n][n];
		DP[0][0] = triangle[0][0];
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j <= i; j++) {
				if(j != 0) 
					if(DP[i][j] < DP[i - 1][j - 1] + triangle[i][j]) DP[i][j] = DP[i - 1][j - 1] + triangle[i][j];
				if(j != i)
					if(DP[i][j] < DP[i - 1][j] + triangle[i][j])     DP[i][j] = DP[i - 1][j] + triangle[i][j];
			}
		}
		 
		answer = 0;
		for(int j = 0; j < n; j++) {
			if(answer < DP[n - 1][j]) answer  = DP[n - 1][j];
		}
		
		System.out.println(answer);
		
	}

}