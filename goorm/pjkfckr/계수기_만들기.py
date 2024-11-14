
"""
각 자리마다 최대값이 다른 숫자판을 가진 계수기를 만들고자 한다.
이 계수기가 만족해야 하는 요건은 다음과 같다.
- 계수기는 N의 자리수를 표시할 수 있다.
- i 번째 자리에는 0부터 A[i] 까지 숫자를 표시할 수 있다.
- 버튼을 한 번 누르면 가장 오른쪽 자리의 숫자가 1 증가한다.
    - 만약 어떤 자리의 숫자가 표시할 수 있는 최댓값보다 커진다면, 숫자를 최소값 0 으로 초기화하고 바로 왼쪽 자리의 숫자를 1 증가시킨다.
    - 가장 왼쪽 자리의 숫자가 최대값을 넘어갈 때는 왼쪽으로 올림을 전파하지 않는다.


계수기에 표시되는 숫자의 초기 상태가 주어질 때, K번 버튼을 누른 뒤 계수기의 각 자리에 표시되는 숫자를 구해보자.


입력
첫째 줄에 계수기의 숫자판의 개수 N이 주어진다.
둘째 줄에 각 숫자판의 최대값을 의미하는 A[1], A[2], A[N] 이 공백을 두고 주어진다.
셋째 줄에 각 숫자판의 초기값을 의미하는 B[1], B[2], B[N] 이 공백을 두고 주어진다.
넷째 줄에 버튼을 누르는 횟수 K가 주어진다.


출력
K번 버튼을 누른 뒤 계수기에 표시되는 N개의 숫자를 공백없이 출력

"""

N = int(input())
A = list(map(int, input().split()))
B = list(map(int, input().split()))
K = int(input())

def increment(counter, max_value):
    n = len(counter)
    for i in range(n - 1, -1, -1): # -1 까지가 end로 조건을 주고 -1 로 reverse 시켜서 최대에서 최솟값으로 움직이도록, 즉 오른쪽에서 왼쪽으로 이동
        counter[i] += 1
        if counter[i] > max_value[i]:
            counter[i] = 0
            if i == 0:
                break
        else:
            break

    return counter

for _ in range(K):
    B = increment(B, A)

result = "".join(map(str, B))
print(result)