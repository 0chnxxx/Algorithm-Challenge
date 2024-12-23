
"""

로봇이 m x n 그리드에 있습니다. 로봇은 처음에 왼쪽 상단 모서리(즉, grid)에 위치해 있습니다. 로봇은 오른쪽 하단 모서리(즉, grid[m - 1][n - 1])로 이동하려고 합니다. 로봇은 언제든지 아래쪽 또는 오른쪽으로만 이동할 수 있습니다.
두 정수 m과 n이 주어지면, 로봇이 오른쪽 하단 모서리에 도달하기 위해 취할 수 있는 가능한 고유 경로의 수를 반환합니다.
테스트 케이스는 답이 2 * 10^9 이하가 되도록 생성됩니다.

"""

m, n = 3, 7
dp = [[1] * n for _ in range(m)]


for i in range(1, m):
    for j in range(1, n):
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1]

print(dp[m-1][n-1])