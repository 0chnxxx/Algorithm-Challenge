"""
1번부터 N번 카드까치 총 N종의 카드가 있다.

M장의 카드들이 순서대로 제공되고, N종의 카드를 모두 모으면 승리할 수 있다.

최소한의 카드로 승리 할 수 있는지 말아야한다.

M장의 카드를 모두 받아도 모든 종류의 카드를 모을수 없다면 -1



"""
from collections import Counter


def solution():
    N, M = map(int, input().split())
    card_list = [int(input()) for _ in range(M)]

    set_list = set()

    for cnt, card in enumerate(card_list, 1):
        set_list.add(card)
        if len(set_list) == N:
            return cnt

    return -1

result = solution()

print(result)