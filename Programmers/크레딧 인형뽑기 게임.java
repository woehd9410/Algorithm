import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
               int answer = 0;
        
        Stack<Integer> bucket = new Stack<>();
        @SuppressWarnings("unchecked")
		Stack<Integer> bo[]       = new Stack[board.length];
       
        
        for(int j = 0; j < board.length; j++) {
        	bo[j] = new Stack<>();
        	for(int i = board[j].length - 1; i >= 0; i--) {
        		if(board[i][j] == 0) break;
        		bo[j].add(board[i][j]);
        	}
        }
        
        for(int move : moves) {
        	move = move - 1;
        	if(bo[move].isEmpty()) continue;
        	int peek = bo[move].pop();
        	
        	if(!bucket.isEmpty()) {
        		if(peek == bucket.peek()) {
        			bucket.pop();
        			answer += 2;
        			continue;
        		}
        	}
        	bucket.add(peek);
        }
        return answer;
    }
}