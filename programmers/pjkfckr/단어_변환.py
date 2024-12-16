
"""

두 개의 단어 begin, target 과 단어의 집합 words가 있다.

아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려 한다.

1. 한 번에 한개의 알파벳만 바꿀 수 있다.
2. words에 있는 단어로만 변환할 수 있다.

"""
from collections import deque

begin = "hit"
target = "cog"
words = ["hot", "dot", "dog", "lot", "log", "cog"]
def bfs(begin, target, words):
    if target not in words:
        return 0

    queue = deque([(begin, 0)])
    visited = set()

    while queue:
        word, depth = queue.popleft()

        if word == target:
            return depth

        for i in range(len(word)):
            for w in words:
                # sum(a != b for a, b in zip(word, w))
                # 현재 단어와 정확히 한 글자만 다른 단어 찾기
                if w not in visited and sum(a != b for a, b in zip(word, w)) == 1:
                    queue.append((w, depth + 1))
                    visited.add(w)

    return 0

result = bfs(begin, target, words)
print(result)



