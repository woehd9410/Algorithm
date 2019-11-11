#include <iostream>
#include <queue>

using namespace std;
char map[51][51];
int d[51][51];
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };
int R, C;
int fx, fy, sx, sy, hx, hy;
bool flag; int ans;
vector<pair<int, int>> v;
void Input() {
	cin >> R >> C;
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			cin >> map[i][j];
			d[i][j] = 99999999;
			if (map[i][j] == '*') {
				v.push_back({ i,j });
			}
			else if (map[i][j] == 'S') {
				sx = i; sy = j;
			}
			else if (map[i][j] == 'D') {
				hx = i; hy = j;
			}
		}
	}
}

void fire(int i, int j) {
	queue<pair<int, int>> q;
	q.push({ i,j });
	d[i][j] = 0;
	while (!q.empty()) {
		int x = q.front().first; int y = q.front().second;
		q.pop();
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k]; int ny = y + dy[k];
			if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
			if (d[x][y] + 1 >= d[nx][ny]) continue;
			if (map[nx][ny] == 'X' || map[nx][ny] == '*' || map[nx][ny] == 'D') continue;
			q.push({ nx,ny });
			d[nx][ny] = d[x][y] + 1;
		}
	}
}
void BFS(int i, int j) {
	queue<pair<int, int>> q;
	q.push({ i,j });
	d[i][j] = 0;
	while (!q.empty()) {
		int x = q.front().first; int y = q.front().second;
		q.pop();
		if ((x == hx && y == hy)) {
			ans = d[x][y];
			flag = true;
			return;
		}
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k]; int ny = y + dy[k];
			if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
			if (d[x][y] + 1 >= d[nx][ny]) continue;
			if (map[nx][ny] == 'X' || map[nx][ny] == '*') continue;
			q.push({ nx,ny });
			d[nx][ny] = d[x][y] + 1;
		}
	}

}
void solve() {
	flag = false;
	for (int i = 0; i < v.size(); i++) {
		fire(v[i].first, v[i].second);
	}
	BFS(sx, sy);
}
int main() {
	Input();
	solve();

	if (flag) cout << ans << '\n';
	else cout << "impossible" << '\n';
}