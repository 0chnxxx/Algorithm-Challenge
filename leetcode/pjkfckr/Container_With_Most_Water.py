"""
주어진 정수 배열 height의 길이는 n입니다. 두 개의 수직선이 그려져 있으며, i번째 선의 두 끝점은 (i, 0)과 (i, height[i])입니다.
x축과 함께 컨테이너를 형성하는 두 개의 선을 찾아야 하며, 이 컨테이너가 가장 많은 물을 담을 수 있도록 해야 합니다.
컨테이너가 담을 수 있는 최대 물의 양을 반환하세요.
컨테이너를 기울일 수 없다는 점에 유의하세요.

"""

height = [1, 1]

left, right = 0, len(height) - 1
max_area = 0

while left < right:
    width = right - left
    container_height = min(height[left], height[right])
    area = width * container_height
    max_area = max(max_area, area)

    if height[left] < height[right]:
        left += 1
    else:
        right -= 1

print(max_area)