import java.util.*;
class Solution {
public static int solution(int[] stones, int k) {
        int answer = 0;
        int l = 0;
        int r = 200000000;
        int mid = 0;
        
        while(l <= r) {
        	mid = (l + r) / 2;
        	
        	if(isPossible(stones, k, mid)) {
        		l = mid + 1;
        		answer = Math.max(answer, mid);
        	}
        	else {
        		r = mid - 1;
        	}
        }
        
        return answer;
    }
	private static boolean isPossible(int[] stones, int k, int mid) {
		int cnt = 0;
		for(int stone : stones) {
			if(stone - mid < 0) {
				cnt++;
			}
			else {
				cnt = 0;
			}
			if(cnt == k) return false;
		}
		return true;
	}
}