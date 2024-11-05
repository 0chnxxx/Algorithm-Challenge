/**
 * @param {string}
 * @return {number}
 */


function chunk(data = [], size = 1) {
  const arr = [];

  for (let i = 0; i < data.length; i += size) {
    arr.push(data.slice(i, i + size));
  }

  return arr;
}

var minChanges = function(s) {
    let SPLIT_COUNT = 2;

    const arr = chunk(s, SPLIT_COUNT);

    return arr.reduce((acc, cur)=> {
        const zero = [...cur].filter( a => a === '0').length;
        const one = cur.length - zero;

        return acc + (zero >= one ? one : zero);
    }, 0);
};
// Example
// Input: s = "1001"
// Output: 2
// Explanation: We change s[1] to 1 and s[3] to 0 to get string "1100".
//     It can be seen that the string "1100" is beautiful because we can partition it into "11|00".
//     It can be proven that 2 is the minimum number of changes needed to make the string beautiful.
//     Example 2:
//
// Input: s = "10"
// Output: 1
// Explanation: We change s[1] to 1 to get string "11".
//     It can be seen that the string "11" is beautiful because we can partition it into "11".
//     It can be proven that 1 is the minimum number of changes needed to make the string beautiful.
//     Example 3:
//
// Input: s = "0000"
// Output: 0
// Explanation: We don't need to make any changes as the string "0000" is beautiful already.
