import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> MaxHeap = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> MinHeap = new PriorityQueue<>();
		
		int FirstValue = Integer.parseInt(br.readLine());
		MaxHeap.add(FirstValue);
		System.out.println(FirstValue);
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < N; i++) {
			int now = Integer.parseInt(br.readLine());
			if(MaxHeap.peek() > now) {
				MaxHeap.add(now);
				if(MaxHeap.size() > MinHeap.size()) {
					MinHeap.add(MaxHeap.peek());
					MaxHeap.poll();
				}
			}else {
				MinHeap.add(now);
				if(MinHeap.size() > MaxHeap.size()) {
					MaxHeap.add(MinHeap.peek());
					MinHeap.poll();
				}
			}
			
			if(MaxHeap.size() >= MinHeap.size()) {
				sb.append(MaxHeap.peek() + "\n");
			}else {
				sb.append(MinHeap.peek() + "\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}