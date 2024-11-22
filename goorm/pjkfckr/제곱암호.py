
"""

제곱암호
- 암호문의 길이가 N 이라면 N / 2 개의 숫자와 N / 2 개의 알파멧 소문자로 이루어져 있다. 항상 짝수
- 암호문의 첫 글자는 항상 알파벳 소문자, 이후 항상 숫자와 알파벳 소문자가 번갈아 가며 등장


복호화
- 원문은 처음에 비어있는 상태
- 암호분의 첫 번째 문자부터 순서대로 아래의 복호화 과정을 거친다. 첬 번째 문자는 문장의 가장 왼쪽 문자를 의미
- i가 홀수일 때, 암호문의 i번째 문자를 알파벳의 사전 기준 다음 문자로 바꾸는 작업을 암호의 i + 1 번째 수의 제곱번 시행한다.
작업이 끝난 뒤 변환된 알파벳을 원문의 맨 오른쪽에 추가한다.
- z에서 사전기준 다음 문자로 바꿔야하는 경우에는 a로 바뀌게 된다.
- 복호화가 끝난 뒤에 원문은 N / 2 길이의 알파벳 소문자로만 이루어진 문자열이다.

"""
import string

N = int(input())
secret_word = input()

word = ""

# ord 로 계산된 유니코드 값을 repeat 으로 알파벳 중 얼마나 이동할지 계산 후 chr 로 문자를 반환
def next_letter(char, repeat):
    return chr((ord(char) - ord('a') + repeat) % 26 + ord('a'))

# string 에서 소문자 알파벳 배열의 인덱스로 지정하여 가져오는 방법
def next_letter_string(char, repeat):
    alphabet = string.ascii_lowercase
    index = alphabet.index(char)
    new_index = (index + repeat) % 26
    return alphabet[new_index]


for i in range(0, N, 2):
    word += next_letter(secret_word[i], int(secret_word[i + 1]) ** 2)

print(word)

