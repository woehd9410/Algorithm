#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <string>
using namespace std;

typedef struct Node {
    char ch;
    Node* prev;
    Node* next;

    Node(char ch) {
        this->ch = ch;
        prev = this;
        next = this;
    }
    ~Node() {
        delete this;
    }
}node;

typedef struct Cursor {
    Node* prev;
    Node* next;

    Cursor() {
        prev = nullptr;
        next = nullptr;
    }
    ~Cursor() {
        delete this;
    }
}cursor;

typedef struct List {
    node* head;
    node* tail;
    cursor* curs;
    int size;
    List() {
        head = nullptr;
        tail = nullptr;
        curs = new Cursor();
        size = 0;
    }

    void add(char ch) {
        node* cur = new node(ch);
        if (size == 0) {
            head = cur;
            head->prev = NULL;
            tail = head;
            curs->prev = tail;
          
        }
        else {
            tail->next = cur;
            cur->prev = tail;
            cur->next = NULL;
            tail = cur;
            curs->prev = tail;
        }
        size++;
    }

    void cursor_left() {
        if (curs->next == head) return;
        curs->next = curs->prev;
        curs->prev = curs->prev->prev;
    }
    void cursor_right() {       
        if (curs->prev == tail) return;
        curs->prev = curs->next;
        curs->next = curs->next->next;
    }
    void cursor_delete() {
        if (size == 0) return;
        if (curs->next == head) return;
        if (curs->prev == tail) {
            curs->prev = tail->prev;
            curs->prev->next = nullptr;
            tail = tail->prev;
            curs->prev = tail;
            curs->next = nullptr;
        }
        else if (curs->prev == head) {
            curs->next->prev = nullptr;
            head = head->next;
            curs->prev = nullptr;
        }
        else {
            curs->prev->prev->next = curs->next;
            curs->next->prev = curs->prev->prev;
            curs->prev = curs->prev->prev;
        }
        size--;
    }

    void push(char data) {
        node* cur = new node(data);
        if(curs->prev != nullptr) curs->prev->next = cur;
        if(curs->next != nullptr) curs->next->prev = cur;
        cur->next = curs->next;
        cur->prev = curs->prev;
        curs->prev = cur;
        if (cur->next == nullptr) tail = cur;
        if (cur->prev == nullptr) head = cur;
        size++;
    }

   
}list;
void input() {
    string tmp;
    cin >> tmp;

    list* str = new list();
    for (int i = 0; i < tmp.length(); i++) {
        str->add(tmp[i]);
    }
    int order;
    cin >> order;

    for (int i = 0; i < order; i++) {
        char ch;
        cin >> ch;
        if (ch == 'P') {
            char ch2;
            cin >> ch2;
            str->push(ch2);
        }
        else if (ch == 'L') {
            str->cursor_left();
        }
        else if (ch == 'D') {
            str->cursor_right();
        }
        else if (ch == 'B') {
            str->cursor_delete();
        }
    }
    node* now = str->head;

    for (int i = 0; i < str->size; i++) {
        cout << now->ch;
        now = now->next;
    }

}

void solve() {

}
int main() {
    freopen("Text.txt", "r", stdin);
    input();
    solve();
}