#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <vector>
using namespace std;

#define endl '\n'

int N, K, ans;
bool flag;
char word[51][27];
bool chk[27];
bool visit[27];
bool v[26];
vector<int> possible;

void input() {
	cin >> N >> K;
	if (K >= 0 && K <= 4) {
		ans = 0;
		flag = true;
		return;
	}
	if (K == 26) {
		ans = N;
		flag = true;
		return;
	}
	for (int i = 0; i < N; i++) {
		cin >> word[i];
	}

	for (int i = 0; i < N; i++) {
		int j = 0;
		while (word[i][j] != NULL) {
			if (!chk[word[i][j] - 'a']) {
				chk[word[i][j] - 'a'] = true;
				if (word[i][j] != 'a' && word[i][j] != 'n' && word[i][j] != 'c' && word[i][j] != 'i' && word[i][j] != 't') {
					possible.push_back(word[i][j] - 'a');
				}
			}
			j++;
		}
	}
}


void DFS(int idk, int cnt, int limit) {
	if (cnt == limit) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			int j = 4; bool ok = true;

			while (word[i][j] != NULL) {
				if (visit[word[i][j] - 'a'] == false) {
					ok = false; break;
				}
				j++;
			}
			if (ok) sum++;
		}
		if (ans < sum) ans = sum;
		return;
	}

	for (int i = idk; i < possible.size(); i++) {
		if (v[i] == true) continue;
		v[i] = true;
		visit[possible[i]] = true;
		DFS(i, cnt + 1, limit);
		v[i] = false;
		visit[possible[i]] = false;
	}
}
int main() {
	freopen("Text.txt", "r", stdin);

	input();
	if (!flag) {
		visit[0] = true; visit[2] = true; visit[13] = true;
		visit[8] = true; visit[19] = true;
		int limit = K - 5;
		if (K - 5 > possible.size()) limit = possible.size();
		DFS(0, 0, limit);
	}
	cout << ans << endl;
}