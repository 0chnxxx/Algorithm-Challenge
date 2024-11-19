// 1. index 1 부터 k 개씩 슬라이스 한다.
// 2. 조건에 맞는지 확인한다
// All the elements of the subarray are distinct.
// 3. 조건에 맞다면 합을 구하고 배열에 넣는다.
// 4. 배열의 최댓값을 반환한다.

// 실패

/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maximumSubarraySum = function (nums, k) {
  const res = [];

  const isDistinctArr = (nums) => {
    return new Set(nums).size === nums.length;
  };

  const sumOfArr = (nums) => {
    return nums.reduce((acc, cur) => acc + cur, 0);
  };

  if (nums.length === k)
    for (let i = 0; i <= nums.length / 2 - k; i++) {
      const sliceArr = nums.slice(i, i + k);

      if (isDistinctArr(sliceArr)) {
        res.push(sumOfArr(sliceArr));
      }
    }

  for (let i = nums.length; i > nums.length / 2 - k; i--) {
    const sliceArr = nums.slice(i - k, i);

    if (isDistinctArr(sliceArr)) {
      res.push(sumOfArr(sliceArr));
    }
  }

  return res.length > 0 ? Math.max(...res) : 0;
};

// 슬라이딩 윈도우 방법을 사용한 풀이

/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maximumSubarraySum = function (nums, k) {
  let maxSum = 0;
  let currentSum = 0;
  const seen = new Map();

  // 첫 번째 윈도우 초기화
  for (let i = 0; i < k; i++) {
    currentSum += nums[i];
    seen.set(nums[i], (seen.get(nums[i]) || 0) + 1);
  }

  // 첫 번째 윈도우가 유효한지 확인
  if (seen.size === k) {
    maxSum = currentSum;
  }

  // 윈도우 슬라이딩
  for (let i = k; i < nums.length; i++) {
    // 이전 윈도우의 첫 번째 요소 제거
    currentSum -= nums[i - k];
    seen.set(nums[i - k], seen.get(nums[i - k]) - 1);
    if (seen.get(nums[i - k]) === 0) {
      seen.delete(nums[i - k]);
    }

    // 새 요소 추가
    currentSum += nums[i];
    seen.set(nums[i], (seen.get(nums[i]) || 0) + 1);

    // 현재 윈도우가 유효한지 확인
    if (seen.size === k) {
      maxSum = Math.max(maxSum, currentSum);
    }
  }

  return maxSum;
};
