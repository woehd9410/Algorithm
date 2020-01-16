#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <queue>
using namespace std;

struct mem {
	int counter;
	int fix;
	int tk;
};

int T;
int N, M, K, A, B;
int arr[9]; int brr[9];
bool achk[9]; bool bchk[9];
mem custom[1001];

int main() {
	freopen("input.txt", "r", stdin);
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> N >> M >> K >> A >> B;
		for (int i = 0; i < N; i++) {
			cin >> arr[i];
		}
		for (int i = 0; i < M; i++) {
			cin >> brr[i];
		}
		for (int i = 0; i < K; i++) {
			cin >> custom[i].tk;
		}
		int time = 0;
		while (time++) {
			if (time != 0) {
				bool flag = true;
				for (int i = 0; i < N; i++) {
					if (!achk[i]) flag = false;
				}
				for (int i = 0; i < M; i++) {
					if (!bchk[i]) flag = false;
				}
				if (!flag) break;
			}
		}
	}
}