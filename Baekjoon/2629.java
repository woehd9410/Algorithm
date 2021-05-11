import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] sinkers;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sinkers = new int[N];
        String[] input = br.readLine().split(" ");
        for(int i=0; i<N; i++)
            sinkers[i] = Integer.parseInt(input[i]);

        int M = Integer.parseInt(br.readLine());
        int[] beads = new int[M];
        input = br.readLine().split(" ");
        for(int i=0; i<M; i++)
            beads[i] = Integer.parseInt(input[i]);

        dp = new boolean[N+1][60001];

        dfs(0, 0);

        for(int i=0; i<M; i++) {
            if(dp[N][beads[i]]) {
                System.out.print("Y ");
                continue;
            }

            System.out.print("N ");
        }
    }

    public static void dfs(int cnt, int weight) {
        if(dp[cnt][weight]) return;

        dp[cnt][weight] = true;

        if(cnt==N) return;

        dfs(cnt+1, weight+sinkers[cnt]);              
        dfs(cnt+1, weight);                           
        dfs(cnt+1, Math.abs(weight-sinkers[cnt]));    
    }
}