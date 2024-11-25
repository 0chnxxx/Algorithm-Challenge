"""

구름 심시티를 하고 있는 플레이어는 한 변의 길이가 N인 정사각형 모양의 마을 M을 만들고 있다.

마을의 모든 칸에는 건물이 하나씩 있고, r번째 행, c번째 열에 해당하는 칸에는
정수 M[r][c]가 적혀있다. M[r][c]는 해당 칸 에 있는 컨물의 유형의 번호를 의미한다.

건물의 유형이 동일하면서, 서로 상하좌우 인접한 칸에 위치한 건물끼리는 서로 전력을 공유할 수 있다.
전력을 공유할 수 있는 관계에 속한 건물의 개수가 K개 이상이면 이를 단지라고 한다.

플레이어는 발전기를 설치해 각 단지에 전력을 공급하고자 한다.
하지만 비용 문제로 인해 단 하나의 발전기만 설치할 수 있다.
발전기는 특정 건물 유형 하나에 해당하는 모든 단지에 전력을 공급할 수 있다.

그래서 플레이어는 가장 많은 단지가 있는 건물 유형에 전력을 공급할 것이다.

만약 그러한 건물 유형이 여러 개라면, M[r][c] 가 더 큰 건물 유형에 전력을 공급한다.

플레이어가 전력을 공급해야 할 건물의 유형 번호를 구해보자

"""
from collections import deque

N, K = map(int, input().split())
M = [list(map(int, input().split())) for _ in range(N)]
direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]
visited = [[False] * N for _ in range(N)]
result_map = {}

def bfs(x, y):
    q = deque([(x, y)])
    count = 1
    main = M[x][y]
    visited[x][y] = True
    while q:
        qx, qy = q.popleft()
        for dx, dy in direction:
            nx, ny = qx + dx, qy + dy

            if (0 <= nx < N and 0 <= ny < N
                    and not visited[nx][ny] and M[nx][ny] == main):
                visited[nx][ny] = True
                q.append((nx, ny))
                count += 1

    return count, main




for r in range(N):
    for c in range(N):
        if not visited[r][c]:
            cnt, type = bfs(r, c)
            if cnt >= K:
                if type not in result_map:
                    result_map[type] = 0
                result_map[type] += 1

if result_map:
    max_result = max(result_map.values())
    candidates = [t for t, cnt in result_map.items() if cnt == max_result]
    print(max(candidates))
else:
    print(max(max(row) for row in M))
