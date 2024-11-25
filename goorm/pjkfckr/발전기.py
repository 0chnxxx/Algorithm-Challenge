
"""
한 변의 길이가 N인 정사각형 모양의 마을 M을 만들고있다.

r번째 행, c번째 열에 해당하는 칸의 숫자 M[r][c] 가 적혀있다.
M[r][c]는 0 또는 1 중 하나이며, 각 숫자가 의미하는 바는 아래와 같다.
- 0 이면 아무것도 없는 칸이다.
- 1이면 집이 있는 칸이다.

마을에 있는 집에 전력을 공급하기 위해선 그 집에 발전기를 설치하거나, 상하좌우로 인접한 집중 하나가 전력을 공급받고 있다.
플레이어가 모든 집에 전력을 공급하기 위해서 설치해야 할 발전기의 최소 개수를 구해보자.


첫째 줄에 마을의 크기 N이 주어진다.
다음 N개의 줄에는 마을의 상태를 나타내는 N개의 숫자가 공백을 두고 주어진다.
r번째 줄에서 c번째로 주어지는 값이 M[r][c]에 해당하는.
1 <= N <= 1000
M[r][c] = 0 or 1

출력
플레이어가 모든 집에 전력을 공급하기 위해서 설치해야 할 발전기의 최소 개수를 출력

"""
from collections import deque

direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]
N = int(input())
M = [list(map(int, input().split())) for _ in range(N)]
visited = [[False] * N for _ in range(N)]


def bfs(x, y):
    n = len(M)
    q = deque([(x, y)])

    while q:
        qx, qy = q.popleft()
        for dx, dy in direction:
            nx, ny = qx + dx, qy + dy

            if (0 <= nx < n and 0 <= ny < n and
                    M[nx][ny] == 1 and not visited[nx][ny]):
                visited[nx][ny] = True
                q.append((nx, ny))



n = len(M)
count = 0
for r in range(n):
    for c in range(n):
        if M[r][c] == 1 and not visited[r][c]:
            bfs(r, c)
            count += 1


print(count)


