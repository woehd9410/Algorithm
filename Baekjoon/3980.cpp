#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
using namespace std;

#define endl '\n'

int T;
int map[12][12];
bool visit[12];
int ans;

void init() {
	memset(visit, false, sizeof(visit));
	ans = 0;
}

void input() {
	for (int i = 1; i <= 11; i++) {
		for (int j = 1; j <= 11; j++) {
			cin >> map[i][j];
		}
	}
}

void DFS(int next, int sum) {
	if (next == 12) {
		if (ans < sum) ans = sum;
		return;
	}
	for (int i = 1; i <= 11; i++) {
		if (map[next][i] == 0 || visit[i]) continue;
		visit[i] = true;
		DFS(next + 1, sum + map[next][i]);
		visit[i] = false;
	}
}

void solve() {
	DFS(1, 0);
}

int main() {
	freopen("input.txt", "r", stdin);
	cin >> T;
	for (int i = 0; i < T; i++) {
		init();
		input();

		solve();
		cout << ans << endl;
	}
}