/*
 * TITLE            : [5639 | 이진 검색 트리] 
 * DATE             : 2021-04-19
 * DESCRIPTION : Node의 개수 10,000개 이하 
 */

import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<Integer> preOrder;
	static ArrayList<Node>    trees;
	static int   idx;
	static Node rootNode;
	static class Node{
		int num;
		public Node(int num) {
			this.num = num;
		}
		Node left;
		Node right;
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		preOrder = new ArrayList<>();
		String str = null;
		while((str = br.readLine()) != null && str.length() != 0) {
			preOrder.add(stoi(str));
		}
		
		Node node = new Node(preOrder.get(0));
		rootNode = node;
		Node root = rootNode;
		for(int i = 0; i < preOrder.size() - 1; i++) {
			if(preOrder.get(i + 1) < preOrder.get(i)) {
				node.left        = new Node(preOrder.get(i + 1));
				node              = node.left;
			}
			else {
				node              = chk(rootNode, preOrder.get(i + 1));
				node.right       = new Node(preOrder.get(i + 1));
				if(node.num == rootNode.num) rootNode = node.right;
				node              = node.right;
			}
		}
		print(root);
	}
	private static void print(Node node) {
		if(node.left != null) print(node.left);
		if(node.right != null) print(node.right);
		System.out.println(node.num);
	}
	private static Node chk(Node node, int num) {
		while(true) {
			if(node.num > num) {
				node = node.left;
			}
			else {
				if(node.right != null) node = node.right;
				else break;
			}
		}
		
		return node;
	}
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
