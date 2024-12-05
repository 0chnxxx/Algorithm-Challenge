"""

구름 찾기 게임은 한 변의 길이가 N인 격자 모양의 게임판 M에서 진행하는 게임.
게임판의 일부 칸에는 "구름" 이 숨겨져 있고, 게임판에 숨겨진 모든 구름의 위치를 찾으면 승리

구름 찾기 게임의 제작자인 플레이어는 조금 더 쉽게 구름을 찾을 수 있도록 도와주는 깃발을 게임판에 설치하려 한다.
깃발은 구름이 없는 칸이면서, 상하좌우와 대각선으로 인접한 여덟 칸 구름이 하나 이상 있는 칸에만 설치할 수 있다.

이렇게 설치한 깃발에는 인접한 여덟 칸 중 구름이 있는 칸의 개수에 해당하는 값이 적힌다.

플레이어는 깃발을 세울 수 있는 모든 칸에 깃발을 세워두웠다.
문득, 플레이어는 깃발 중 값이 K인 깃발이 몇 개나 있는지가 궁금해졌다.

값이 K인 깃발의 개수를 세어보자.


"""
import itertools
from collections import Counter

N, K = map(int, input().split())

M = [list(map(int, input().split())) for _ in range(N)]
M_copy = [[0] * N for _ in range(N)]

visited = [[False] * N for _ in range(N)]

def bfs(x, y):
    direction = [(0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0), (-1, 1)]
    count = 0
    for dx, dy in direction:
        nx, ny = x + dx, y + dy
        if 0 <= nx < N and 0 <= ny < N and M[nx][ny] == 1:
            count += 1
    if M[x][y] != 1:
        M_copy[x][y] = count



for i in range(N):
    for j in range(N):
        bfs(i, j)

counter = Counter(list(itertools.chain(*M_copy)))

print(counter[K])