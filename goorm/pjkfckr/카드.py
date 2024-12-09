
"""

deposit, pay, reservation 의 기능을 가지고 있다.

- deposit: 주어진 금액만큼 계좌에 돈이 들어온다.
- pay: 주어진 금액만큼 계좌에서 돈이 결제된다. 단 계좌의 현재 잔액이 주어진 금액보다 적다면 결제되지 않는다.
- reservation: 주어진 금액만큼 계좌에서 돈이 결제된다.
                단, 계좌의 현재 잔액이 주어진 금액보다 적거나,
                대기 목록에 다른 거래가 있다면 결제되지 않고 대기 목록의 맨 뒤에 추가된다.

- 대기 목록에 있는 거래들은 대기 목록에 들어간 순서대로 결제가 가능해지는 즉시
해당 거래의 금액만큼 계좌에서 금액이 차감된 뒤 대기목록에서 삭제된다.

계좌에 들어있는 금액과 지난달 거래내역이 주어졌을 때,
주어진 모든 거래가 진행된 뒤에 계좌에 남아있는 금액을 출력
거래가 완료되지 않고 대기 목록에 남아있는 경우도 거래가 진행된 것으로 본다.

"""
from collections import deque

N, K = map(int, input().split())
queue = deque()

def process_queue(n):
    while queue and n >= int(queue[0]):
        n -= int(queue.popleft())

    return n

for _ in range(K):
    payment_type, amount = map(str, input().split())

    if payment_type == 'deposit':
        N += int(amount)
        N = process_queue(N)

    elif payment_type == 'pay':
        if N >= int(amount):
            N -= int(amount)

    elif payment_type == 'reservation':
        if not queue and N >= int(amount):
            N -= int(amount)
        else:
            queue.append(amount)

    N = process_queue(N)

print(N)