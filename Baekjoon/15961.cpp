#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
using namespace std;

#define MAX 3000001
#define endl '\n'

int N, d, k, c;
int arr[MAX];
int sushi[3001];
int maxi;
void Input() {
	cin >> N >> d >> k >> c;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
}

void Solve() {
	int cnt = 0;
	bool flag = false;
	sushi[c] = 1;
	cnt++;
	for (int i = 0; i < k; i++) {
		if (sushi[arr[i]] == 0) cnt++;
		sushi[arr[i]]++;
	}
	if (sushi[c] == 0) {
		cnt++;
		flag = true;
	}
	maxi = maxi > cnt ? maxi : cnt;
	
	cout << 1 << "번째" << maxi << endl;
	for (int i = 1; i < N; i++) {		
		int first = i - 1;
		int end = i + (k - 1);
		if (end >= N) end -= N;
		if (arr[first] == arr[end]) continue;
		sushi[arr[first]]--;
		if (sushi[arr[first]] == 0) cnt--;
		sushi[arr[end]]++;
		if (sushi[arr[end]] == 1) cnt++;
		
		maxi = maxi > cnt ? maxi : cnt;
		
		cout << i + 1 << "번째"<< maxi << endl;
	}
}

void Result() {
	cout << maxi << endl;
}
int main() {
	freopen("input.txt", "r", stdin);

	Input();

	Solve();

	Result();
}