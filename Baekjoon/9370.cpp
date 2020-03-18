/*
	Baekjoon (9370 - 미확인 도착지)
*/

#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <memory.h>

using namespace std;

int T;
int n, m, t; // 교차로, 도로, 목적지 후보
int s, g, h; // 출발지, 반드시 지나는 교차로
int a, b, d; //
int dist[2001];

vector <vector<pair<int, int>>> map;
vector <vector<int>> path;
vector <int> answer;
bool flag(int now, int next) {
	if ((now == g && next == h) || (now == h && next == g)) {
		return true;
	}
	for (int i = 0; i < path[now].size(); i++) {
		if (flag(path[now][i], now)) return true;
	}
	return false;
}

void dijkstra(int goal) {
	memset(dist, -1, sizeof(dist));
	priority_queue <pair<pair<int, int>, int>> pq;
	pq.push({ {0,s},0 });
	path.clear();
	path.resize(n + 1);
	while (!pq.empty()) {
		int cost = -pq.top().first.first;
		int pos = pq.top().first.second;
		int pre = pq.top().second;
		pq.pop();
		if (dist[pos] == cost) path[pos].push_back(pre);
		if (dist[pos] != -1) continue;
		dist[pos] = cost;
		path[pos].push_back(pre);
		for (int k = 0; k < map[pos].size(); k++) {
			int next = map[pos][k].first;
			int ncost = -cost - map[pos][k].second;
			if (dist[next] == -1) pq.push({ {ncost, next},pos });
		}
	}
	if (dist[goal] != -1) {
		for (int i = 0; i < path[goal].size(); i++) {
			if (flag(path[goal][i], goal)) answer.push_back(goal);
		}
	}
}

void input() {
	map.clear();
	cin >> n >> m >> t;
	map.resize(n + 1);
	cin >> s >> g >> h;

	for (int i = 1; i <= m; i++) {
		cin >> a >> b >> d;
		map[a].push_back({ b, d });
		map[b].push_back({ a, d });
	}
	answer.clear();
	for (int i = 0; i < t; i++) {
		int tmp;
		cin >> tmp;
		dijkstra(tmp);
	}
}

int main() {
	freopen("input.txt", "r", stdin);

	cin >> T;
	for (int test_case = 1; test_case <= T; test_case++) {
		input();
		sort(answer.begin(), answer.end());
		for (int i = 0; i < answer.size(); i++) {
			cout << answer[i] << " ";
		}
		cout << '\n';
	}
}