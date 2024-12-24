"""
m x n 정수 행렬 matrix에서 target 값을 검색하는 효율적인 알고리즘을 작성하세요. 이 행렬은 다음과 같은 특성을 가지고 있습니다:
각 행의 정수들은 왼쪽에서 오른쪽으로 오름차순으로 정렬되어 있습니다.
각 열의 정수들은 위에서 아래로 오름차순으로 정렬되어 있습니다.

"""
import bisect
from typing import List

matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]

target = 5

# O(m + n) O(rows, cols)
def search_matrix(m: List[List[int]], t: int) -> bool:
    if not m or not m[0]:
        return False

    rows, cols = len(m), len(m[0])
    row, col = 0, cols - 1

    while row < rows and col >= 0:
        if m[row][col] == target:
            return True
        elif m[row][col] > target:
            col -= 1
        else:
            row += 1

    return False

# O(m * log(n))
def binary_search(matrix: List[List[int]], target: int) -> bool:
    for row in matrix:
        index = bisect.bisect_left(row, target)
        if index < len(row) and row[index] == target:
            return True
    return False

print(search_matrix(matrix, target))
print(binary_search(matrix, target))



