import java.util.*;
import java.io.*;

public class Main {
	static class  pos{
		int x;
		int y;
		public pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String answer = solution(numbers, "right");
		System.out.println(answer);
	}
    public static String solution(int[] numbers, String hand) {
        String answer = "";
        String[][] keyPad = {
        		{"1", "2", "3"},
        		{"4", "5", "6"},
        		{"7", "8", "9"},
        		{"*", "0", "#"},
        };
        pos leftNow   = new pos(3, 0);
        pos rightNow  = new pos(3, 2);
        
        int leftDist  = 0;
        int rightDist = 0;
        
        HashMap<String, pos> map = new HashMap<>();
        for(int x = 0; x < 4; x++) {
        	for(int y = 0; y < 3; y++) {
        		map.put(keyPad[x][y], new pos(x,y));
        	}
        }
        
        for(int num : numbers) {
        	if(num == 1 || num == 4 || num == 7) {
        		answer += "L";
        		leftNow = map.get(Integer.toString(num));
        	}
        	else if(num == 3 || num == 6 || num == 9) {
        		answer += "R";
        		rightNow = map.get(Integer.toString(num));
        	}
        	else {
        		leftDist = Math.abs(leftNow.x - map.get(Integer.toString(num)).x) + Math.abs(leftNow.y - map.get(Integer.toString(num)).y);
        		rightDist = Math.abs(rightNow.x - map.get(Integer.toString(num)).x) + Math.abs(rightNow.y - map.get(Integer.toString(num)).y);
        		
        		if(leftDist > rightDist) {
        			answer += "R";
        			rightNow = map.get(Integer.toString(num));
        		}
        		else if(leftDist < rightDist) {
        			answer += "L";
        			leftNow = map.get(Integer.toString(num));
        		}
        		else if(leftDist == rightDist) {
        			if(hand.equals("right")) {
        				answer += "R";
        				rightNow = map.get(Integer.toString(num));
        			}
        			else {
        				answer += "L";
        				leftNow = map.get(Integer.toString(num));
        			}
        		}
        	}
        		
        }
        return answer;
    }

}
