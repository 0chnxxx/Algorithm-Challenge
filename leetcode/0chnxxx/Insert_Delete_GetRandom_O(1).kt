import kotlin.random.Random

/**
 * Implement the RandomizedSet class:
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 *
 * Constraints:
 * -2^31 <= val <= 2^31 - 1
 * At most 2 * 105^ calls will be made to insert, remove, and getRandom.
 * There will be at least one element in the data structure when getRandom is called.
 */

fun main() {
    val randomizedSet = RandomizedSet()

    println(randomizedSet.insert(1)) // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    println(randomizedSet.remove(2)) // Returns false as 2 does not exist in the set.
    println(randomizedSet.insert(2)) // Inserts 2 to the set, returns true. Set now contains [1,2].
    println(randomizedSet.getRandom()) // getRandom() should return either 1 or 2 randomly.
    println(randomizedSet.remove(1)) // Removes 1 from the set, returns true. Set now contains [2].
    println(randomizedSet.insert(2)) // 2 was already in the set, so return false.
    println(randomizedSet.getRandom()) // Since 2 is the only number in the set, getRandom() will always return 2.
}

class RandomizedSet() {
    // 값과 인덱스 매핑
    private val map = HashMap<Int, Int>()
    // 실제 값 저장
    private val list = ArrayList<Int>()

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun insert(`val`: Int): Boolean {
        if (map.containsKey(`val`)) return false

        map[`val`] = list.size

        list.add(`val`)

        return true
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun remove(`val`: Int): Boolean {
        val index = map[`val`] ?: return false
        val lastVal = list.last()

        // 마지막 값을 삭제 대상 자리로 옮김
        list[index] = lastVal
        map[lastVal] = index

        // 마지막 원소 제거
        list.removeAt(list.size - 1)
        map.remove(`val`)

        return true
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun getRandom(): Int {
        return list[Random.nextInt(list.size)]
    }
}

//class RandomizedSet() {
//    val set = mutableSetOf<Int>()
//
//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(1)
//    fun insert(`val`: Int): Boolean {
//        return set.add(`val`)
//    }
//
//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(1)
//    fun remove(`val`: Int): Boolean {
//        return set.remove(`val`)
//    }
//
//    // 시간 복잡도 : O(N)
//    // 공간 복잡도 : O(N)
//    fun getRandom(): Int {
//        return set.random()
//    }
//}