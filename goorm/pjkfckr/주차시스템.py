
"""

현대백화점에 세로가 N칸, 가로가 M칸으로 이루어진 주차장이 있다.
주차장의 각 칸에는 1대의 차량만 주차할 수 있다.

최근 현대백화점을 이용하는 손님이 증가하면서 주차장이 매우 혼잡하다.
현대모비스는 이 문제를 해결하기 위해서 특별한 주차 시스템을 만들어,
모든 자동차가 효율적으로 주차할 수 있도록 하였다.
주차 시스템은 다음과 같은 방식으로 작동된다.

주차시스템은 주차장의 한 칸을 3가지로 분류한다.
- 0은 현재 해당 칸에 자동차가 없다는 것을 의미
- 1은 현재 해당 칸에 주차한 자동차가 있음
- 2는 현재 해당 칸에 자동차는 없지만, 장애인 전용 주차 공간.

여러 구역으로 분리
같은 구역에 속한 두 주차 칸 사이에는 항상 상태가 1인 칸을 거치지 않고, 이동하는 경로가 존재
서로 다른 구역에 속한 두 주차 칸 사이에는 그러한 경로가 존재하지 않도록 주차 구역을 나눈다.

주차 시스템은 마지막으로 각 분할한 구역의 주차 분류의 상태에 따라서 점수를 측정
주차 구역에 존재하는 상태가 0인 칸의 개수마다 1점을 더하고, 상태가 2인 칸의 개수마다 2점을 뺀다.
이렇게 모든 주차 구역에 대해 점수를 측정하고, 이중 가장 높은 점수를 가지고 있는 구역으로 차를 안내


"""
from collections import deque
import sys

input = sys.stdin.readline  # 입력 속도 개선

N, M = map(int, input().split())

parking = [list(map(int, input().split())) for _ in range(N)]

visited = [[False] * M for _ in range(N)]

def bfs(x, y):
    queue = deque([(x, y)])
    direction = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    visited[x][y] = True
    score = 0

    if parking[x][y] == 0:
        score += 1
    elif parking[x][y] == 2:
        score -= 2

    while queue:
        cx, cy = queue.popleft()

        for dx, dy in direction:
            nx, ny = cx + dx, cy + dy

            if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny] and parking[nx][ny] != 1:
                visited[nx][ny] = True
                queue.append((nx, ny))

                if parking[nx][ny] == 0:
                    score += 1
                elif parking[nx][ny] == 2:
                    score -= 2

    return score

max_score = 0
for i in range(N):
    for j in range(M):
        if not visited[i][j] and parking[i][j] != 1:
            score = bfs(i, j)
            max_score = max(max_score, score)


print(max(max_score, 0))