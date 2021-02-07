import java.io.*;
import java.util.*;

public class Main {	
	
	public static void main(String[] args) throws Exception {
		String[] cities = {
				"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"
		};
		
		System.out.println(solution(0,cities));
	}
    public static int solution(int cacheSize, String[] cities) {
        int answer = 0; 
        List<String> cache = new LinkedList<>();
        if (cacheSize == 0) {
            answer = cities.length * 5;
            return answer;
        }
        for(String city : cities) {
        	String value = city.toLowerCase();
        	if(cache.contains(value)) {
        		cache.remove(value);
        		cache.add(value);
        		answer += 1;
        	}
        	else {
        		if(cache.size() == cacheSize) cache.remove(0);
        		cache.add(value);
        		answer += 5;
        	}
        	
        	
    	
        }
        return answer;
    }

}

