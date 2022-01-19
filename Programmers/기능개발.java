import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) {
		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		solution(progresses, speeds);
	}
    
    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        
        Stack<Integer> st = new Stack<>();
        for(int i = speeds.length - 1; i >= 0; i--) {
        	int q = (100 - progresses[i]) / speeds[i];
        	int r = (100 - progresses[i]) % speeds[i];
        	
        	if(r != 0) q += 1;
        	st.add(q);
        }
        int cnt = 0;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        while(!st.isEmpty()) {
        	int top = st.peek();
        	cnt = 0;
        	while(!st.isEmpty() && st.peek() <= top) {
        		st.pop();
        		cnt++;
        	}
        	arr.add(cnt);
        }
        answer = new int[arr.size()];
        for(int i = 0; i < arr.size(); i++) {
        	answer[i] = arr.get(i);
        }
        return answer;
    }

	
}