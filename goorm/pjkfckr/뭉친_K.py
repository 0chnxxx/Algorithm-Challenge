"""
N * N 크기의 2차원 배열 M

M[i][j] 의 크기는 1
각 칸에는 0 ~ 9 사이의 숫자가 하나씩 있다.

배열 M에는 뭉친 그룹이 있다.
뭉친 그룹이란 상하좌우로 인접한 칸으로 연결되어 있으면서,
모든 칸들이 같은 값을 가지는 칸들의 집합을 의미한다.
뭉친 그룹의 크기는 그룹에 속한 칸의 개수와 같다.

M[x][y] 칸의 값이 K일때, 값이 K인 칸으로 이루어진 뭉친 그룹 중 가장 큰 뭉친 그룹의 크기를 출력]

입력
첫째 줄에 M 크기의 N
둘째줄에 x, y
다음 N 개의 줄에는 M의 상태가 주어진다. i 번째 줄에는 M[i][1]...M[i][N] 이 공백을 두고 주어진다.

"""
from collections import deque

N = int(input())
X, Y = map(int, input().split())
X, Y = X - 1, Y - 1  # 0-based 인덱싱으로 변환
M = [list(map(int, input().split())) for _ in range(N)]
K = M[X][Y]
visit = [[False] * N for _ in range(N)]
result = 0

def bfs(x, y):
    q = deque([(x, y)])
    visit[x][y] = True
    directions = [(0, 1), (1, 0), (-1, 0), (0, -1)]
    count = 0

    while q:
        x, y = q.popleft()
        count += 1
        for dx, dy in directions:
            nx, ny = x + dx, y + dy

            if 0 <= nx < N and 0 <= ny < N and not visit[nx][ny] and M[nx][ny] == K:
                visit[nx][ny] = True
                q.append((nx, ny))

    return count

for i in range(N):
    for j in range(N):
        if not visit[i][j] and M[i][j] == K: # 방문하지 않았고, M[i][j] 가 K값이랑 동일
            result = max(result, bfs(i, j))

print(result)