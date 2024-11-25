
from collections import defaultdict

N = int(input())

N_list = [tuple(map(int, input().split())) for _ in range(N)]

speed_groups = defaultdict(list)
result = 0

for i in range(N):
    v, w = N_list[i]
    speed_groups[v].append((i + 1, w))


for v in speed_groups:
    sorted_vehicles = sorted(speed_groups[v], key=lambda x: (-x[1], -x[0]))
    result += sorted_vehicles[0][0]

print(result)