import java.util.*;
import java.io.*;

public class Solution {
	static class TrieNode{
		char data;
		HashMap<Character,TrieNode> child = new HashMap<>();
		HashMap<Integer,Integer> lenWord = new HashMap<>();
		
		public TrieNode(char data) {
			data = this.data;
		}
	}
	static class Trie{
		char data;
		TrieNode root;
		
		public Trie() {
			root = new TrieNode(data);
		}
		
		public void insert(String keyword) {
			TrieNode node = this.root;
			int size = keyword.length();
			for(int i = 0; i < size; i++) {
				char ch = keyword.charAt(i);
				
				if(!node.child.containsKey(ch)) {
					node.child.put(ch, new TrieNode(ch));
				}
				
				if(!node.lenWord.containsKey(size)) {
					node.lenWord.put(size, 1);
				}
				else {
					int len = node.lenWord.get(size);
					node.lenWord.replace(size, len + 1);
				}
				
				node = node.child.get(ch);
			}
		}
		
		public int search(String keyword) {
			TrieNode node = root;
			int size = keyword.length();
			for(int i = 0; i < size; i++) {
				char ch = keyword.charAt(i);
				if(ch == '?') {
					break;
				}else if(node.child.containsKey(ch)) {
					node = node.child.get(ch);
				}else{
					return 0;
				}
			}
			if(node.lenWord.containsKey(size)) {
				return node.lenWord.get(size);
			}
			return 0;
		}
	}
	public static void main(String[] args) {
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
	
		int[] answer = solution(words, queries);
		for(int i = 0; i < queries.length; i++) {
			System.out.println(answer[i]);
		}
	}
	public static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Trie frontTrie = new Trie();
        Trie backTrie = new Trie();
        
        for(String word: words) {
        	frontTrie.insert(word);
        	backTrie.insert(new StringBuffer(word).reverse().toString());
        }
        
        for(int i = 0; i < queries.length; i++) {
        	if(queries[i].charAt(0) == '?') {
        		answer[i] = backTrie.search(new StringBuffer(queries[i]).reverse().toString());
        	}
        	else answer[i] = frontTrie.search(queries[i]);
        }
        return answer;
    }
}