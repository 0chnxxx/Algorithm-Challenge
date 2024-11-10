

"""

N * N 크기의 정사각형 모양의 땅이 있다. 땅을 1 * 1 크기의 작은 땅으로 나누었을 때,
위에서 y번째, 왼쪽에서 x번째에 위치한 땅의 좌표를 (y, x)로 나타낸다.

추가로 모든 땅에는 "폭탄 값" 이라고 하는 값이 있다.
모든 폭탄 값의 초기값은 0

K개의 폭탄을 땅 위에 떨어트리려고한다. 어떤 1 x 1 크기의 땅 위에 폭탄을 떨어트리게 되면
폭탄이 떨어진 땅과, 그 땅에 상하좌우로 인접한 칸의 폭탄 값에 영향을 끼친다.
폭탄 값이 변하는 정도는 땅의 상태에 따라 다르다.

N x N 크기 영역 밖이거나, 땅의 상태가 "#" 이라면 변하지 않는다.
땅의 상태가 0 이라면 폭탄 값은 1 증가
땅의 상태가 "@"이라면 폭탄 값은 2 증가

모든 폭탄을 떨어트린 뒤에, 모든 땅의 폭탄 값 중에서 가장 높은 값을 출력



입력
첫째 줄에 땅의 한변의 길이 N과 떨어트릴 횟수 K

다음 N개의 줄에는 땅의 상태를 나타내는 N개의 문자가 공백을 두고 주어진다.

r 번째 줄에는 c번째로 주어지는 문자는 (r, c) 좌표에 해당하는 땅의 상태

다음 K개의 줄에는 폭탄을 떨어트릴 땅의 좌표를 나타내는 y, x가 공백을 두고 주어진다.

"""

N, K = map(int, input().split())
matrix = [input().split() for _ in range(N)]
booms = [list(map(int, input().split())) for _ in range(K)]


cnt_list = [[0] * N for _ in range(N)]
direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]



for boom in booms:
    x, y = boom[0] - 1, boom[1] - 1
    if boom[0] > N or boom[1] > N:
        continue

    if matrix[x][y] == "@":
        cnt_list[x][y] += 2
    elif matrix[x][y] == "0":
        cnt_list[x][y] += 1


    for dx, dy in direction:
        nx, ny = x + dx, y + dy
        if 0 <= nx < N and 0 <= ny < N:
            if matrix[nx][ny] == "#":
                continue
            elif matrix[nx][ny] == "@":
                cnt_list[nx][ny] += 2
            elif matrix[nx][ny] == "0":
                cnt_list[nx][ny] += 1


max_num = max(max(row) for row in cnt_list)
print(max_num)