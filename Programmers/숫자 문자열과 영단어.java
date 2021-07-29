class Solution {
    public int solution(String s) {
   int answer = 0;
        String tmp = "";
        for(int i = 0; i < s.length(); i++) {
        	if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
        		tmp += s.charAt(i);
        		continue;
        	}
        	
        	if(s.charAt(i) == 'z') {
        		tmp += '0';
        		i += 3;
        	}
        	else if(s.charAt(i) == 'o') {
        		tmp += '1';
        		i += 2;
        	}
        	else if(s.charAt(i) == 't') {
        		if(s.charAt(i + 1) == 'w') {
        			tmp += '2';
        			i += 2;
        		}
        		else if(s.charAt(i + 1) == 'h') {
        			tmp += '3';
        			i += 4;
        		}
        	}
        	else if(s.charAt(i) == 'f') {
        		if(s.charAt(i + 1) == 'o') {
        			tmp += '4';
        			i += 3;
        		}
        		else if(s.charAt(i + 1) == 'i') {
        			tmp += '5';
        			i += 3;
        		}
        	}
        	else if(s.charAt(i) == 's') {
        		if(s.charAt(i + 1) == 'i') {
        			tmp += '6';
        			i += 2;
        		}
        		else if(s.charAt(i + 1) == 'e') {
        			tmp += '7';
        			i += 4;
        		}
        	}
        	else if(s.charAt(i) == 'e') {
        		tmp += '8';
        		i += 4;
        	}
        	else if(s.charAt(i) == 'n') {
        		tmp += '9';
        		i += 3;
        	}
        }
        answer = Integer.parseInt(tmp);
        return answer;
    }
}