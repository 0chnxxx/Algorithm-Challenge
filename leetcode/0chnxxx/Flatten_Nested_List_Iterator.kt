/**
 * You are given a nested list of integers nestedList.
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 * Implement an iterator to flatten it.
 *
 * Implement the NestedIterator class:
 * NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * int next() Returns the next integer in the nested list.
 * boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 *
 * Your code will be tested with the following pseudocode:
 * initialize iterator with nestedList
 * res = []
 * while iterator.hasNext()
 *     append iterator.next() to the end of res
 * return res
 *
 * If res matches the expected flattened list, then your code will be judged as correct.
 *
 * Constraints:
 * 1 <= nestedList.length <= 500
 * The values of the integers in the nested list is in the range [-10^6, 10^6].
 */

fun main() {
    val list1 = NestedInteger()
    list1.add(NestedInteger(1))
    list1.add(NestedInteger(1))

    val list2 = NestedInteger()
    list2.add(NestedInteger(1))
    list2.add(NestedInteger(1))

    val nestedList = listOf(
        list1,
        NestedInteger(2),
        list2
    )

    val iterator = NestedIterator(nestedList)

    val result = mutableListOf<Int>()

    while (iterator.hasNext()) {
        result.add(iterator.next())
    }

    println(result)
}

class NestedInteger {
    private var value: Int? = null
    private var list: MutableList<NestedInteger>? = null

    // Constructor initializes an empty nested list.
    constructor()

    // Constructor initializes a single integer.
    constructor(value: Int) {
        this.value = value
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    fun isInteger(): Boolean = value != null

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    fun getInteger(): Int? = value

    // Set this NestedInteger to hold a single integer.
    fun setInteger(value: Int) {
        this.value = value
        this.list = null
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    fun add(ni: NestedInteger) {
        if (list == null) {
            list = mutableListOf()
        }

        list!!.add(ni)
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    fun getList(): List<NestedInteger>? = list
}

class NestedIterator(nestedList: List<NestedInteger>) {
    private val stack = ArrayDeque<NestedInteger>()

    init {
        push(nestedList)
    }

    fun push(nestedList: List<NestedInteger>) {
        for (i in nestedList.indices.reversed()) {
            stack.addFirst(nestedList[i])
        }
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun next(): Int {
        return stack.removeFirst().getInteger()!!
    }

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(N)
    fun hasNext(): Boolean {
        while (stack.isNotEmpty()) {
            val top = stack.first()

            if (top.isInteger()) return true

            stack.removeFirst()
            push(top.getList()!!)
        }

        return false
    }
}
