
from collections import defaultdict

N = int(input())

speed_groups = defaultdict(lambda: (0, 0))
result = 0

# i + 1 이 i번째 차량정보
for i in range(1, N + 1):
    v, w = map(int, input().split())
    # 최근 스피드 그룹에서 속도로 데이터 찾기
    current_max = speed_groups[v]
    # 내구도가 데이터의 최대보다 크거나 (내구도가 같고 차량번호가 더 크면)
    if w > current_max[0] or (w == current_max[0] and i > current_max[1]):
        speed_groups[v] = (w, i)

# 차량번호 더하기
result = sum(i[1] for i in speed_groups.values())
print(result)

# Timeout Code
# N_list = [tuple(map(int, input().split())) for _ in range(N)]
#
# speed_groups = defaultdict(list)
# result = 0
#
# for i in range(N):
#     v, w = N_list[i]
#     speed_groups[v].append((i + 1, w))
#
#
# for v in speed_groups:
#     sorted_vehicles = sorted(speed_groups[v], key=lambda x: (-x[1], -x[0]))
#     result += sorted_vehicles[0][0]
#
# print(result)
#
