/*
 * TITLE : 트리인가? [6416]
 * DATE  : 2021-04-16
 */

import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<Integer>[] NodesTo;
	static ArrayList<Integer>[] NodesFrom;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		int k = 1;
		NodesTo    = new ArrayList[100000 + 1];
		NodesFrom = new ArrayList[100000 + 1];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < NodesTo.length; i++) {
			NodesTo[i] = new ArrayList<>();
			NodesFrom[i] = new ArrayList<>();
		}
		while(true) {
			String[] ch = new String[30];
			ch =br.readLine().split("  | ");
			if(ch[0].equals("-1")) break;
			int u = 0; int v = 0;
			if(ch[0].equals("")) {

				continue;
			}

			for(int i = 0; i < ch.length - 1; i += 2) {
				u = stoi(ch[i]);
				v = stoi(ch[i + 1]);
				int cnt = 0;
				if(u == 0 && v == 0) {
					int isRoot = 0; boolean isTree = true;
					for(int j = 1; j <= 100000; j++) {
						if(NodesFrom[j].size() > 0 || NodesTo[j].size() > 0) cnt++;
						if(NodesFrom[j].size() == 0 && NodesTo[j].size() >= 1) isRoot++;
						if(NodesFrom[j].size() > 1) isTree = false; 
					}
					if(isRoot != 1 && cnt != 0) isTree = false;
					if(!isTree) System.out.println("Case " + k + " is not a tree.");
					else         System.out.println("Case " + k + " is a tree.");
					k++;
					for(int j = 0; j < NodesTo.length; j++) {
						NodesTo[j] = new ArrayList<>();
						NodesFrom[j] = new ArrayList<>();
					}
					break;
				}
				NodesTo[u].add(v);
				NodesFrom[v].add(u);
			}
			
		}
		
	}
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
