from collections import deque

def bfs(x, y, n):
    queue = deque([(0, 0, 0)])
    direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]

    while queue:
        cx, cy, time = queue.popleft()

        if cx == x and cy == y and time == n:
            return "YES"

        if time >= n:
            continue

        for dx, dy in direction:
            nx, ny = cx + dx, cy + dy
            queue.append((nx, ny, time + 1))

    return "NO"


def bfs_solution():
    N = int(input())

    for _ in range(N):
        x, y, n = map(int, input().split())

        print(bfs(x, y, n), end="\n")




"""
abs(x) + abs(y)로 맨해튼 거리를 계산
거리가 주어진 시간 n보다 크면 도달할 수 없으므로 "NO"를 반환
거리가 정확히 n과 같으면 정확히 도달 가능하므로 "YES"를 반환
거리가 n보다 작은 경우, 남은 시간 동안 제자리에서 왔다갔다 할 수 있는지 확인
남은 시간(n - distance)이 짝수이면 제자리로 돌아올 수 있으므로 "YES"
홀수이면 제자리로 돌아올 수 없으므로 "NO"
"""
def can_search(x, y, n):
    distance = abs(x) + abs(y) # 맨허튼 거리 계산

    if distance > n:
        return "NO"
    elif distance == n:
        return "YES"
    else:
        # 남은 시간이 짝수인 경우에만 제자리로 돌아올 수 있음
        return "YES" if (n - distance) % 2 == 0 else "NO"

def solution_2():
    N = int(input())
    results = []

    for _ in range(N):
        x, y, n = map(int, input().split())
        results.append(can_search(x, y, n))

    print(results)