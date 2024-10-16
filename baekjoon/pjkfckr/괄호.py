

def is_valid_parentheses(s):
    stack = []
    for char in s:
        if char == '(':
            stack.append(char)
        elif char == ')':
            if not stack:
                return False
            stack.pop()
    return len(stack) == 0


def solution():
    N = int(input())

    for _ in range(N):
        item = input().strip()

        if len(item) <= 1 or item[0] == ")" or item.count("(") != item.count(")"):
            print("NO")
        elif is_valid_parentheses(item):
            print("YES")
        else:
            print("NO")

solution()
