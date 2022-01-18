class Solution {
    public String solution(int n) {
        String answer = "";
        
        int[] num = new int[3];
        num[1] = 1; num[2] = 2; num[0] = 4;
        
        String temp = "";
        while(n > 0){
            if(n <= 3){
            	if(n == 3) n = 0;
                temp += Integer.toString(num[n]);
                break;
            }
            int r = n % 3;
            n /= 3;
            temp += Integer.toString(num[r]);
            if(r == 0) n -= 1;
        }
        
        StringBuffer sb = new StringBuffer(temp);
        answer = sb.reverse().toString();
        return answer;
    }
}