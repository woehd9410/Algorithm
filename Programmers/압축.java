import java.util.*;
class Solution {
    public static int[] solution(String msg) {
		int[] answer = {};
		HashMap<String, Integer> Dictionary = new HashMap<>();
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 1; i < 27; i++) {
			String alpha = Character.toString((char)('A' + (i - 1)));
			int num = i;
			Dictionary.put(alpha, num);
		}
		for(int i = 0; i < msg.length(); i++) {
			int idx = i; int now = 0;
			String tmp = Character.toString(msg.charAt(i));
			while(true) {
				if(Dictionary.containsKey(tmp)) {
					now = Dictionary.get(tmp);
				}else {
					if(idx + 1 < msg.length()) {
						Dictionary.put(tmp, Dictionary.size() + 1);
					}
					i = idx - 1;
					break;
				}
				if(idx + 1 < msg.length()) {
					tmp += msg.charAt(idx + 1);
					idx++;
				}
				else {
                    i = idx;
					break;
				}
			}
			list.add(now);
			
		}
		answer = new int[list.size()];
		for(int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}
}