import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static String N;
	static int K;
	static boolean visit[][];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = st.nextToken();
		K = Integer.parseInt(st.nextToken());
		
		int length = N.length();
		
		visit = new boolean[K + 1][1000000 + 1];
		for(int i = 0; i < K + 1; i++) {
			for(int j = 0; j < 1000000 + 1; j++) {
				visit[i][j] = false;
			}
		}
		
		Queue<String> q = new LinkedList<>();
		q.add(N);
		int cnt = 0; int Max = -1;
		while(!q.isEmpty() && cnt < K) {
			int size = q.size();
			
			for(int i = 0; i < size; i++) {
				String str = q.peek();
				String sTmp; int nTmp;
				q.poll();
				char[] ch = str.toCharArray();
				for(int j = 0; j < length - 1; j++) {
					for(int k = j + 1; k < length; k++ ){
						if(j == 0 && ch[k] == '0') continue;
						sTmp = swap(str, j, k);
						nTmp = Integer.parseInt(sTmp);
						
						if(!visit[cnt][nTmp]) {
							if(cnt == K - 1) {
								if(Max < nTmp) {
									Max = nTmp;
								}
							}
							visit[cnt][nTmp] = true;
							q.add(sTmp);
						}
					}
				}
			}
			cnt++;
			
		}
		
		System.out.println(Max);
	}

	private static String swap(String str, int i, int j) {
		char[] chars = str.toCharArray(); 
		char tmp = chars[i];
		chars[i] = chars[j];
		chars[j] = tmp;
		return new String(chars);
	}

}