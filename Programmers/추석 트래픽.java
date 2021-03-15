class Solution {

    public static int solution(String[] lines) {
        int answer = 0;
        int startTimes[] = new int[lines.length];
        int endTimes[] = new int[lines.length];
        
        getTimes(lines, startTimes, endTimes);
        int count = 0;
        for(int i = 0; i < lines.length; i++) {
        	int startWindow = endTimes[i];
        	int endWindow   = endTimes[i] + 999;  
        	count = 0;
        	for(int j = 0; j < lines.length; j++) {
        		if(startTimes[j] < startWindow && endTimes[j] >= startWindow) count++;
        		else if(startTimes[j] <= endWindow && endTimes[j] > endWindow) count++;
        		else if(startTimes[j] <= startWindow && endTimes[j] >= endWindow) count++;
       		else if(startTimes[j] >= startWindow && endTimes[j] <= endWindow) count++;
        	}
        	answer = count > answer ? count : answer;
        }
        return answer;
    }
	private static void getTimes(String[] lines, int[] startTimes, int[] endTimes) {
		int idx = 0; int endTime = 0;
		for(String line : lines) {
			String[] log    = line.split(" ");
			String[] times  = log[1].split(":"); 
			int processTime = (int) (Double.parseDouble(log[2].substring(0,log[2].length() - 1)) * 1000); 
			
			endTime = 0;
			endTime =  Integer.parseInt(times[0]) * 60 * 60 * 1000;
			endTime += Integer.parseInt(times[1]) * 60 * 1000;
			endTime += (int) (Double.parseDouble(times[2]) * 1000);
			
			startTimes[idx] = endTime - processTime + 1;
			endTimes[idx] = endTime;
			idx++;
		}
	}
}