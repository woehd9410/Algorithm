import java.util.*;
import java.io.*;
class Solution {
    public int solution(String s) {
         int answer = 0;
        int count = 0;
        int innerCnt = 0;
        int idx = 0;
        Stack<Character> stack = new Stack<>();
        while(count < s.length()) {
        	idx = count;
        	stack.clear();
        	while(innerCnt < s.length()) {
        		if(idx == s.length()) idx = 0;
        		if(!stack.isEmpty()) {
        			if(stack.peek() == '[' && s.charAt(idx) == ']' ||
        			   stack.peek() == '{' && s.charAt(idx) == '}' ||
        			   stack.peek() == '(' && s.charAt(idx) == ')' ) stack.pop();
        			else stack.push(s.charAt(idx));
        		}else {
        			stack.push(s.charAt(idx));
        		}
        		idx++;
        		innerCnt++;
        	}
        	innerCnt = 0;
        	if(stack.isEmpty()) answer++;
        	count++;
        }
        return answer;
    }
}