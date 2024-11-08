"""

발표한 논문 n편중 h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최대값이 return

인용횟수를 담은 배열 citations

"""



def solution(citations):
    n = len(citations)
    reversed_sort = sorted(citations, reverse=True)

    # 정렬된 배열을 순회하면서 각 원소의 값과 현재 인덱스(+1)를 비교합니다.
    # 현재 인덱스 + 1이 현재 원소의 값보다 크거나 같아지는 지점이 H-Index가 될 수 있습니다.
    for i in range(n):
        if i + 1 > reversed_sort[i]:
            return i

    return len(citations)





citations = [3, 0, 6, 1, 5]

print(solution(citations))
