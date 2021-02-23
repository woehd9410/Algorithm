import java.util.*;

public class Main {	
	static class struct implements Comparable<struct>{
		String filename;
		String header;
		int body;
		String tail;
		int order;
		struct(String filename, String header, int body, String tail, int order){
			this.filename = filename;
			this.header = header;
			this.body = body;
			this.tail = tail;
			this.order = order;
		}
		@Override
		public int compareTo(struct o) {
			if(this.header.toLowerCase().compareTo(o.header.toLowerCase()) < 0) {
				return -1;
			}
			else if(this.header.toLowerCase().compareTo(o.header.toLowerCase()) == 0) {
				if(this.body < o.body) {
					return -1;
				}
				else if(this.body == o.body) {
					if(this.order < o.order) {
						return -1;
					}
				}
			}
			return 1;
		}
	}
	public static void main(String[] args) {
		String[] answer = {};
		String[] files = {"foo9.txt", "foo010bar020.zip", "F-15", "img0", "IMG01", "img2.JPG"};
		answer = solution(files);
		for(int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
	public static String[] solution(String[] files) {
        String[] answer = {};
        ArrayList<struct> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        int index = 0;
        for(String file : files) {
        	struct st = new struct(file,"",0,"", index);
        	int i = 0;
        	while(true) {
        		if(file.charAt(i) >= '0' && file.charAt(i) <= '9') {
        			break;
        		}
        		st.header += file.charAt(i);
        		i++;
        	}
        	String str = ""; boolean flag = false;
        	for(int k = 0; k < 5; k++) {
        		if(i == file.length()) break;
        		if(file.charAt(i) >= '0' && file.charAt(i) <= '9') {
        			flag = true;
            		str += file.charAt(i);
            		i++;
        		}
        		else {
        			break;
        		}
        	}
        	st.body = Integer.valueOf(str);
        	str = "";
        	while(i < file.length()) {
        		str += file.charAt(i);
        		i++;
        	}
        	st.tail = str;
        	list.add(st);
        	index++;
        }
        Collections.sort(list);
        
        answer = new String[list.size()];
        for(int i = 0; i < list.size(); i++) {
        	answer[i] = list.get(i).filename;
        }
        return answer;
    }
}

