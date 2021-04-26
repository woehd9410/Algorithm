/*
 * TITLE            : [1197 | 최소 스패닝 트리] 
 * DATE             : 2021-04-21 
 */

import java.util.*;
import java.io.*;

public class Main {
	
	// Global Variables
	static int V;
	static int E;
	
	static int A;
	static int B;
	static int C;
	static int ans;
	
	//pq
	static PriorityQueue<Node> nodes;
	
	//Array
	static int[] parent;
		
	public static void main(String[] args) throws IOException {
		
		// 입력
		input();
		mst();
	}
	
	private static void mst() {
		
		parent = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		
		while(!nodes.isEmpty()) {
			Node now = nodes.poll();
			
			int s = find(now.start);
			int e = find(now.end);
			
			if(s == e) continue;
			union(s,e);
			ans += now.value;
		}
		
		System.out.println(ans);
	}

	private static void union(int s, int e) {
		int sRoot = find(s);
		int eRoot = find(e);
		
		if(sRoot != eRoot) {
			parent[sRoot] = eRoot;
		}
		else {
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = stoi(st.nextToken());
		E = stoi(st.nextToken());
		
		nodes = new PriorityQueue<>();
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			A  = stoi(st.nextToken()); 
			B  = stoi(st.nextToken()); 
			C  = stoi(st.nextToken()); 
			
			nodes.add(new Node(A, B, C));
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
			this.start  = start;
			this.end   = end;
			this.value = value;
		}

		@Override
		public int compareTo(Node node) {
			return this.value - node.value;
		}
	}
}
