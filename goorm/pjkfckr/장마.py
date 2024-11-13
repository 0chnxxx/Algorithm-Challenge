

"""

1번 집부터, N번 집까지 총 N개의 집이 순서대로 있다.

딸 높이는 제각각이여서,  N[i] 번째 땅 높이는 K[i] 이다.
땅 높이의 기준은 해수면, 해수면보다 낮은 위치에 집이 있다면, 높이가 음수일 수 있다.


장마가 시작되면 M일 동안 계속온다.
장마 시작된지 M[i] 일이 되는 날에는 S[i]번 집이 있는 뒤치부터 e[i]번 집이 있는 위치까지 비가 내린다.

어떤 집에 비가 내리면 그 집에 쌓인 물의 높이가 1만큼 증가.

한번 쌓은 물은 배수 시스템이 작동하기 전까지 빠지지 않는다.

배수 시스템은 장마가 시작된 지 3의 배수가 되는 날마다, 비가 내리고 난 뒤 작동한다.
배수 시스템은 작동 날짜를 기준으로 2일 내에 비가 내린 위치에서만 작동

예)
장마 시작후 9일째에 동작하는 배수 시스템은 7, 8, 9일째에 비가 내린 위치에만 작동.

배수 시스템이 작동하면 그집에 쌓은 물의 높이가 1만큼 감소

장마가 끝난 뒤, 마을의 모든 땅 높이는 그 땅에 쌓였던 물의 높이만큼 증가

입력
첫째 줄에 집의 개수 N, 장마 기간 M
둘째 줄에는 말의 땅 높이 K[N]
다음 M개의 줄에는 s[i], e[i]가 공백을 두고 주어진다.

"""

N, M = map(int, input().split())
grounds = list(map(int, input().split()))
water_level = [0] * N # 물 높이
rainy = [tuple(map(int, input().split())) for _ in range(M)]
clear_list = set()

for i in range(M):
    s, e = rainy[i]

    for j in range(s-1, e):
        water_level[j] += 1
        clear_list.add(j)

    if (i+1) % 3 == 0:
        for j in clear_list:
            water_level[j] -= 1

        clear_list.clear()


result = [grounds[i] + water_level[i] for i in range(N)]
print(*result)



