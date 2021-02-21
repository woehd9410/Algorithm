import java.util.*;
import java.util.stream.IntStream;

public class Main {	
	
	static int[] sel;
	static ArrayList<int[]> arr;
	static String[] dup;
	static boolean[] visit;
    public static int solution(String[][] relation) {
        int answer = 0;
        int column = relation[0].length;
        int row = relation.length;
        arr = new ArrayList<int[]>();
        sel = new int[column];
        dup = new String[68];
        visit = new boolean[column];
        for(int j = 0; j < column; j++) {
        		visit[j] = false;
        		sel[j] = -1;
        	}
        for(int i = 0; i < column; i++) {
        	Combi(0, 0, i + 1, column, row, relation);
        	for(int j = 0; j < column; j++) {
        		visit[j] = false;
        		sel[j] = -1;
        	}
        }
        answer = arr.size();
        return answer;
    }
	private static void Combi(int idx, int cnt, int size, int column, int row, String[][] relation) {
		if(cnt == size) {
			for(int i = 0; i < arr.size(); i++) {
				int num = 0;
				int[] tmp = arr.get(i);
				for(int j = 0; j < tmp.length; j++) {
					int now = tmp[j];
					if(IntStream.of(sel).anyMatch(x -> x == now)) {
						num++;
					}
				}
				if(num == tmp.length) return;
			}
			
            for(int i = 0; i < row; i++) {
                String str = null;
                for(int j = 0; j < size; j++) {
                    str += relation[i][sel[j]];
                }
                dup[i] = str;
            }
			Set<String> set = new HashSet<>();
			set.addAll(Arrays.asList(dup));
			if(row + 1 == set.size()) {
				int[] tmp = new int[cnt];
				for(int i = 0; i < cnt; i++) {
					tmp[i] = sel[i];
				}
				arr.add(tmp);
			}
			return;
		}
		for(int i = idx; i < column; i++) {
			if(visit[i]) continue;
			sel[cnt] = i;
			visit[i] = true;
			Combi(i + 1, cnt + 1, size, column, row, relation);
			visit[i] = false;
		}
	}
}

