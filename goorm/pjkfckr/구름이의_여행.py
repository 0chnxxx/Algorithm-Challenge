

"""
N 개의 섬으로 이루어져있다.

각 섬에는 1부터 N까지의 번호

섬 사이에 M개의 다리를 설치했다


- 모든 다리는 양방향으로 이동할 수 있다.
- 서로 다른 두 섬을 잇는 다리는 최대 하나이다.
- 다리가 잇는 두 섬은 항상 다른 섬이다.

1번 섬에서 출발해서 N번 섬으로 가려고 하는데, 통과하는 다리의 개수가 K개 이하가 되길 원한다.
구름이를 도와 1번 섬에서 N번 섬까지 K개 이하의 다리를 이용해 도착할 수 있는지를 판별해보자.


섬의 개수 N, 다리의 개수 M, 건널 다리 최대 개수 K

그 다음 M 개의 줄에는 다리가 잇는 두 섬의 번호를 의미하는 ui, vi 가 공백을 두고 주어진다.

"""
from collections import deque


def bfs(n, k, bridge):
    graph = [[] for _ in range(n + 1)]

    for u, v in bridge:
        graph[u].append(v)
        graph[v].append(u)

    queue = deque([(1, 0)]) # (섬 번호, 사용한 다리 개수)
    visited = [float('inf')] * (n+1)  # 각 섬에 도달하기 위한 최소 다리 개수
    visited[1] = 0

    while queue:
        cur, used_bridge = queue.popleft()

        if cur == n and used_bridge <= k:
            return True

        if used_bridge >= k:
            continue

        for next_island in graph[cur]:
            if used_bridge + 1 < visited[next_island]:
                visited[next_island] = used_bridge + 1
                queue.append((next_island, used_bridge + 1))

    return False





def solution():
    N, M, K = map(int, input().split())
    bridges = [tuple(map(int, input().split())) for _ in range(M)]

    result = bfs(N, K, bridges)

    return "YES" if result else "NO"


result = solution()
print(result)