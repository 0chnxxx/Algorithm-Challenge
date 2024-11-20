"""

N종류가 한 개 씩있고, 각 과읠의 가격은 P[i]
그리고 그 과일을 먹었을때 플레이어가 얻을 수 있는 포만감은 C[i]이다.

과일을 조각 단위로 구매하는 것이 가능하다.
가격이 p인 과일을 조각 단위로 구매하고자 할 경우,
플레이어는 이 과일을 p개의 조각으로 자른 뒤 그중 원하는 몇 개의 조각만을 구매할 수 있다.
이때 모든 조각의 가격은 1, 먹었을때  얻을 수 있는 포만감은 C[i] / P[i] 로 동일

플레이어는 K만큼의 돈을 가지고 있고, 플레이어는 주어진 금액 이내에서 구매한 과일들의 포만감 합이
가장 크게 되도록 살 과일을 선택하려고 한다.
플레이어가 최적의 방법에 따라 과일을 구매했을 때, 구매한 과일들의 최대 포만감 합을 구해보자


"""

N, K = map(int, input().split())

P_C = [tuple(map(int, input().split())) for _ in range(N)]

# 단위 가격당 포만감 계산 및 정렬
fruits = sorted([(c / p, p, c) for p, c in P_C], reverse=True)
amount_c = 0

for unit, p, c in fruits:
    if K >= p:
        amount_c += c
        K -= p

    else:
        amount_c += K * unit
        break

# print(int(amount_c))
