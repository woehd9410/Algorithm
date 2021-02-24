import java.util.*;

public class Main {	
	public static void main(String[] args) throws NumberFormatException {
		String result = solution(16,16,2,1);
		System.out.println(result);
	}
	
	public static String solution(int n, int t, int m, int p) {
        String answer = "";
        String str = "";
        int idx = 0; 
        // 진수 String 다 저장하는 로직
        Stack<Character> stack = new Stack<>();
        while(true) {
        	if(str.length() >= m * t) break;
        	int num = idx;
        	char remainder = '0'; int remain = 0;
        	while(true) {
        		remain = num % n;
        		if(n >= 10 && n <= 16) {
        			if(remain >= 10 && remain <= 15) {
        				remainder = (char)(55 + remain);
        				stack.add(remainder);
        			}
        			else {
        				remainder = (char)(remain + '0');
            			stack.add(remainder);
        			}
        		}
        		else {
        			remainder = (char)(remain + '0');
        			stack.add(remainder);
        		}
        		if(num < n) {
	    			break;
        		}
        		num /= n;

        	}
        	while(!stack.isEmpty()) {
        		str += stack.peek();
        		stack.pop();
        	}
        	idx++;
        }
        int time = 0; boolean flag = false;
        for(int i = 0; i < str.length(); i++) {
        	if(answer.length() == t)break;
        	if((i % m) == p - 1) {
        		answer += str.charAt(i);
        	}
        }
        return answer;
    }
}

