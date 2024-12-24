"""

주어진 것은 길이 n의 0-indexed 정수 배열 nums입니다. 당신은 처음에 nums에 위치해 있습니다.
각 요소 nums[i]는 인덱스 i에서 앞으로 점프할 수 있는 최대 길이를 나타냅니다. 즉, nums[i]에 있을 때, 다음과 같이 nums[i + j]로 점프할 수 있습니다:
0 <= j <= nums[i] 이고
i + j < n
nums[n - 1]에 도달하기 위한 최소 점프 수를 반환하세요. 테스트 케이스는 nums[n - 1]에 도달할 수 있도록 생성됩니다.

"""
nums = [2,3,1,1,4]

def jump(nums_arr):
    n = len(nums)
    if n <= 1:
        return 0

    dp = [float('inf')] * n
    dp[0] = 0 # 시작 지점 점프 수 0

    for i in range(n):
        # nums[i] 만큼 점프 가능
        for j in range(1, nums_arr[i] + 1):
            # 배열의 범위 초과 여부
            if i + j < n:
                dp[i + j] = min(dp[i + j], dp[i] + 1)

    return dp[n - 1]

print(jump(nums))

