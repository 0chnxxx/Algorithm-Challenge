from bisect import bisect_left, bisect_right


def solution(nums, target):

    left = bisect_left(nums, target)

    if left == len(nums) or nums[left] != target:
        return [-1, -1]

    right = bisect_right(nums, target) - 1

    return [left, right]


def solution_2(nums, target):
    def binary_search(nums, target, is_searching_left):
        left, right, idx = 0, len(nums) - 1, -1

        while left <= right:
            mid = (left + right) // 2

            if nums[mid] > target:
                right = mid - 1
            elif nums[mid] < target:
                left = mid + 1
            else:
                idx = mid
                if is_searching_left:
                    right = mid - 1
                else:
                    left = mid + 1

        return idx

    left = binary_search(nums, target, True)
    right = binary_search(nums, target, False)

    return [left, right]