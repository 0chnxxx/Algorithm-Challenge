def stair(n):

    stair_scores = [int(input()) for _ in range(n)]

    dp = [0] * n
    dp[0] = stair_scores[0] # 첫번째 계단
    dp[1] = stair_scores[0] + stair_scores[1] # 첫번째와 두번째의 합
    dp[2] = max(stair_scores[1] + stair_scores[2], stair_scores[0] + stair_scores[2]) # 두번째와 세번째의 합 (한계단) 첫번째와 세번째의 합 (두계단)

    for i in range(3, n):
        dp[i] = max(dp[i - 2] + stair_scores[i], dp[i - 3] + stair_scores[i - 1] + stair_scores[i])
        print(dp)

    return dp[-1]



N = int(input())
print(stair(N))