
from collections import Counter

def solution(topping):
    if len(topping) % 2 != 0:
        return 0


    left = Counter()
    right = Counter(topping)
    result = 0
    for t in topping:
        left[t] += 1
        right[t] -= 1
        if right[t] == 0:
            del right[t]

        if len(left) == len(right):
            result += 1
    return result

solution([1, 2, 1, 3, 1, 4, 1, 2])
solution([[1, 2, 3, 1, 4]])