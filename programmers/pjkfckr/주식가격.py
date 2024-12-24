"""

초 단위로 기록된 주식가격이 담긴 배열 prices 가 매개변수로 주어질 떄,
가격이 떨이지지 않은 기간은 몇 초인지를 return


"""

prices = [1, 2, 3, 2, 3]

def solution(prices):
    n = len(prices)
    answer = [0] * n
    for i in range(n):
        for j in range(i + 1, n):
            answer[i] += 1
            if prices[i] > prices[j]:
                break

    return answer

solution(prices)

