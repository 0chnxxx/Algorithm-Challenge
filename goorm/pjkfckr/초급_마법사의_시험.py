

"""

출구를 찾아야 한다.
이때 지나가는 길에 나무가 가로막고 있으면 그냥 지나갈 수 없고, 10마력을 소비하면 그 나무를 통과해 지나갈 수 있다
단, 연속된 2개 이상의 나무는 통과할 수 없다

이때, 마력 K만큼 가지고 있고 이 시험을 통과하라면 최소 몇 초가 걸리는지 구해라.

크기를 나타내는 세로길이 R, 가로길이 C, 마력 K가 주어진다.

다음 R개의 줄에 걸쳐 정보가 주어진다.
각 줄은 C개의 문자로 구성되며 0은 지나갈 수 있는길, 1은 나무를 의미
인접한 곳으로 한 칸 이동할 때 1초가 소요되며, 현재 위치에서 마력을 10만큼 사용하여
인전한 나무의 다음칸으로 이동할 때도 1초가 소용된다.
여기서 인접한 칸은 상하좌우 네 방향으로 맞닿아있는 칸을 의미

최초 시작 위치는 좌측 상단 (1, 1) 이고, 출구는 우측 하단 (R, C)에 위치
"""
from collections import deque

R, C, K = map(int, input().split())
J = [input().rstrip() for _ in range(R)]

def bfs(R, C, K, J):
    queue = deque([(0, 0, K, 0)])
    visited = {(0, 0, K)}
    direction = [(1, 0), (-1, 0), (0, 1), (0, -1)]

    while queue:
        x, y, k, cnt = queue.popleft()

        if x == R - 1 and y == C - 1:
            return cnt

        for dx, dy in direction:
            nx, ny = x + dx, y + dy

            if 0 <= nx < R and 0 <= ny < C:
                if J[nx][ny] == "0":
                    if (nx, ny, k) not in visited:
                        visited.add((nx, ny, k))
                        queue.append((nx, ny, k, cnt + 1))

                elif J[nx][ny] == "1" and k >= 10:
                    mx, my = nx + dx, ny + dy
                    if 0 <= mx < R and 0 <= my < C and J[mx][my] == "0":
                        if (mx, my, k - 10) not in visited:
                            visited.add((mx, my, k - 10))
                            queue.append((mx, my, k - 10, cnt + 1))

    return -1

print(bfs(R, C, K, J))
