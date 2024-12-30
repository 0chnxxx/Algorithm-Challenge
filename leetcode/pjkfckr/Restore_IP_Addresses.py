"""


"""

s = "101023"

def resolve_addr(addr):
    def is_valid(segment):
        """
        세그먼트 유효성 검사
        길이가 0이거나 3을 초과하면 유효하지 않는다. ex (1001.10.1.10)
        "0" 으로 시작하느 ㄴ경우 길이가 1이어야 한다. ex (01.10.1.10)
        정수값이 0에서 255 사이여야 한다. ex(255.255.270.255)
        """
        if len(segment) > 3 or len(segment) == 0:
            return False

        if segment[0] == "0" and len(segment) > 1:
            return False
        return 0 <= int(segment) <= 255


    result = []
    n = len(addr)
    if n < 4 or n > 12:
        return result

    # 각 조합에 대해 4개의 세그먼트를 나누기 위해 3중 반복문을 사용
    # 조합하여 나올 수 있는 IP가 유효하다면 배열에 담고 return
    for i in range(1, min(4, n - 2)):
        for j in range(i + 1, min(i + 4, n - 1)):
            for k in range(j + 1, min(j + 4, n)):
                seg1, seg2, seg3, seg4 = s[:i], s[i:j], s[j:k], s[k:]

                if all(is_valid(seg) for seg in (seg1, seg2, seg3, seg4)):
                    result.append(
                        f"{seg1}.{seg2}.{seg3}.{seg4}"
                    )

    return result

print(resolve_addr(s))