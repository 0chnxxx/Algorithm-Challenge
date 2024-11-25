"""

구름이의 첫 업무는 구름 사이트에서 사용자들이 자주 실행하는 이벤트를 정리하는 일
이때 이벤트란 사용자가 웹사이트에서 실행하거나, 클릭한 것을 의미한다.

구름이는 이를 위해서 사용자들이 취할 수 있는 이벤트를 N개로 규정하고, 각각의 이벤트에 1번부터 N번까지 번호를 붙였다.

구름이는 이어서 M명의 사용자가 구름 사이트에서 이벤트를 실행한 내역을 추출하였다.
추출한 정보를 바탕으로 구름이는 사용자들이 가장 자주 발생하는 이벤트들을 알아내고자 한다.
한 사람이 같은 이벤트를 여러 번 실행한 경우에도 중복으로 세어준다.

구름이를 도와 M명의 사용자들이 가장 자주 실행했던 이벤트들을 찾아 출력


"""
from collections import Counter
import itertools

N, M = map(int, input().split())
K = [list(map(int, input().split()[1:])) for _ in range(M)]

# 이중배열 평탄화
filter_k = list(itertools.chain(*K))
counter = Counter(filter_k)

most_common = counter.most_common()

max_count = most_common[0][1]

result = [event for event, count in most_common if count == max_count]
print(*sorted(result, reverse=True))