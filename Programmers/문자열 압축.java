import java.util.*;

public class Main {	
	public static int solution(String s) {
        int answer = 987654321;
        if(s.length() == 1) return 1;
        // s.length() / 2 단위까지 실행해보면서 가장 짧은 길이 비교해봄
        for(int n = 1; n <= (s.length() / 2); n++) {
        	int length = 0;   boolean flag = false;
        	int cnt = 0;
        	// 0번째 인덱스부터 비교
        	for(int i = 0; i < s.length(); i+=n) {
        		if(i + 2*n > s.length()) {
        			if(!flag) {
        				length += s.length() - i;
        				break;
        			}else {
        				flag = false;
        			}
        			continue;
        		}
        		String now  =  getStr(i, i + n, s);
        		String next =  getStr(i + n, i + 2*n, s);
        		
        		if(now.equals(next)) {
        			if(flag) {
	        				cnt++;
        				if(cnt == 9 || cnt == 99) length += 1;
        				continue;
        			}
        			flag = true;
        			cnt++;
        			length += n + 1;
        		}
        		else {
        			if(flag) {
        				cnt = 0;
        				flag = false;
        				continue;
        			}
        			length+=n;
        		}
        	}
        	if(answer > length) answer = length;
        	
        }
        	return answer;
	}
	private static String getStr(int start, int end, String s) {
		String str = "";
		for(int i = start; i < end; i++) {
			str += s.charAt(i);
		}
		return str;
	}
}

