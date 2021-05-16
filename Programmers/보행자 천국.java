class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
int answer = 0;
		int[][] DP = new int[m][n];
		
		DP[0][0] = 1;
		
		//  맨 윗줄 값 지정
		for(int i = 0; i < n; i++) {
			if(i == 0) continue;
			if(cityMap[0][i] == 1) break;
			
			DP[0][i] = 1;
		}
		
	    //  맨 왼쪽 줄 값 지정
		for(int i = 0; i < m; i++) {
			if(i == 0) continue;
			if(cityMap[i][0] == 1) break;
			
			DP[i][0] = 1;
		}
		if(m > 1 && n > 1) {
			for(int i = 1; i < m; i++) {
				for(int j = 1; j < n; j++) {
					if(cityMap[i][j] == 1) {
						DP[i][j] = 0; 
						continue;
					}
					//위쪽 검사
					if(cityMap[i - 1][j] == 0) DP[i][j] += DP[i - 1][j]  % 20170805;
					if(cityMap[i - 1][j] == 2)
						if(i - 1 > 0) {
							for(int k = i - 2; k >= 0; k--) {
								if(cityMap[k][j] != 2) {
									DP[i][j] += DP[k][j]  % 20170805;
									break;
								}
							}
						}
//						if(i - 1 > 0) DP[i][j] += DP[i - 2][j];
					
					//왼쪽 검사
					if(cityMap[i][j - 1] == 0) DP[i][j] += DP[i][j - 1]  % 20170805;
					if(cityMap[i][j - 1] == 2) 
						if(j - 1 > 0) {
							for(int k = j - 2; k >= 0; k--) {
								if(cityMap[i][k] != 2) {
									DP[i][j] += DP[i][k]  % 20170805;
									break;
								}
							}
						}
//						if(j - 1 > 0) DP[i][j] += DP[i][j - 2];
					
				}
			}			
		}
		answer = DP[m - 1][n - 1] % 20170805;
		return answer;
    }
}