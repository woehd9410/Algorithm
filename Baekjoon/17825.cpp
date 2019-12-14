/*
	2019-12-14 Saturday
	BaekJoon ªÔº∫ SW ±‚√‚πÆ¡¶
	¡÷ªÁ¿ß ¿∑≥Ó¿Ã
*/
#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#define endl '\n'

using namespace std;


int answer;
pair<int, int> horse[4];
bool goal[4];
int arr[11];
bool visit[42];
int line1[] = { 0, 13, 16, 19, 25, 30, 35, 40, 42};
int line2[] = { 0, 22, 24, 25, 30, 35, 40, 42};
int line3[] = { 0, 28, 27, 26, 25, 30, 35, 40, 42};



void Input() {
	for (int i = 0; i < 10; i++) {
		cin >> arr[i];
	}
	for (int i = 0; i < 4; i++) {
		horse[i].first = 0;
		horse[i].second = 0;
	}
}


void DFS(int horseNum, int now, int sum) {
	if (now == 10) {
		if (answer < sum) {
			answer = sum;
			cout << answer << endl;
		}
		return;
	}
	int line = horse[horseNum].second;
	if (horse[horseNum].second == 0) {
		if ((horse[horseNum].first * 2) % 10 == 0) {
			horse[horseNum].second = horse[horseNum].first * 2 / 10;
			horse[horseNum].first = 0;
		}
	}
	int cnt = arr[now]; int start = horse[horseNum].first;
	int plus = 0;
	int move = 0;
	if (horse[horseNum].second == 0) {
		if (start * 2 == 42) return;
		while (cnt--) {
			move++;
			if ((start + move) * 2 == 42) {
				visit[start * 2] = false;
				horse[horseNum].first = start + move;
				return;
			}
			if (visit[(start + move) * 2]) {
				move = 0;
				return;
			}
		}
		plus = (start + move) * 2;
		sum += plus;
		visit[horse[horseNum].first * 2] = false;
		horse[horseNum].first = start + move;
		visit[(start + move) * 2] = true;
	}
	else if (horse[horseNum].second == 1) {
		if (line1[start] == 42) return;
		while (cnt--) {
			move++;
			if (line1[start + move] == 42) {
				visit[line1[horse[horseNum].first]] = false;
				horse[horseNum].first = start + move;
				return;
			}
			if (visit[line1[start + move]]) {
				return;
			}
		}
		plus = line1[start + move];
		sum += plus;
		visit[line1[horse[horseNum].first]] = false;
		horse[horseNum].first = start + move;
		visit[start + move] = true;
	}
	else if (horse[horseNum].second == 2) {
		if (line2[start] == 42) return;
		while (cnt--) {
			move++;
			if (line2[start + move] == 42) {
				visit[line2[horse[horseNum].first]] = false;
				horse[horseNum].first = start + move;
				return;
			}
			if (visit[line2[start + move]]) {
				return;
			}
		}
		plus = line2[start + move];
		sum += plus;
		visit[line2[horse[horseNum].first]] = false;
		horse[horseNum].first = start + move;
		visit[start + move] = true;
	}
	else if (horse[horseNum].second == 3) {
		if (line3[start] == 42) return;
		while (cnt--) {
			move++;
			if (line3[start + move] == 42) {
				visit[line3[horse[horseNum].first]] = false;
				horse[horseNum].first = start + move;
				return;
			}
			if (visit[line3[start + move]]) {
				return;
			}
		}
		plus = line3[start + move];
		sum += plus;
		visit[line3[horse[horseNum].first]] = false;
		horse[horseNum].first = start + move;
		visit[start + move] = true;
	}
	cout << sum << endl;

	for (int i = 0; i < 4; i++) {
		
		
		DFS(i, now + 1, sum);
		sum -= plus;    
		if (horse[horseNum].second == 0) {
			visit[(start + move) * 2] = false;
		}
		visit[start + move] = false;
		horse[horseNum].first -= move;
		horse[horseNum].second = line;
	}
	
}

void Solve() {
	answer += arr[0] * 2;
	int sum = answer;
	horse[0].first = arr[0];
	visit[2 * arr[0]] = false;
	int line = 0;
	DFS(0, 1 , sum);
}

int main() {
	freopen("Text.txt", "r", stdin);
	Input();

	Solve();
	cout << "¡§¥‰ : ";
	cout << answer << endl;
}