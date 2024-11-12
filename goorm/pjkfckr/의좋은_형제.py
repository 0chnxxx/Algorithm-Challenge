

"""

식량 젋절반을 구름이에게 가져다 주었다.
가지고 있는 식량의 양이 홀수라서 반으로 나눌 수 없는 경우
식량 전부를 준다.

D번째 날에는 두 형제에게 식량이 얼마나 남았을지 궁금해졌다.
D번째의 날에 구름이와 바람이는 식량을 얼마나 갖고 있을지 출력하시오


처음 식량 100, 100 을 갖고있고
첫째날엔 절반인 50을 전해주면 50이 남고 150이 남는다.

"""

N, M = map(int, input().split())
D = int(input())


def ceil_div(a, b):
    return (a + b - 1) // b

# D일 동안의 시뮬레이션
for i in range(1, D + 1):
    if i % 2 != 0:
        if N % 2 == 0:
            M += N // 2
            N //= 2
        else:
            M += ceil_div(N, 2)
            N = N - ceil_div(N, 2)

    else:  # 짝수 날
        if M % 2 == 0:
            N += M // 2
            M //= 2
        else:
            N += ceil_div(M, 2)
            M = M - ceil_div(M, 2)

print(N, M)