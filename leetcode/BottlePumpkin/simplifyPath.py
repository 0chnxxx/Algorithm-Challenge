class Solution:
    def simplifyPath(self, path: str) -> str:
        arr = list(path.split('/'))
        check = []
        for i in arr:
            print(i)
            check.append(i)
 

            if i == '' or i == '.':
                check.pop()
                continue

            if i == '..':
                    check.pop()
                    if check:
                          check.pop()

        return "/" + "/".join(check)