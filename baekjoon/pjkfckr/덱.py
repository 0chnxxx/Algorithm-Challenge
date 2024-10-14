from collections import deque


class Deck(object):
    def __init__(self):
        self.cards = deque()

    def push_front(self, value):
        self.cards.append(value)

    def push_back(self, value):
        self.cards.appendleft(value)

    def pop_front(self):
        return self.cards.pop() if self.cards else -1

    def pop_back(self):
        return self.cards.popleft() if self.cards else -1

    def size(self):
        return len(self.cards)

    def empty(self):
        return 0 if self.size() > 0 else 1

    def front(self):
        return self.cards[-1] if self.cards else -1

    def back(self):
        return self.cards[0] if self.cards else -1

def solution():
    N = int(input())
    commands = [input().split() for _ in range(N)]
    deck = Deck()

    for command in commands:
        if command[0] == "push_front":
            deck.push_front(int(command[1]))

        elif command[0] == "push_back":
            deck.push_back(int(command[1]))

        elif command[0] == "pop_front":
            print(deck.pop_front())

        elif command[0] == "pop_back":
            print(deck.pop_back())
        elif command[0] == "size":
            print(deck.size())
        elif command[0] == "empty":
            print(deck.empty())
        elif command[0] == "front":
            print(deck.front())
        elif command[0] == "back":
            print(deck.back())

solution()
