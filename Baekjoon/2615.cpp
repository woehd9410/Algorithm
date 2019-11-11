#define _CRT_SECURE_NO_WARNING_
#include <iostream>

using namespace std;

int map[21][21];
bool visit[21][21][4];
int dx[] = { 0, 1, 1, 1 };
int dy[] = { 1, 1, 0, -1 };
int winner;
int ansx, ansy;
bool flag;
void Input() {
	for (int i = 1; i <= 19; i++) {
		for (int j = 1; j <= 19; j++) {
			cin >> map[i][j];
		}
	} 
}
bool Find(int x, int y, int dir, int cnt) {
	visit[x][y][dir] = true;
	int nx = x + dx[dir]; int ny = y + dy[dir];
	if (cnt == 5) {
		if (map[x][y] != map[nx][ny]) {
			if (dir == 3) {
				ansx = x;
				ansy = y;
			}
			flag = true;
		}
	}
	
	if (nx >= 1 && ny >= 1 && nx <= 19 && ny <= 19) {
		if (!visit[nx][ny][dir]) {
			if (map[x][y] == map[nx][ny]) {
				Find(nx, ny, dir, cnt + 1);
			}
		}
	}
	return flag;
}

void Solve() {
	for (int i = 1; i <= 19; i++) {
		for (int j = 1; j <= 19; j++) {
			if (map[i][j] == 0) continue;
			for (int k = 0; k < 4; k++) {
				flag = false;
				if (Find(i, j, k, 1)) {
					winner = map[i][j];
					if (k != 3) {
						ansx = i;
						ansy = j;
					}
				}
			}
		}
	}
	
}
void Result() {
	cout << winner << '\n';
	if(winner != 0) cout << ansx << " " << ansy << '\n';
}
int main() {
	freopen("input.txt", "r", stdin);

	Input();
	
	Solve();

	Result();
}