/*
 * TTILE : Á¤¼ö »ï°¢Çü
 * GRADE : Silver 1
 * DATE  : 2021-04-05 
 */

import java.util.*;
import java.io.*;

public class Main{
	static int     N;
	static int     M;
	static int[]   arr;
	static long[] DP;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());	
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		DP = new long[N + 1];
		DP[0] = 0;
		DP[1] = arr[1];
		for(int i = 2; i <= N; i++) {
			 DP[i] += DP[i - 1] + arr[i];
		}
		int i = 0; int j = 0;
		for(int index = 0; index < M; index++) {
			st = new StringTokenizer(br.readLine());
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			if(i == j) System.out.println(arr[i]);
			else       System.out.println(DP[j] - DP[i - 1]);
		}
		
	}

}