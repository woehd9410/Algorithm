#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <map>
#include <vector>

using namespace std;

#define endl '\n'

int dice[10];
int ans;

map<int, int> red = {
	{0, 2}, {2, 4} , {4, 6}, {6, 8}, {8, 10},
	{10, 12}, {12, 14}, {14, 16}, {16, 18}, {18, 20},
	{20, 22}, {22, 24}, {24, 26}, {26, 28}, {28, 30},
	{30, 32}, {32, 34}, {34, 36}, {36, 38}, {38, 40}, {40, 50}
};

map<int, int> blue = {
	{10, 13}, {13, 16}, {16, 19}, {19, 25},
	{20, 22}, {22, 24}, {24, 25}, {30, 28},
	{28, 27}, {27, 26}, {26, 25}
};

map<int, int>blue_goal = {
	{25, 30}, {30, 35}, {35, 40}, {40, 50}
};

void Input() {
	for (int i = 0; i < 10; i++) {
		cin >> dice[i];
	}
}

int move(int idx, int now, vector<pair<int, int>>& locate) {
	int present = locate[idx].first;
	int dir = locate[idx].second;
	int cnt = dice[now];

	for (int i = 0; i < cnt; i++) {
		if (dir == 0) present = red[present];
		if (dir == 1) present = blue[present];
		if (dir == 2) present = blue_goal[present];

		if (present == 25) dir = 2;
		else if (present == 50) {
			locate[idx].first = present;
			return 0;
		}
	}

	if (dir == 0 && (present == 10 || present == 20 || present == 30)) {
		dir = 1;
	}
	bool ok = true;
	for (int i = 0; i < 4; i++) {
		
		if (idx == i) continue;
		if (present == locate[i].first &&
			(present == 40 || dir == locate[i].second)) ok = false;
	}

	if (ok) {
		locate[idx].first = present;
		locate[idx].second = dir;
		return present;
	}
	else {
		return -1;
	}
	
	return 0;
}

void DFS(int now, int sum, vector<pair<int, int>> &locate) {
	if (now == 10) {
		if (ans < sum) ans = sum;
		return;
	}

	for (int i = 0; i < 4; i++) {
		int prev_locate = locate[i].first;
		int prev_dir = locate[i].second;

		if (prev_locate == 50) continue;
		int ret = move(i, now, locate);
		if (ret > -1) {
			DFS(now + 1, sum + ret, locate);
		}

		locate[i].first = prev_locate;
		locate[i].second = prev_dir;
	}
}

int main() {
	freopen("Text.txt", "r", stdin);
	Input();

	vector<pair<int, int>> locate =
	{
		{0, 0},
		{0, 0},
		{0, 0},
		{0, 0}
	};

	DFS(0, 0, locate);

	cout << ans << endl;
}