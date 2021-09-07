import java.util.*;
class Solution {
static class songs implements Comparable<songs>{
		int turn;
		int time;
		String name;
		public songs(int turn , int time, String name) {
			this.turn = turn;
			this.time = time;
			this.name = name;
		}
		public int compareTo(songs o) {
			if(this.time < o.time) {
				return 1;
			}
			else if(this.time == o.time) {
				if(this.turn > o.turn)
					return 1;
			}
			return -1;		
		}
		
	}
	public static void main(String[] args) throws Exception {
		String m = "ABC";
		String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB",
				               "13:00,13:05,WORLD,ABCDEF"};
		System.out.println(solution(m,musicinfos));
	}
   
    public static String solution(String m, String[] musicinfos) {
        String answer = "";
        int mSize = m.length();
        ArrayList<songs> list = new ArrayList<>();
        for(int i = 0; i < m.length(); i++) {
        	if(m.charAt(i) == '#') mSize--;
        }
        for(int i = 0; i < musicinfos.length; i++) {
        	String[] song = musicinfos[i].split(",");
        	String start[] = song[0].split(":");
        	String finish[] = song[1].split(":");
        	songs tmp = new songs(0, 0, "name");
        	int startTime = (Integer.parseInt(start[0]) * 60) + Integer.parseInt(start[1]);
        	int finishTime = (Integer.parseInt(finish[0]) * 60) + Integer.parseInt(finish[1]);
        	
        	int time = finishTime - startTime;
        	tmp.time = time;
        	String melody = "";
        	char input = ' ';
        	int idx = 0;
        	String mTmp = "";
        	for(int j = 0; j < m.length(); j++) {
        		input = m.charAt(j);
        		if(j + 1 < m.length() && m.charAt(j + 1) == '#') {
        			if(m.charAt(j) == 'A') input = 'v';
        			if(m.charAt(j) == 'C') input = 'w';
        			if(m.charAt(j) == 'D') input = 'x';
        			if(m.charAt(j) == 'F') input = 'y';
        			if(m.charAt(j) == 'G') input = 'z';
        			j++;
        		}
        		mTmp += input;
        	}
        	// 총 재생된 멜로디 생성 작업
        	while(time > 0) {
        		input = song[3].charAt(idx);
        		if(idx + 1 < song[3].length() && song[3].charAt(idx + 1) == '#') {
        			if(song[3].charAt(idx) == 'A') input = 'v';
        			if(song[3].charAt(idx) == 'C') input = 'w';
        			if(song[3].charAt(idx) == 'D') input = 'x';
        			if(song[3].charAt(idx) == 'F') input = 'y';
        			if(song[3].charAt(idx) == 'G') input = 'z';
        			idx++;
        		}
        		melody += input;
        		idx++;
        		if(idx == song[3].length()) idx = 0;
        		time--;
        	}
//        	System.out.println(melody);
        	if(melody.contains(mTmp)) {
        		tmp.name = song[2];
        		tmp.turn = i;
        		list.add(tmp);
        	}
        }
        Collections.sort(list);
        if(list.size() == 0) answer = "(None)";
        else answer = list.get(0).name;
        return answer;
    }
}