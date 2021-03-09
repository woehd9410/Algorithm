import java.util.*;
import java.io.*;



public class Solution {

	public static void main(String[] args) throws IOException {
		int answer = solution("1S2D*3T");
		System.out.println(answer);
	}
	public static int solution(String dartResult) {
        int answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        int num = 0;
        for(int i = 0; i < dartResult.length(); i++) {
        	if(dartResult.charAt(i) >= '0' && dartResult.charAt(i) <= '9') {
        		if(i != 0) {
        			list.add(num);
        			num = 0;
        		}
        		if(dartResult.charAt(i) == '1' && dartResult.charAt(i+1) == '0'){
        			num = 10;
        			i++;
        			continue;
        		}
        		num = dartResult.charAt(i) - '0';
        	}
        	
        	if(dartResult.charAt(i) == 'D') num *= num;
       		if(dartResult.charAt(i) == 'T') num *= num * num;
       		
       		if(dartResult.charAt(i) == '*') {
       			num *= 2;
       			if(!list.isEmpty()) list.set(list.size() - 1, list.get(list.size() - 1) * 2);
       		}
       		if(dartResult.charAt(i) == '#') num *= -1;
       		
       		
         }
        list.add(num);
        for(int ans : list) {
        	answer += ans;
        }
        return answer;
    }
}