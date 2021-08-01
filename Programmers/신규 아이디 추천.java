class Solution {
	public static String solution(String new_id) {
				String answer = "";
				
				// 1단계
				new_id = new_id.toLowerCase();
				
				// 2단계
				for(char ch : new_id.toCharArray()) {
					if(ch >= '0' && ch <= '9') answer += ch;
					else if(ch >= 'a' && ch <= 'z') answer += ch;
					else if(ch == '-' || ch == '_' || ch == '.' ) answer += ch;
				}
				
				new_id = answer;
				answer ="";
				boolean flag = false;
				for(char ch : new_id.toCharArray()) {
					if(ch == '.') {
						flag = true;
					}
					
					if(flag && ch != '.') {
						flag = false;
						answer += "." + ch;
						continue;
					}
					else if(ch != '.' && !flag) {
						answer += ch;
					}
					
				}
				if(flag) answer += new_id.charAt(new_id.length() - 1);
				new_id = answer;
				answer = "";
				int idx = 0;
				for(char ch : new_id.toCharArray()) {
					if((idx == 0 || idx == new_id.length() - 1) && ch == '.') {
						idx++;
						continue;
					}
					answer += ch;
					idx++;
				}
				new_id = answer;
			   if(new_id.equals("")) new_id = "a";
				
				if(new_id.length() > 15) {
					answer ="";
					for(int i = 0; i < 15; i++) {
						if(i == 14 && new_id.charAt(i) == '.') continue;
						answer += new_id.charAt(i);
					}        	
					new_id = answer;
					answer = "";
				}
				
				if(new_id.length() == 1) new_id += new_id + new_id;
				else if(new_id.length() == 2) new_id += new_id.charAt(1);
				
				answer = new_id;
				return answer;
		}
	}