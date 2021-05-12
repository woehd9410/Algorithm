import java.util.*;
import java.io.*;
class Solution {
	
	static ArrayList<Integer>[] list;
	static Set<String> list1;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		String[] user_id     = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"*rodo", "*rodo", "******"};
		System.out.println(solution(user_id, banned_id));
	}
	public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
		
		list = new ArrayList[9];
		list1 = new LinkedHashSet<>();

		for(int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		
        for(int i = 0; i < banned_id.length; i++) {
        	possibleWord(i, banned_id[i], user_id);
        }
        
        selected = new boolean[9];
        
        DFS(0, banned_id.length);
        answer = list1.size();
        return answer;
    }

	private static void DFS(int cnt , int N) {
		if(cnt == N) {
			String str = "";
			int index = 0;
			for(int i = 0; i < selected.length; i++) {
				if(selected[i]) str += i;
			}
			list1.add(str);
		}
		for(int i = 0; i < list[cnt].size(); i++) {
			int num = list[cnt].get(i);
			if(selected[num]) continue;
			selected[num] = true;
			DFS(cnt + 1, N);
			selected[num] = false;
		}
	}
	private static void possibleWord(int idx, String banned_id, String[] user_id) {
		boolean possible = true;
		for(int i = 0; i < user_id.length; i++) {
			possible = true;
			if(user_id[i].length() != banned_id.length()) continue;
			for(int j = 0; j < banned_id.length(); j++) {
				if(banned_id.charAt(j) == '*') continue;
				if(banned_id.charAt(j) != user_id[i].charAt(j)) {
					possible = false;
					break;
				}
			}
			if(!possible) continue;
			else            list[idx].add(i);
		}
		
	}
	
}