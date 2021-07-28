import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
         String answer = "";
        HashMap<String, Integer> hm = new HashMap<>();
        
        for(String part : participant) {
        	if(hm.get(part) == null) {
        		hm.put(part, 1);
        	}else {
        		int num = hm.get(part) + 1;
        		hm.put(part, num);
        	}
        }
        
        for(String comp : completion) {
        	if(hm.get(comp) != null) {
        		int num = hm.get(comp) - 1;
        		hm.put(comp, num);
        	}
        }
        
        for(String key : hm.keySet()) {
        	if(hm.get(key) == 1) answer = key;
        }
        return answer;
    }
}