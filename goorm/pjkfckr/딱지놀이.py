
"""

딱지놀이

A,B 가 딱지놀이

규칙
- 매 라운드마다 각자 하나를 선택
- 별, 동그라미, 네모, 세모
- 두 어린이가 낸 딱지 중 어느 쪽이 더 강력한 것인지

만약 두 딱지의 별의 개수가 다르면 별이 많은 쪽의 딱지가 이긴다.
별의 개수가 같고 동그라미의 개수가 다르면, 동그라미가 많은 쪽의 딱지가 이긴다
별, 동그라미의 개수가 각각 같고 네모의 개수가 다르면, 네모가 많은 쪽의 딱지가 이긴다.
별, 동그라미, 네모의 개수가 각각 같고 세모의 개수가 다르면, 세모가 많은 쪽의 딱지가 이긴다.
별, 동그라미, 네모, 세모의 개수가 각각 모두 같다면 무승부




"""

from collections import Counter


N = int(input())

initial_counter = Counter({1: 0, 2: 0, 3: 0, 4: 0})

def compare_cards(a, b):
    for shape in [4, 3, 2, 1]:
        if a[shape] > b[shape]:
            return "A"
        elif a[shape] < b[shape]:
            return "B"

    return "D"


for _ in range(N):

    # ㄷ딱지의 그림의 개수 가 첫번째, 그뒤는 그림의 종류
    a_list = list(map(int, input().split()))[1:]
    b_list = list(map(int, input().split()))[1:]


    a_count = initial_counter + Counter(a_list)
    b_count = initial_counter + Counter(b_list)

    print(compare_cards(a_count, b_count))


print()