import java.util.*;
import java.io.*;



public class Main {
	static class info implements Comparable<info>{
		int stage;
		double failRate;
		info(int stage, double failRate) {
			this.stage = stage;
			this.failRate = failRate;
		}
		
		@Override
		public int compareTo(info o) {
			if(this.failRate > o.failRate) {
				return -1;
			}
			if(this.failRate == o.failRate){
				if(this.stage < o.stage) {
					return -1;
				}
				else {
					return 1;
				}
			}
			if (this.failRate < o.failRate){
				return 1;
			}
			return 0;
		}
		
	}
	static int[] peopleOfStage;
	static ArrayList<info> failRateList;
	public static void main(String[] args) throws IOException {
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		int[] answers = solution(5, stages);
		for(int ans : answers) {
			System.out.println(ans);
		}
	}
    public static int[] solution(int N, int[] stages) {
        int[] answer = {};
        peopleOfStage = new int[N + 1];
        for(int stage : stages) {
        	if(stage > N) continue;
        	peopleOfStage[stage] += 1;
        }
        failRateList = new ArrayList<>();
        int peopleNum = stages.length;
        for(int idx = 1; idx <= N; idx++) {
            if(peopleNum == 0 || peopleOfStage[idx] == 0) {
        		failRateList.add(new info(idx, 0));
                continue;
        	}
        	failRateList.add(new info(idx, (double) peopleOfStage[idx] / peopleNum));
        	peopleNum -= peopleOfStage[idx];
        }
        Collections.sort(failRateList);
        answer = new int[N];
        for(int idx = 0; idx < N; idx++) {
        	answer[idx] = failRateList.get(idx).stage;
        }
        return answer;
    }
}