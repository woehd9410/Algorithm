import java.io.*;
class Solution {
static int board[][];
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
	   int[][] key = {
			   {0, 0, 0}, {1, 0, 0}, {0, 1, 1}
	   };
	   int[][] lock = {
			   {1, 1, 1}, {1, 1, 0}, {1, 0, 1}
	   };
	   
	   System.out.println(solution(key, lock));
	}
	public static boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        M = key.length; 
        N = lock.length;
        
        board = new int[N + 2 * M - 1][N + 2 * M - 1];
        setLock(lock);
        
        for(int i = 1; i <= N + M - 1; i++) {
        	for(int j = 1; j <= N + M -1; j++) {
        		for(int k = 0; k < 4; k++) {
        			turnMap(key);
        			if(check(i, j, board, key)) {
        				answer = true;
        				return answer;
        			}
        		}
        		if(answer) return answer;
        	}
        	if(answer) return answer;
        }
        
        return answer;
    }
	private static boolean check(int x, int y, int[][] board, int[][] key) {
		boolean flag = true;
		for(int i = x; i < x + M; i++) {
			for(int j = y; j < y + M; j++) {
				board[i][j] += key[i - x][j - y];
			}
		}
		
		for(int i = M; i < M + N; i++) {
			for(int j = M; j < M + N; j++) {
				if(board[i][j] != 1) flag = false;
			}
		}
		
		for(int i = x; i < x + M; i++) {
			for(int j = y; j < y + M; j++) {
				board[i][j] -= key[i - x][j - y];
			}
		}
		
		return flag;
	}
	private static void setLock(int[][] lock) {
		for(int i = M; i < M + N; i++) {
			for(int j = M; j < M + N; j++) {
				board[i][j] = lock[i - M][j - M];
			}
		}
	}
	private static void turnMap(int[][] key) {
		int[][] temp = new int[key.length][key.length];
		
		for(int i = 0; i < key.length; i++) {
			for(int j = 0; j < key.length; j++) {
				temp[j][key.length - 1 - i] = key[i][j]; 
			}
		}
		for(int i = 0; i < key.length; i++) {
			for(int j = 0; j < key.length; j++) {
				key[i][j] = temp[i][j];
			}
		}
	}
}