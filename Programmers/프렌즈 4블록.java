import java.io.*;

import java.util.*;

public class Main {	
	static int[][] check;
	static int[] dx = {0 , 1 ,  0};
	static int[] dy = {1 , 0 , -1};
	static String board[];
	static char[][] chboard;
	static boolean flag;
	public static void main(String[] args) throws Exception {
		board = new String[]{
				"AABBEE",
				"AAAEEE",
				"VAAEEV",
				"AABBEE",
                "AACCEE",
                "VVCCEE"
		};
		
		System.out.println(solution(6,6,board));
	}

    public static int solution(int m, int n, String[] board) {
        int answer = 0; int cnt = 0;
        check = new int[m + 1][n + 1];
        chboard = new char[m][n];
        for(int i = 0; i < m; i++) {
        	chboard[i] = board[i].toCharArray();
        }
        while(true) {
        	 cnt = 0;
        	 for(int i = 0; i < m - 1; i++) {
             	for(int j = 0; j < n - 1; j++) {
             		if(chboard[i][j] == '0') continue;
             		if(chboard[i][j] != chboard[i][j + 1]) continue;
             		flag = true;
             		DFS(i,j, chboard[i][j] , chboard[i][j], 0);
             	}
             }
             
             for(int i = 0; i < m; i++) {
             	for(int j = 0; j < n; j++) {
             		if(check[i][j] != 0) {
             			cnt++;
             		}
             	}
             }
             if(cnt == 0) {
             	break;
             }
             else {
            	 answer += cnt;
            	 changeBoard(m, n);
             }
        }
        return answer;
    }

	private static void changeBoard(int m, int n) {
	   	 for(int i = 0; i < m ; i++) {
	      	for(int j = 0; j < n; j++) {
	      		if(check[i][j] != 0) {
	      			chboard[i][j] = '0';
	      			check[i][j] = 0;
	      		}
	      	}
	      }
		for(int j = 0; j < n; j++) {
			for(int i = m - 1; i > 0; i--) {
				if(chboard[i][j] == '0') {
					changeValue(i,j);
				}
			}
		}
		
	}

	private static void changeValue(int i, int j) {
		for(int k = i - 1; k >= 0; k--) {
			if(chboard[k][j] != '0') {
				chboard[i][j] = chboard[k][j];
				chboard[k][j] = '0';
				check[k][j] = 0;
				break;
			}
		}
	}

	private static void DFS(int x, int y, char start, char next, int cnt) {
		if(cnt == 3 || !flag) {
			if(flag) check[x][y] += 1;
			return;
		}

		int nx = x + dx[cnt]; int ny = y + dy[cnt];
		if(start != chboard[nx][ny]) {
			flag = false;
			return;
		}
		check[x][y] += 1;
		DFS(nx,ny,start, chboard[nx][ny], cnt + 1);
		if(flag == false) check[x][y] -= 1;
	}
	
	
}

