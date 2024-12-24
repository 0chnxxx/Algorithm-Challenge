import heapq

nums = [3,2,1,5,6,4]
k = 2
heap = []

for num in nums:
    heapq.heappush(heap, -num)

for _ in range(k - 1):
    heapq.heappop(heap)

print(-heapq.heappop(heap))