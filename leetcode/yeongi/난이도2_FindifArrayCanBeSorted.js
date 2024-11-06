/**
 * @param {number[]} nums
 * @return {boolean}
 */

// 3011. Find if Array Can Be Sorted

const returnOneCountInStr = (str) => {
    return [...str].reduce((acc, cur) => {
        if (cur === '1') return acc + 1
        return acc
    }, 0)
}

const covertNumToBinary = (num) => {
    return num.toString(2)
}

const isAscending = (arr) => {
    for (let i = 0; i < arr.length - 1; i++) {
        if (arr[i] > arr[i + 1]) {
            return false
        }
    }
    return true
}

const canSortArray = function (nums) {

    if(isAscending(nums)) return true;

    const binaryArr = nums
        .map((n) => covertNumToBinary(n))
        .sort((a, b) => {
            if (
                returnOneCountInStr(covertNumToBinary(a)) ===
                returnOneCountInStr(covertNumToBinary(b))
            ) {
                return a - b
            }
        })

    return isAscending(binaryArr.map(n => Number(n)))
}
