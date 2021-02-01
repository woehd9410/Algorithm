import java.io.*;

import java.util.*;

public class Main {	
	static char[] sel;
	static ArrayList<Character> ch;
	static String[] numbers;
	static ArrayList<Oper> oper;
	static long answer;
	static class Oper{
		char oper;
		int  idx;
		int  priority;
		
		Oper(char oper, int idx, int priority){
			this.oper = oper;
			this.idx = idx;
			this.priority = priority;
		}
	}
	public static void main(String[] args) throws Exception {
		String expression = "50*6-3*2";
		long answer = solution(expression);
		
		System.out.println(answer);
	}
	 static long solution(String expression) {
		answer = 0;
		sel = new char[3];
		for(int i = 0; i < 3; i++) {
			sel[i] = '0';
		}
		// 첫번째 수식과 연산식을 분리해주는 작업 필요
		numbers = expression.split("\\+|\\-|\\*");
		char[] chr = expression.toCharArray();
		
		oper = new ArrayList<>();
		
		int idx = 0;
		ch = new ArrayList<>();
		for(int i = 0; i < chr.length; i++) {
			if(chr[i] == '+' || chr[i] == '-' || chr[i] == '*') {
				 Oper temp = new Oper('i',0,0);
				 if(!ch.contains(chr[i])) ch.add(chr[i]);
				 temp.oper = chr[i]; temp.idx = idx; temp.priority = 0; 
				 oper.add(temp);
			}
		}
		
		dfs(0,ch.size());
	
		return answer;
	}
	private static void dfs(int cnt, int N) {
		if(cnt == N) {
			//연산
			long value = 0;
			String[] tmp = new String[numbers.length];
			for(int i = 0; i < numbers.length; i++) {
				tmp[i] = numbers[i];
			}
			long left = 0; long right = 0;
			int leftCnt = 0; int rightCnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < oper.size(); j++) {
					left = 0; right = 0;
					if(oper.get(j).oper == sel[i]) {
						leftCnt = j; rightCnt = j + 1;
						while(true){
							if(!tmp[leftCnt].equals("E")) {
								
								left = Long.parseLong(tmp[leftCnt]);
								break;
							}
							leftCnt--;
						}
						while(true){
							if(rightCnt >= tmp.length) break;
							if(!tmp[rightCnt].equals("E")) {
								right = Long.parseLong(tmp[rightCnt]);
								break;
							}
							rightCnt++;
						}
						if(oper.get(j).oper == '+') {
							tmp[leftCnt] = Long.toString(left + right);
						}
						else if(oper.get(j).oper == '-') {
							tmp[leftCnt] = Long.toString(left - right);
						}
						else if(oper.get(j).oper == '*') {
							tmp[leftCnt] = Long.toString(left * right);
						}
						if(rightCnt < tmp.length) tmp[rightCnt] = "E";
					}
				}
			}
			for(int i = 0; i < tmp.length; i++) {
				if(!tmp[i].equals("E")) {
					value = Long.parseLong(tmp[i]);
				}
			}
			value = Math.abs(value);
			if(answer < value) answer = value;
			
			return;
		}
		
		for(int k = 0; k < N; k++) {
			if(sel[k] != '0') continue;
			sel[k] = ch.get(cnt);
			dfs(cnt + 1 , N);
			sel[k] = '0';
		}
	}

}

