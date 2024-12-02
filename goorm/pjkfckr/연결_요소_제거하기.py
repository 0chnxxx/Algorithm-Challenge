
"""

N * N 크기의 2차원 배열이 있다.
2차원 배열의 i행 j열에 해당하는 칸은 (i,j)로 나타낸다.
처음에 이 배열의 각 칸에는 알파벳 대분자 또는 . 문자가 하나 적혀있다.

상하좌우로 인접한 두 칸에 같은 문자가 적혀있는 경우, 두 칸은 연결되어 있다고 한다.
서로 연결된 칸들의 집합을 연결 요소라고 하고, 연결 요소의 크기는 그 연결 요소에 포함된 칸들의 개수와 같다.

아래 작업을 Q번 수행
1. (y[i], x[i]) 칸을 고른 뒤, 그 칸에 알파벳 대문자 d[i]를 쓴다. 구름이가 고른 칸은 . 문자가 적힌 칸임을 보장
2. 배열에 존재하는 모든 연결 요소의 크기를 계산한다.
    만약 크기가 K 이상인 연결 요소가 존재한다면, 그 연결 요소에 포함된 모든 칸에 적힌 문자를 지운다.

모든 작업을 수행한 뒤에, 배열에 적혀있는 문자를 구해야한다.


"""
from collections import deque

N, K, Q = map(int, input().split())
N_graph = [list(input().strip()) for _ in range(N)]


def bfs(graph, start, K):
    char = graph[start[0]][start[1]]
    queue = deque([start])
    visited = {start}
    component = [start]

    while queue:
        y, x = queue.popleft()
        for dy, dx in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
            ny, nx = y + dy, x + dx
            if 0 <= ny < N and 0 <= nx < N and (ny, nx) not in visited and graph[ny][nx] == char:
                queue.append((ny, nx))
                visited.add((ny, nx))
                component.append((ny, nx))

    if len(component) >= K:
        for y, x in component:
            graph[y][x] = '.'
        return component
    return []


for _ in range(Q):
    y, x, d = input().split()
    y, x = int(y) - 1, int(x) - 1
    N_graph[y][x] = d

    to_check = {(y, x)}
    while to_check:
        start = to_check.pop()
        if N_graph[start[0]][start[1]] != '.':
            deleted = bfs(N_graph, start, K)
            to_check.update([(y + dy, x + dx) for y, x in deleted for dy, dx in [(1, 0), (-1, 0), (0, 1), (0, -1)]
                             if 0 <= y + dy < N and 0 <= x + dx < N])

for graph in N_graph:
    print("".join(graph))