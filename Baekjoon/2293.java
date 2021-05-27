import java.util.*;

import java.io.*;

public class Main {
	
	
	static int[] DP;
	static int[] coin;
	static long answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		int N; int K;
		
	   StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());                       
		
		coin = new int[N];
		answer = 0;
		for(int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		DP = new int[K + 1];
		DP[0] = 1;
		
		
		for(int i = 0; i < N; i++) {
			for(int j = coin[i]; j <= K; j++) {
				DP[j] = DP[j] + DP[j - coin[i]];
			}
		}
		
		System.out.println(DP[K]);
	}
}