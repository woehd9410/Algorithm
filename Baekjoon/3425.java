import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<String> orders;
	static ArrayList<Long> numbers;
	static ArrayList<String> results;
	static int N;
	static int MAX = 1000000000;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		orders = new ArrayList<>();
		numbers = new ArrayList<>();
		results = new ArrayList<>();
		while(true) {
			String order = br.readLine();
			if(order.equals("QUIT")) break;
			if(order.equals("END")) {
				N = Integer.parseInt(br.readLine());
				long num = 0;
				for(int i = 0; i < N; i++) {
					num = Long.parseLong(br.readLine());
					numbers.add(num);
					if(Simulate()) results.add(numbers.get(0).toString());
					else           results.add("ERROR");
					numbers.clear();
				}
				orders.clear();
				for(String result : results) {
					System.out.println(result);
				}
				System.out.println();
				results.clear();
			}
			else {
				orders.add(order);
			}
		}
		
	}
	private static boolean Simulate() {
		for(String order : orders) {
			//숫자가 부족해서 연산을 수행할 수 없을 때, 0으로 나눴을 때 (DIV, MOD), 연산 결과의 절댓값이 10의 9승을 넘어갈 때는 모두 프로그램 에러
			if(order.length() > 3) {
				String[] tmp = order.split(" ");
				NUM(Integer.parseInt(tmp[1]));
			}
			else {
				switch (order) {
				case "POP":
					if(numbers.isEmpty()) return false;
					else POP();
					break;
				case "INV":
					if(numbers.isEmpty()) return false;
					else INV();
					break;
				case "DUP":
					if(numbers.isEmpty()) return false;
					else DUP();
					break;
				case "SWP":
					if(numbers.size() <= 1) return false;
					else SWP();
					break;
				case "ADD":
					if(numbers.size() <= 1) return false;
					else ADD();
					break;
				case "SUB":
					if(numbers.size() <= 1) return false;
					else SUB();
					break;
				case "MUL":
					if(numbers.size() <= 1) return false;
					else MUL();
					break;
				case "DIV":
					if(numbers.size() <= 1) return false;
					if(numbers.get(numbers.size() - 1) == 0) return false;
					DIV();
					break;
				case "MOD":
					if(numbers.size() <= 1 || numbers.get(numbers.size() - 1) == 0) return false;
					if(numbers.get(numbers.size() - 1) == 0) return false;
					MOD();
					break;
				default:
					break;
				}
			}
		}
		if(numbers.size() == 0) return false;
		if(numbers.size() != 1) return false;
		if(Math.abs(numbers.get(numbers.size() - 1)) > MAX) return false;
		return true;
	}
	private static void MOD() {
		int firstIdx  = numbers.size() - 2;
		int secondIdx = numbers.size() - 1;
		
		long num = Math.abs(numbers.get(firstIdx)) % Math.abs(numbers.get(secondIdx));
		if(numbers.get(firstIdx) < 0) {
			num *= -1;
		}
		numbers.remove(secondIdx);
		numbers.set(secondIdx - 1, num);
	}
	private static void DIV() {
		int firstIdx  = numbers.size() - 2;
		int secondIdx = numbers.size() - 1;
		
		long num = Math.abs(numbers.get(firstIdx)) / Math.abs(numbers.get(secondIdx));
		if(numbers.get(firstIdx) * numbers.get(secondIdx) < 0) {
			num *= -1;
		}
		numbers.remove(secondIdx);
		numbers.set(secondIdx - 1, num);
	}
	private static void MUL() {
		int firstIdx  = numbers.size() - 2;
		int secondIdx = numbers.size() - 1;
		
		long num = numbers.get(firstIdx) * numbers.get(secondIdx);
		numbers.remove(secondIdx);
		numbers.set(secondIdx - 1, num);
	}
	private static void SUB() {
		int firstIdx  = numbers.size() - 2;
		int secondIdx = numbers.size() - 1;
		
		long num = numbers.get(firstIdx) - numbers.get(secondIdx);
		numbers.remove(secondIdx);
		numbers.set(secondIdx - 1, num);
	}
	private static void ADD() {
		int firstIdx  = numbers.size() - 2;
		int secondIdx = numbers.size() - 1;
		
		long num = numbers.get(firstIdx) + numbers.get(secondIdx);
		numbers.remove(secondIdx);
		numbers.set(secondIdx - 1, num);
	}
	private static void SWP() {
		int firstIdx  = numbers.size() - 2;
		int secondIdx = numbers.size() - 1;
		
		long tmp = numbers.get(firstIdx);
		numbers.set(firstIdx, numbers.get(secondIdx));
		numbers.set(secondIdx, tmp);
	}
	private static void DUP() {
		int index = numbers.size() - 1;
		long num = numbers.get(index);
		numbers.add(num);
	}
	private static void INV() {
		int index = numbers.size() - 1;
		numbers.set(index, numbers.get(index) * -1);
	}
	private static void POP() {
		numbers.remove(numbers.size() - 1);
	}
	private static void NUM(long num) {
		numbers.add(num);
	}

}
