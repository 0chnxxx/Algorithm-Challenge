"""
용량이 다른 두 개의 빈 물통 A,B
물을 채우고 비우는 일을 반복하여 두 물통을 원하는 사애가 되도록 만들어야한다.
물통 이외에는 물의 양을 정확히 잴 수 있는 방법이 없으며, 가능한 작업은 다음과 같은 세 종류가 전부이다.

- [F(x): Fill x]: 물통 x에 물을 가득 채운다. (물을 채우기 전에 물통 x가 비어있는지 여부는 관계 없음. 다른 물통은 그대로 둠)
- [E(x): Empty x] : 물통 x의 물을 모두 버린다. (다른 물통은 그대로 둠)
- [M(x, y): Move water from x to y]: 물통 x의 물을 물통 y에 붓는다.
이때 만약 물통 x에 남아있는 물의 양이 물 통 y에 남아있는 빈 공간보다 적거나 같다면
물통 x의 물을 물통 y에 모두 붓는다.
만약 물통 x에 남아 있는 물의 양이 물통 y에 남아 있는 빈 공간보다 많다면 부을 수 있는 만큼 최대로 부어 물통 y를 꽉 채우고 나머지 물통 x에 남긴다.

물통 A의 용량을 나타내는 정수 a,
물통 B의 용량을 나타내는 정수 b,
최종 상태에서 물통 A에 남겨야 하는 물의 용량을 나타내는 정수 c
최종 상태에서 물통 B에 남겨야 하는 물의 용량을 나타내는 정수 d

"""
from collections import deque

a, b, c, d = map(int, input().split())

def bfs(a, b, c, d):
    q = deque([(0, 0, 0)]) # (x, y, 작업횟수)

    visited = {(0, 0)}

    while q:
        x, y, count = q.popleft()

        if x == c and y == d:
            return count

        for nx, ny in [
            (a, y), (x, b), # Fill
            (0, y), (x, 0), # Empty
            (max(0, x - (b -y)), min(b, y + x)), # Move A to B
            (min(a, x + y), max(0, y - (a - x))) # Move B to A
        ]:
            if (nx, ny) not in visited:
                visited.add((nx, ny))
                q.append((nx, ny, count + 1))

    return -1

result = bfs(a, b, c, d)
print(result)
