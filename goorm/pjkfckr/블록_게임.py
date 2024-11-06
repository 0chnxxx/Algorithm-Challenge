"""

2차원
1. (0, 0)위치에 1인 블록
2. 그다음 블록은 가장 마지막에 놓은 블록의 상화좌우 중 한곳에 놓는다.
    모든 블록의 크기는 1x1로 동일하고, 항상 이전 블록에 딱 붙어 있도록 블록을 놓는다 . i번째로 놓게 되는 블록의 점수는 S[i] 이다.
3. 게임을 진행하다 보면, 블록을 놓아야 하는 자리에 이미 블록이 있는 경우가 있을 수 있다.
    이런 경우에는 블록을 겹쳐 놓는 것이 아니라, 블록을 새로 놓는 위치에 원래 블록이 존재하지 않을 때까지 최근에 놓은 블록들을 순서대로 제거한다.
    그 다음 블록을 놓는다.


"""

N = int(input()) # 블록을 올려놓은 횟수
block_direction = input().rstrip() # 블록을 놓는 방향을 의미하는 길이 N의 문자열 D
block_scores = list(map(int, input().split(' '))) # 블록의 점수 (공백 을 두고)
dx = {'L': -1, 'R': 1, 'U': 0, 'D': 0} # 상하좌우
dy = {'L': 0, 'R': 0, 'U': 1, 'D': -1} # 상하좌우


x, y = 0, 0

# 게임판과 블록 위치를 저장
board = {}
blocks = []

board[(x, y)] = 1 # 처음 위치 1점
blocks.append((x, y))


for direction, score in zip(block_direction, block_scores):
    # 위치
    nx, ny = x + dx[direction], y + dy[direction]

    # 이미 블록이 있는 경우
    while (nx, ny) in board:
        del board[blocks.pop()]

    # 블록 추가
    board[(nx, ny)] = score
    blocks.append((nx, ny))

    # 위치 업데이트
    x, y = nx, ny


total_score = sum(board.values())

print(total_score)