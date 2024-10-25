"""
이진 트리를 입력받아 전위 순회(preorder traversal), 중위 순회(inorder traversal), 후위 순회(postorder traversal)한 결과를 출력하는 프로그램을 작성하시오.



예를 들어 위와 같은 이진 트리가 입력되면,

전위 순회한 결과 : ABDCEFG // (루트) (왼쪽 자식) (오른쪽 자식)
중위 순회한 결과 : DBAECFG // (왼쪽 자식) (루트) (오른쪽 자식)
후위 순회한 결과 : DBEGFCA // (왼쪽 자식) (오른쪽 자식) (루트)

"""


N = int(input())
edges = [input().split() for _ in range(N)]
tree = {}

for edge in edges:
    node = edge[0]
    left = edge[1]
    right = edge[2]
    if left == ".":
        left = None
    if right == ".":
        right = None
    tree[node] = [left, right]

def pre_trav(center):
    if center is None:
        return

    print(center, end='') # 센터방문
    pre_trav(tree[center][0]) # left
    pre_trav(tree[center][1]) # right

def in_trav(center):
    if center is None:
        return

    in_trav(tree[center][0]) # left
    print(center, end='') # 센터방문
    in_trav(tree[center][1]) # right

def post_trav(center):
    if center is None:
        return

    post_trav(tree[center][0]) # left
    post_trav(tree[center][1]) # right
    print(center, end='') # center


root_node = edges[0][0]

pre_trav(root_node)
print()
in_trav(root_node)
print()
post_trav(root_node)
