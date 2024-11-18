"""

N개의 물질
1번부터 N번까지의 번호를 붙여 관리

가장 밀도가 높은 물질을 찾으려 한다.
어떤 물질의 밀도는 그 물질의 무게를 부피로 나눈 값으로 계산한다.

N개의 물질의 부피와 무게가 주어졌을때, 가장 밀도가 높은 물질의 번호를 출력

물질의 밀도가 동일하다면 그중 더 무거운 물질의 번호를 출력
밀도와 무게가 같다면 그중 번호가 가장 작은 물질의 번호를 출력


(w, v) 무게, 부피

"""
N = int(input())
ele = [tuple(map(int, input().split())) for _ in range(N)]
d = []
for i, e in enumerate(ele):
    w, v = e
    d.append((w/ v, w, i+1))


sorted_item = sorted(d, key=lambda x: (-x[0], -x[1], x[2]))

result = sorted_item[0]

print(result[2])