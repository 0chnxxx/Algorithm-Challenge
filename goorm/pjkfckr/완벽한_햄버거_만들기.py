"""

구름 햄버거는 N개의 재료를 순서대로 쌓아서 만들고,
구름 햄버거의 맛은 사용된 모든 재료의 맛의 정도를 더한 값이다.

완벽한 구름 햄버거를 만들기 위해서는 맛의 정도가 가장 높은 재료를 기준으로
위랑 아래로 갈수록 재료의 맛의 정도가 감소하거나 같아야 한다.

플레이어는 N개의 재료를 순서대로 쌓아서 구름 햄버거를 만들었다.
i번째로 쌓은 재료의 맛의 정도가 K[i]라고 할때, 플레이어가 만든 구름 햄버거의 맛을 구해보자.

만약 플레이어가 완벽하지 않은 구름 햄버거를 만들었다면 0을 출력한다.

입력
첫째 줄에 구름 햄버거에 들어가 재료의 개수 N개가 주어진다.
그 다음 줄에 플레이어가 햄버거를 만들 때 쓴 재료의 맛의 정도 K1....Kn 가 공배긍로 주어진다.
"""
from collections import deque

N = int(input())
K = list(map(int, input().split()))

# 구름 햄버거의 맛은 사용된 모든 재료의 맛의 정도를 더한 값
# 맛의 정도가 가장 높은 재료를 기준으로 내림차순
is_perfect = True

max_index = K.index(max(K))
# 최대값 왼쪽 검사
for i in range(max_index - 1, -1, -1):
    if K[i] > K[i+1]:
        is_perfect = False
        break

# 최대값 오른쪽 검사
for i in range(max_index + 1, len(K)):
    if K[i] > K[i-1]:
        is_perfect = False
        break

if is_perfect:
    print(sum(K))
else:
    print(0)
