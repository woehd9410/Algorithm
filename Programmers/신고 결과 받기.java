import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
         int[] answer = new int[id_list.length];
	        
	        //누가 누구를 신고했는지 담아둘 배열
	        //누가 얼마나 신고 당했는지 담아둘 배열
	        int idx = 0;
	        HashMap<String, Integer> IdxInfo = new HashMap<String, Integer>();
	        for(String id : id_list) {
	        	IdxInfo.put(id, idx++);
	        }
	        
	        @SuppressWarnings("unchecked")
			LinkedList<String>[] st = new LinkedList[id_list.length];
	        for(int i = 0;  i < st.length; i++) {
	        	st[i] = new LinkedList<>();
	        }
	        int[] repCnt = new int[id_list.length];
        
	        HashSet<String> hs = new HashSet<String>(Arrays.asList(report));
	        String[] repNoDup = hs.toArray(new String[0]);
        
	        for(String rep : repNoDup) {
	        	String tmp[] = rep.split(" ");
	        	
	        	st[IdxInfo.get(tmp[0])].add(tmp[1]);
	        	repCnt[IdxInfo.get(tmp[1])]++;
	        }
	        
	        int cnt = 0;
	        idx = 0;
	        for(String id : id_list) {
	        	cnt = 0;
	        	for(int i = 0; i < st[IdxInfo.get(id)].size(); i++) {
	        		if(repCnt[IdxInfo.get(st[IdxInfo.get(id)].get(i))] >= k) cnt++;
	        	}
	        	answer[idx++] = cnt;
	        }
	        return answer;
    }
}