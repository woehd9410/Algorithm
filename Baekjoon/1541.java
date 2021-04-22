import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	static ArrayList<Character> oper;
	static String[] srr;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		srr = str.split("\\-");
		int minResult = 0;
		for(int i = 0; i < srr.length; i++) {
			int sum = calc(srr[i]);
			if(i != 0) minResult -= sum;
			else minResult += sum;
		}
		System.out.println(minResult);
		
		
	}
	private static int calc(String string) {
		int result = 0;
		String tmp[] = string.split("\\+");
		for(int i = 0; i < tmp.length; i++) {
			result += Integer.parseInt(tmp[i]);
		}
		return result;
	}
}