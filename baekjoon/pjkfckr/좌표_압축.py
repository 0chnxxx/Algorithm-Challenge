


def solution():
    N = int(input())

    location = list(map(int, input().split()))
    sorted_location = sorted(set(location))

    mapper = {}
    for idx, val in enumerate(sorted_location):
        mapper[val] = idx

    ans = [0] * N

    for idx, val in enumerate(location):
        ans[idx] = mapper[val]

    print(*ans)
solution()