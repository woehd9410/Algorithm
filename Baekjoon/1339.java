/*
 * TITLE            : [1339 | 단어 수학] 
 * DATE             : 2021-04-21 
 */

import java.util.*;
import java.io.*;

public class Main {
	static class info implements Comparable<info>{
		int   sum;
		char alpha;
		public info (int sum , char alpha) {
			this.sum   = sum;
			this.alpha = alpha;
		}
		@Override
		public int compareTo(info o) {
			if(o.sum > this.sum) {
				return 1; 
			}
			else {
				return -1;
			}
		}
	}
	static int N; // 단어 개수 (1 <= N <= 10)
	static ArrayList<String>      words;
	static int[] numbers;
	static int   ans;
	static int[] alphas;
	static ArrayList<info>     list;
	public static void main(String[] args) throws IOException {
		input();
		getPriority();
		getSum();
		System.out.println(ans);
	}
	
	private static void getSum() {
		ans = 0;
		int sum, num;
		for(String word : words) {
			num = 1; sum = 0;
			for(int i = word.length() - 1; i >= 0; i--) {
				sum += alphas[word.charAt(i) - 'A'] * num;
				num *= 10;
			}
			ans += sum;
		}
	}

	private static void getPriority() {
		alphas = new int[26];
		for(String word : words) {
			int num = 1;
			for(int i = word.length() - 1; i >= 0; i--) {
				alphas[word.charAt(i) - 'A'] += num;
				num *= 10;
			}
		}		
		list = new ArrayList<>();
		for(int i = 0; i < 26; i++) {
			if(alphas[i] != 0) {
				list.add(new info(alphas[i],(char) ('A' + i)));
			}
			alphas[i] = 0;
		}
		Collections.sort(list);
		int num = 9;
		for(int i = 0; i < list.size(); i++) {
			int idx = list.get(i).alpha - 'A';
			alphas[idx] = num--;
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = stoi(br.readLine());
		String str = "";
		words     = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			 str = br.readLine();
			 words.add(str);
		}

	}
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
