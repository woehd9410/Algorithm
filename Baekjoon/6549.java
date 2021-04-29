/*
 * TITLE            : [6549 | 히스토그램에서 가장 큰 직사각형] 
 * DATE             : 2021-04-29 
 */

import java.util.*;
import java.io.*;

public class Main {
	
	//Global Variable
	static int n;
	static final int INF = 0x7fffffff;
	
	//Arrays
	static int[] arr;
	static int[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(true) {
			 st = new StringTokenizer(br.readLine());
			 
			 n = stoi(st.nextToken());
			if(n == 0) break;
			
			arr   = new int[n + 1];
			tree = new int[n  * 4];

			for(int i = 1; i <= n; i++) {
				arr[i] = stoi(st.nextToken());
			}
			
			init(1, n, 1);
		
			sb.append(getMaxWidth(1, n) + "\n");
		}
		System.out.println(sb);
	}


	private static long getMaxWidth(int start, int end) {
		long maxWidth, tmpWidth;
		int minIndex = query(1, n, start, end, 1);
		
		maxWidth = (long) (end - start + 1) * (long) arr[minIndex];
		
		if(start <= minIndex - 1) {
			tmpWidth = getMaxWidth(start, minIndex - 1);
			maxWidth = Math.max(tmpWidth, maxWidth);
		}
		
		if(end >= minIndex + 1) {
			tmpWidth = getMaxWidth(minIndex + 1, end);
			maxWidth = Math.max(tmpWidth, maxWidth);
		}
		
		return maxWidth;
	}


	private static int query(int start, int end, int nodeLeft, int nodeRight, int index) {
		if(nodeRight < start || nodeLeft > end) return INF;
		
		if(nodeLeft <= start && nodeRight >= end) return tree[index];
		
		int mid = (start + end) / 2;
		int leftMinIndex = query(start, mid, nodeLeft, nodeRight, index << 1);
		int rightMinIndex = query(mid + 1, end, nodeLeft, nodeRight, (index << 1) + 1);
		
		if(leftMinIndex == INF) return rightMinIndex;
		else if(rightMinIndex == INF) return leftMinIndex;
		else return arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
	}


	private static int init(int start, int end, int index) {
		if(start == end) return tree[index] = start;
		
		int mid  = (start + end) / 2;
		int left  = init(start     , mid, index << 1);
		int right = init(mid + 1, end, (index << 1) + 1);
		
		return tree[index] = arr[left] < arr[right] ? left : right;
	}


	private static int stoi(String str) {
		// TODO Auto-generated method stub
		return Integer.parseInt(str);
	}
}