import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = stoi(st.nextToken());
		int L = stoi(st.nextToken());
		
		Deque<Node> dq = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int now = stoi(st.nextToken());
			
			while(!dq.isEmpty() && dq.getLast().value > now) {
				dq.removeLast();
			}
			dq.add(new Node(i,now));
			
			if(dq.getFirst().index <= i - L) {
				dq.removeFirst();
			}
			sb.append(dq.getFirst().value + " ");
		}
		System.out.println(sb);
	}
	private static int stoi(String nextToken) {
		return Integer.parseInt(nextToken);
	}
	
	static class Node{
		int index;
		int value;
		public Node(int index, int value){
			this.index = index;
			this.value = value;
		}
	}
}