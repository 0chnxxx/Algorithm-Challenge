
"""
자신의 이름에서 최대 한 글자를 지워서 개명 신청하면 그 이름으로 변경 가능.

"""

name = input().rstrip()
n = len(name)

# 제거할 문자의 인덱스를 찾습니다
remove_index = -1
for i in range(n - 1):
    if name[i] > name[i + 1]:
        remove_index = i
        break

# 만약 제거할 문자를 찾지 못했다면, 마지막 문자를 제거합니다
if remove_index == -1:
    remove_index = n - 1

# 선택된 문자를 제거하고 결과를 출력합니다
result = name[:remove_index] + name[remove_index + 1:]
print(result)