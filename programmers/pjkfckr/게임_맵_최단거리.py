from collections import deque

def solution(maps):
    di = [0, 1, 0, -1]
    dj = [1, 0, -1, 0]


    q = deque([[0, 0, 1]])
    seen = [[0] * len(maps[i]) for i in range(len(maps))]

    seen[0][0] = 1

    while q:
        ci, cj, distance = q.popleft()

        # 최단거리
        if ci == len(maps) - 1 and cj == len(maps[ci]) - 1:
            return distance

        for k in range(4):
            ni = ci + di[k]
            nj = cj + dj[k]

            # 무시
            if not (0 <= ni < len(maps) and 0 <= nj < len(maps[ni])):
                continue

            # 진행 가능 방향이거나 이미 지나온 곳 확인
            if maps[ni][nj] != 1 or seen[ni][nj]:
                continue

            seen[ni][nj] = 1
            q.append([ni, nj, distance + 1])

    return -1