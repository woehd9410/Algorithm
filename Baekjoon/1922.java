/*
 * TITLE            : [1922 | 네트워크 연결] 
 * DATE             : 2021-04-26 
 */

import java.util.*;
import java.io.*;

public class Main {
	
	//Global Variable
	static int N; // 컴퓨터 수 (1 <= N <= 1000)
	static int M; // 선의 수   (1 <= M <= 100000) 
	static int ans;
	
	//Priority Queue
	static PriorityQueue<Node> pq;
	
	//Array
	static int[] parent;
		
	public static void main(String[] args) throws IOException {
		
		input();
		mst();
		
	}

	private static void mst() {
		parent = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			int s = find(now.start);
			int e = find(now.end);
			
			if(s == e) continue;
			
			union(s, e);
			ans += now.value;
		}
		
		System.out.println(ans);
	}

	private static void union(int s, int e) {
		int sRoot = find(s);
		int eRoot = find(e);
		
		if(sRoot != eRoot) {
			parent[sRoot] = eRoot;
		}else {
			return;
		}
	}

	private static int find(int n) {
		if(n == parent[n]) {
			return n;
		}
		else {
			int num = find(parent[n]);
			parent[n] = num;
			return num;
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		M = stoi(br.readLine());
		
		StringTokenizer st;
		pq = new PriorityQueue<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			int c = stoi(st.nextToken());
			
			pq.add(new Node(a,b,c));
		}
		
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static class Node implements Comparable<Node>{
		int start;
		int end;
		int value;
		
		public Node(int start, int end, int value) {
			this.start = start;
			this.end   = end;
			this.value = value;
		}

		@Override
		public int compareTo(Node node) {
			return this.value - node.value;
		}
	}
}
