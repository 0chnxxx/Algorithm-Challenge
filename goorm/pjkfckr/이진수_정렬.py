
"""
N 개의 10진수 정수가 주어진다.
플레이어에게 정수를 그냥 정렬하는 것은 너무 쉽기때문에 아래 기준에 따라 정수를 정렬

1. 10진수 정수를 2진수로 나타냈을 때, 2진수에 포함된 1의 개수를 기준으로 내림차순 정렬한다.
2. 1의 개수가 같다면, 원래 10진수를 기준으로 내림차순 정렬한다.

"""

N, K = map(int, input().split())
A = list(map(int, input().split()))

def count_ones_binary(num):
    return bin(num).count('1')

sorted_list = sorted(A, key=lambda x: (-count_ones_binary(x), -x))

print(sorted_list[K-1])
