from collections import deque


class Stack:
    def __init__(self, arr):
        self.arr = deque(arr)

    def push(self, value):
        self.arr.append(value)

    def pop(self):
        return self.arr.pop() if len(self.arr) > 0 else -1

    def size(self):
        return len(self.arr)

    def empty(self):
        return 1 if len(self.arr) == 0 else 0

    def top(self):
        return self.arr[-1] if len(self.arr) > 0 else -1


def solution():
    N = int(input())
    commands = [input().split() for _ in range(N)]
    stack = Stack([])

    for command in commands:
        if command[0] == 'push':
            stack.push(int(command[1]))
        elif command[0] == 'pop':
            print(stack.pop())
        elif command[0] == 'size':
            print(stack.size())
        elif command[0] == 'empty':
            print(stack.empty())
        elif command[0] == "top":
            print(stack.top())



solution()