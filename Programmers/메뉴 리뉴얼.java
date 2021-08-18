import java.util.*;
class Solution {
	static char[] sel;
	static HashMap<String, Integer> map;
	static boolean[] chk;
    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        map = new HashMap<String,Integer>();
        chk = new boolean[26];
        ArrayList<String> list = new ArrayList<>();
        List<String> KeySetList;
        for(int i = 0; i < course.length; i++) {
        	sel = new char[course[i]];
        	map.clear();
        	for(int j = 0; j < orders.length; j++) {
        		char[] orderList = orders[j].toCharArray();
        		Arrays.sort(orderList);
        		Combi(0,0, course[i], orderList);
        	}
        	KeySetList = new ArrayList<>(map.keySet());
        	Collections.sort(KeySetList, (o1, o2) -> (map.get(o2).compareTo(map.get(o1))));
        	
        	int num = 0; int idx = 0;
        	for(String key : KeySetList) {
        		if(idx == 0) {
        			if(map.get(key) < 2) break;
        			list.add(key);
        			num = map.get(key);
        		}else {
        			if(map.get(key) == num) list.add(key);
        			else break;
        		}
        		idx++;
        	}
        }        
        answer = new String[list.size()];
        int idx = 0;
        for(String ans : list) {
        	answer[idx] = ans;
        	idx++;
        }
        Arrays.sort(answer);		
        return answer;
    }
	private static void Combi(int idx, int cnt, int size, char[] orderList) {
		if(cnt == size) {
			String tmp = "";
			for(int i = 0; i < size; i++) {
				tmp += sel[i];
			}
			if(map.containsKey(tmp)) {
				int num = map.get(tmp);
				map.replace(tmp, num+= 1);
			}else {
				map.put(tmp, 1);
			}
			
			return;
		}
		for(int i = idx; i < orderList.length; i++) {
//			if(chk[order.charAt(i) - 'A']) continue;
//			chk[order.charAt(i) - 'A'] = true;
			sel[cnt] = orderList[i];
			Combi(i + 1, cnt + 1, size, orderList);
//			chk[order.charAt(i) - 'A'] = false;
		}
	}
}