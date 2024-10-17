from collections import namedtuple

class Solution:
    def simplifyPath(self, path: str) -> str:
        paths = path.split('/')
        stack = []

        for command in paths:
            if not command or command == '.':
                continue
            elif command == "..":
                if stack:
                    stack.pop()
            else:
                stack.append(command)



        return "/" + "/".join(stack)
sol = Solution()

sol.simplifyPath("/home/")
sol.simplifyPath("/home//foo/")
sol.simplifyPath("/home/user/Documents/../Pictures")
sol.simplifyPath("/../")
sol.simplifyPath("/.../a/../b/c/../d/./")