import java.util.*;
public class Main {
	static ArrayList<Integer>[] list;
	static ArrayList<Integer> numbers;
	public static void main(String[] args) {
		int[] answer = {};
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		answer = solution(info, query);
		for(int idx : answer) {
			System.out.println(idx);
		}
	}
    public static int[] solution(String[] infos, String[] querys) {
        int[] answer = new int[querys.length];
        list = new ArrayList[3222 + 1];
        numbers = new ArrayList<>();
        for(int i = 0; i < list.length; i++) {
        	list[i] = new ArrayList<>();
        }
        int nums[] = new int[4];
        for(String info : infos) {
        	
        	String[] str = info.split(" ");
        	if(str[0].equals("cpp")) nums[0] = 1;
        	else if(str[0].equals("java")) nums[0] = 2;
        	else if(str[0].equals("python")) nums[0] = 3;
        		

        	if(str[1].equals("backend")) nums[1] = 1;
        	else if(str[1].equals("frontend")) nums[1] = 2;

        	if(str[2].equals("junior")) nums[2] = 1;
        	else if(str[2].equals("senior")) nums[2] = 2;


        	if(str[3].equals("chicken")) nums[3] = 1;
        	else if(str[3].equals("pizza")) nums[3] = 2;
    		int[] num1 = new int[4];
        	Combi(0, nums, num1);
        	for(int number : numbers) {
        		list[number].add(Integer.parseInt(str[4]));
        	}
        	numbers.clear();
        }
        
        
        int i = 0; boolean chk;
        for(int j = 0; j < list.length; j++) {
        	Collections.sort(list[j]);
        }
        for(String query : querys) {
        	chk = false;
        	String[] str = query.split(" ");
        	if(str[0].equals("cpp")) nums[0] = 1;
        	else if(str[0].equals("java")) nums[0] = 2;
        	else if(str[0].equals("python")) nums[0] = 3;
        	else nums[0] = 0;


        	if(str[2].equals("backend")) nums[1] = 1;
        	else if(str[2].equals("frontend")) nums[1] = 2;
        	else nums[1] = 0;

        	if(str[4].equals("junior")) nums[2] = 1;
        	else if(str[4].equals("senior")) nums[2] = 2;     
        	else nums[2] = 0;

        	if(str[6].equals("chicken")) nums[3] = 1;
        	else if(str[6].equals("pizza")) nums[3] = 2;  
        	else nums[3] = 0;
        	
        	int tmp = nums[0] * 1000 + nums[1] * 100 + nums[2] * 10 + nums[3] * 1;
        	
        	if(!list[tmp].isEmpty()) {
    			//ÀÌÁøÅ½»ö
        		int idx = Collections.binarySearch(list[tmp], Integer.parseInt(str[7]));
        		if(idx >= 0) {
        			for(int a = idx - 1; a >= 0; a--) {
        				if(list[tmp].get(idx) - list[tmp].get(a) > 0) break;
        				else idx = a;
        			}
        			answer[i] = list[tmp].size() - idx;
        		}else {
        			answer[i] = list[tmp].size() + idx + 1;
        		}
    		}
        	i++;
        }
        return answer;
    }
    private static void Combi(int cnt, int[] nums, int[] num1) {
    	if(cnt == 4) {
			int num = num1[0] * 1000 + num1[1] * 100 + num1[2] * 10 + num1[3] * 1;
			numbers.add(num);
			return;
    	}
    	for(int i = 0; i < 2; i++) {
    		if(i == 0) num1[cnt] = 0;
    		else       num1[cnt] = nums[cnt];
    		Combi(cnt + 1, nums, num1);
    	}
    }
}