
"""
Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.
Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.

Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.
"""

def solution(brown, yellow):
    total = brown + yellow # 전체 격자 수 계산

    for width in range(3, int(total**0.5) + 1): # 가능한 크기 탐색, 카펫의 최소 너비는 3이며, 최대 너비는 전체 격자 수의 제곱근을 넘지 않는다.
        if total % width == 0: # 높이 계산
            height = total // width

            brown_count = 2 * (width + height - 2) # 갈색 격자 수 확인


            if brown_count == brown and (width - 2) * (height - 2) == yellow: # 조건 검증
                return [max(width, height), min(width, height)]

    return []
