from collections import deque

class Queue:
    def __init__(self):
        self.queue = deque()

    def push(self, value):
        self.queue.append(value)

    def pop(self):
        return self.queue.popleft() if self.queue else -1

    def size(self):
        return len(self.queue)

    def empty(self):
        return 1 if len(self.queue) == 0 else 0

    def front(self):
        return self.queue[0] if self.queue else -1

    def back(self):
        return self.queue[-1] if self.queue else -1


def solution():
    N = int(input())
    commands = [input().split() for _ in range(N)]
    queue = Queue()

    for command in commands:
        if command[0] == "push":
            queue.push(int(command[1]))
        elif command[0] == "pop":
            print(queue.pop())
        elif command[0] == "size":
            print(queue.size())
        elif command[0] == "empty":
            print(queue.empty())
        elif command[0] == "front":
            print(queue.front())
        elif command[0] == "back":
            print(queue.back())

solution()