

"""

총 N개의 시험
시험 별 성적은 과목 번호 c[i]와 시험 성적 s[i] 의 정보를 가지고 있다.
과목마다 보는 시험의 개수가 다르고, 아에 안보는 경우가 있다.

이번 학기 가장 좋은 평균 점수를 가진 과목 번호 출력
만약 평균이 같다면 과목번호가 더 작은 과목 번호 출력

"""
from collections import defaultdict

N, M = map(int, input().split())

score_groups = defaultdict(lambda: [0, 0])
result = 0

for _ in range(N):
    c, s = map(int, input().split())
    score_groups[c][0] += s
    score_groups[c][1] += 1

max_avg = 0
best_subject = M

for subject, (total, count) in score_groups.items():
    avg = total / count
    if avg > max_avg or (avg == max_avg and subject < best_subject):
        max_avg = avg
        best_subject = subject

print(best_subject)