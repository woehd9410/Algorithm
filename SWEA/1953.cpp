#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <queue>
using namespace std;

#define MAX 51
#define endl '\n'

int T;
int N, M, HoleX, HoleY, L;
int map[MAX][MAX];
bool visit[MAX][MAX];

int ans;
int dx[] = { 0, 1, 0, -1 };
int dy[] = { 1, 0, -1, 0 };
void Input() {
	ans = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			visit[i][j] = false;
			cin >> map[i][j];
		}
	}
}

bool match(int x, int y, int nx, int ny, int dir) {
	if (dir == 0) {
		if (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 5) {
			if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
				return true;
			}
		}
	}
	else if (dir == 1) {
		if (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 5 || map[x][y] == 6) {
			if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
				return true;
			}
		}
	}
	else if (dir == 2) {
		if (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 6 || map[x][y] == 7) {
			if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
				return true;
			}
		}
	}
	else if (dir == 3) {
		if (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 4 || map[x][y] == 7) {
			if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
				return true;
			}
		}
	}
	return false;
}

void Solve() {
	int cnt = 1;
	queue<pair<int, int>> q;
	q.push({ HoleX,HoleY });

	while (L >= cnt) {
		int qSize = q.size();
		while (qSize > 0) {
			int x = q.front().first; int y = q.front().second;
			visit[x][y] = true;
			q.pop();

			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k]; int ny = y + dy[k];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (map[nx][ny] == 0 || visit[nx][ny]) continue;
				if (match(x, y, nx, ny, k)) {
					q.push({ nx,ny });
				}
			}
			qSize--;
		}
		cnt++;
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (visit[i][j]) ans++;
		}
	}
}

void Init() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			visit[i][j] = false;
		}
	}
	ans = 0;
}

int main() {
	freopen("input.txt", "r", stdin);
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> N >> M >> HoleX >> HoleY >> L;

		Input();

		Solve();

		cout << "#" << tc << " "<<ans << endl;
	}
}