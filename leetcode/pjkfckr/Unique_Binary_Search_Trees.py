

n = 3

def solution(n: int):
    dp = [0] * (n + 1)
    dp[0] = dp[1] = 1

    """
    각 숫자에 대해, 
    왼쪽 서브트리의 가능한 구조 수와 
    오른쪽 서브트리의 가능한 구조 수를 곱하여 전체 구조 수를 계산합니다.
    
    """
    for i in range(2, n + 1):
        for j in range(1, i + 1):
            dp[i] += dp[j - 1] * dp[i - j]

    return dp[n]