import java.io.IOException;
//콜로세움

public class Solution {

    public static void main(String[] args) throws IOException {
        String[][] places = {{"POOOP",
                "OXXOX",
                "OPXPX",
                "OOXOX",
                "POXXP"},
                {"POOPX",
                        "OXPXP",
                        "PXXXO",
                        "OXXXO",
                        "OOOPP"},
                {"PXOPX",
                        "OXOXP",
                        "OXPOX",
                        "OXXOP",
                        "PXPOX"},
                {"OOOXX",
                        "XOOOX",
                        "OOOXX",
                        "OXOOX",
                        "OOOOO"},
                {"PXPXP",
                        "XPXPX",
                        "PXPXP",
                        "XPXPX",
                        "PXPXP"}};
        int[] answer = solution(places);
        for(int i = 0; i < 5; i++) {
            System.out.println(answer[i]);
        }
    }
    public static int[] solution(String[][] places) {
        int[] answer = new int[5];

        System.out.println(places[0][0].charAt(0));
        // BFS
        char arr[][] = new char[5][5];

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                arr[j] = places[i][j].toCharArray();
            }
            boolean flag = false;
            for(int x = 0; x < 5; x++) {
                for(int y = 0; y < 5; y++){
                    if(arr[x][y] == 'P') {
                        if(isValidPerson(x, y, arr)) {
                            answer[i] = 0;
                            flag = true;
                        }
                    }
                }
                if(flag) break;
            }
            if(!flag) answer[i] = 1;
        }
        return answer;
    }
    private static boolean isValidPerson(int x, int y, char[][] arr) {
        boolean isValid = false;
        if(chkDistOne(x, y, arr)) isValid = true;

        if(!isValid) {
            if(chkDistTwo(x,y,arr)) isValid = true;
        }

        return isValid;
    }

    private static boolean chkDistOne(int x, int y, char[][] arr) {
        boolean isValid = false;

        int dx[] = { -1, -1, -1, 0, 1, 1, 1, 0};
        int dy[] = { -1, 0, 1, 1, 1, 0, -1, -1};

        for(int i = 0; i < 8; i++) {
            int nx = x + dx[i]; int ny = y + dy[i];

            if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5) {
                if(isValid) return isValid;
                if(arr[nx][ny] == 'P') {
                    if(i == 0) {
                        if(arr[nx + 1][ny] != 'X' || arr[nx][ny + 1] != 'X') isValid = true;
                    }else if(i == 2) {
                        if(arr[nx][ny - 1] != 'X' || arr[nx + 1][ny] != 'X') isValid = true;
                    }
                    else if(i == 4) {
                        if(arr[nx - 1][ny] != 'X' || arr[nx][ny - 1] != 'X') isValid = true;
                    }
                    else if(i == 6) {
                        if(arr[nx][ny + 1] != 'X' || arr[nx - 1][ny] != 'X') isValid = true;
                    }
                    else{
                        isValid = true;
                    }
                }
            }
        }
        return isValid;
    }

    private static boolean chkDistTwo(int x, int y, char[][] arr) {
        boolean isValid = false;

        int dx[] = {-2, 0, 2, 0};
        int dy[] = {0, 2, 0, -2};

        int ax[] = {-1, 0, 1, 0};
        int ay[] = {0, 1, 0, -1};
        for(int i = 0; i < 4; i++) {
            int nnx = x + dx[i]; int nny = y + dy[i];
            int nx = x + ax[i]; int ny = y + ay[i];
            if(nnx >= 0 && nny >= 0 && nnx < 5 && nny < 5) {
                if(arr[nnx][nny] == 'P' && arr[nx][ny] != 'X') {
                    isValid = true;
                    return isValid;
                }
            }
        }
        return isValid;
    }

}