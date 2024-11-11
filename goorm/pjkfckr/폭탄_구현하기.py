

"""

N * N  크기의 정사각형 모양의 땅.
1 * 1 크기의 작은 땅으로 나누었을 때, 위에서 y번째 왼쪽에서 x번째에 위치한 땅 좌표를 (y, x)
추가로 모든 땅에는 폭탄 값이라고 하는 값이 있다.


K개의 폭탄을 이 땅 위에 떨어트리려고 한다. 어떤 1 x 1 크기의 땅 위에 폭탄을 떨어트리게 되면 폭탄이 떨어진 땅과,
그 땅의 상하좌우로 인접한 칸의 폭탄 값이 1 증가
"""


N, K = map(int, input().split())
# before_booms = [list(map(int, input().split())) for _ in range(K)]

# booms 리스트 생성 시 list 대신 tuple을 사용했습니다. 튜플은 불변(immutable)이므로 리스트보다 메모리 사용량이 적고 접근 속도가 빠릅니다.
after_booms = [tuple(map(int, input().split())) for _ in range(K)]


N_map = [[0] * N for _ in range(N)]
direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]


def before_refactor(booms):
    for boom in booms:
        x, y = boom[0] - 1, boom[1] - 1

        N_map[x][y] += 1
        for dx, dy in direction:
            nx, ny = x + dx, y + dy

            if 0 <= nx < N and 0 <= ny < N:
                N_map[nx][ny] += 1

    return sum(sum(row) for row in N_map)


def after_refactor(booms):
    # boom 루프에서 boom와 boom 대신 직접 x, y로 언패킹했습니다. 이렇게 하면 리스트 인덱싱 연산을 줄일 수 있습니다.
    for x, y in booms:
        # x와 y에서 1을 빼는 연산을 루프 시작 부분으로 옮겼습니다. 이렇게 하면 매 방향 확인 시마다 계산할 필요가 없어집니다.
        x -= 1
        y -= 1
        N_map[x][y] += 1
        for dx, dy in direction:
            nx, ny = x + dx, y + dy
            if 0 <= nx < N and 0 <= ny < N:
                N_map[nx][ny] += 1

    return sum(sum(row) for row in N_map)

result = after_refactor(after_booms)
print(result)