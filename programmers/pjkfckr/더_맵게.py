import heapq

data = [1, 2, 3, 9, 10, 12]
K = 7
heapq.heapify(data)
cnt = 0
while len(data) > 1:
    min_value = heapq.heappop(data)
    if min_value >= K:
        break

    second_min_value = heapq.heappop(data)
    result = min_value + (second_min_value * 2)
    heapq.heappush(data, result)
    cnt += 1


if data and data[0] >= K:
    print(cnt)
else:
    print(-1)


