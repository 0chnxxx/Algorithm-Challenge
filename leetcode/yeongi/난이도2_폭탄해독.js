/**
 * @param {number[]} code
 * @param {number} k
 * @return {number[]}
 */
var decrypt = function (code, k) {
  if (k === 0) {
    return code.map(() => 0);
  }

  const n = code.length;
  return code.map((_, index) => {
    let sum = 0;
    if (k > 0) {
      for (let i = 1; i <= k; i++) {
        sum += code[(index + i) % n];
      }
    } else {
      for (let i = 1; i <= -k; i++) {
        sum += code[(index - i + n) % n];
      }
    }
    return sum;
  });
};
