"""
문제
루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.

출력
첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.

"""


from collections import deque

N = int(input())
edges = [input().split() for _ in range(N-1)]
tree = {}
parents = [0] * (N + 1)

for edge in edges:
    parent, child = map(int, edge)
    if parent not in tree:
        tree[parent] = []
    tree[parent].append(child)

    if child not in tree:
        tree[child] = []
    tree[child].append(parent)

queue = deque([1]) # 트르의 루트를 1이라고 가정
visited = [False] * (N+1)
visited[1] = True

while queue:
    current = queue.popleft()

    for visit in tree[current]:
        if not visited[visit]:
            parents[visit] = current # 현재 노드를 부모로 설정
            queue.append(visit)


for i in range(2, N + 1): # 2번 노드부터 순서대로 출력
    print(parents[i])


