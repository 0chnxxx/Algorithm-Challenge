"""


- i번 문제를 해결하고 S[i] 만큼의 점수를 받지만, 한 문제만 풀 수 있다.
- 푼 문제의 번호도 연속하고 푼 문제로 얻을 수 있는 점수도 연속적으로 1씩 증가할 때,
  푼 문제의 점수의 합한 값을 점수로 받지만, 연속되지 않은 문제를 풀면 점수를 얻을 수 없다.
- 2개의 방법 중 더 높은 점수를 얻는다.

최고 점수를 얻어야 하지만, 모든 문제를 풀 시간은 없기에, 최소환의 문제를 풀어서 최고 점수를 얻고자 한다.

"""

N = int(input())

S = sorted(set(map(int, input().split())))
prev = S[0]
score = S[0]
max_score = S[0]

for cur in S[1:]:
    if cur - prev == 1:
        score += cur
    else:
        score = cur

    max_score = max(max_score, score)
    prev = cur

print(max_score)