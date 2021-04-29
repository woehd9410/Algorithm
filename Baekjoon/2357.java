/*
 * TITLE            : [2357 | 최솟값과 최댓값 ] 
 * DATE             : 2021-04-29
 */

import java.util.*;
import java.io.*;

public class Main {
	
	//Global Variables
	static int N; // 1 ~ 100,000
	static int M; // 1 ~ 100,000 
	static final int MAX = 1000000001;
	static final int MIN  = 0;
	
	//Arrays
	static int[]   arr;
	static Node[] tree;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		arr = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			arr[i] = stoi(br.readLine());
		}
		
		tree = new Node[N * 4];
		for(int i = 0; i < N * 4; i++) {
			tree[i] = new Node(MAX, MIN);
		}
		init(1, N, 1);
		
		for( int i = 0;i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			Node node = getMinMax(1, N, a, b, 1);
			sb.append(node.min + " " + node.max + "\n");
		}
		System.out.println(sb);
	}

	

	private static Node getMinMax(int start, int end, int nodeLeft, int nodeRight, int index) {
		if(nodeRight < start || nodeLeft > end) return new Node(MAX, MIN);
		
		if(nodeLeft <= start && nodeRight >= end) return tree[index];
		
		int mid      = (start + end) / 2;
		Node left   = getMinMax(start, mid, nodeLeft, nodeRight, index * 2);
		Node right = getMinMax(mid + 1, end, nodeLeft, nodeRight, index * 2  + 1);
		
		Node node = new Node(0, 0);
		node.min = Math.min(left.min,  right.min);
		node.max = Math.max(left.max,  right.max);
		return node;
	}



	private static Node init(int start, int end, int index) {
		if(start == end) {
			tree[index].max = tree[index].min = arr[start];
			return tree[index];
		}
		
		int mid = (start + end) / 2;
		Node left = init(start, mid, index * 2);
		Node right = init(mid + 1, end, index * 2 + 1);
		
		tree[index].min = left.min < right.min ? left.min : right.min;
		tree[index].max = left.max > right.max ? left.max : right.max;
		return tree[index];
		
	}

	private static int stoi(String str) {
		// TODO Auto-generated method stub
		return Integer.parseInt(str);
	}
	
	static class Node{
		int min;
		int max;
		
		public Node(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}
}