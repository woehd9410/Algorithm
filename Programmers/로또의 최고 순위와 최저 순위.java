class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        //최고점 찾기
        int max = 0;
        int min = 0;
        boolean isValid;
        for (int lotto : lottos){
            if(lotto == 0) max++;
            else{
                isValid = false;
                for (int win_num : win_nums){
                    if(lotto == win_num) isValid = true;
                }
                if (isValid) {
                    max++;
                    min++;
                }
            }
        }
        if (min == 0) min++;
        if (max == 0) max++;
        answer[0] = 7 - max;
        answer[1] = 7 - min;
        return answer;
    }
}