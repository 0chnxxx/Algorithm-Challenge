/**
 * Design an iterator that supports the peek operation on an existing iterator in addition to the hasNext and the next operations.
 *
 * Implement the PeekingIterator class:
 * PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator iterator.
 * int next() Returns the next element in the array and moves the pointer to the next element.
 * boolean hasNext() Returns true if there are still elements in the array.
 * int peek() Returns the next element in the array without moving the pointer.
 * Note: Each language may have a different implementation of the constructor and Iterator, but they all support the int next() and boolean hasNext() functions.
 *
 * Constraints:
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * All the calls to next and peek are valid.
 * At most 1000 calls will be made to next, hasNext, and peek.
 *
 * Follow up:
 * How would you extend your design to be generic and work with all types, not just integer?
 */

fun main() {
    val nums = arrayOf("PeekingIterator", "next", "peek", "next", "next", "hasNext")
    val commands = arrayOf(
        arrayOf(1, 2, 3), // PeekingIterator 초기화 값
        emptyArray<Int>(), // next
        emptyArray(),      // peek
        emptyArray(),      // next
        emptyArray(),      // next
        emptyArray()       // hasNext
    )

    val results = mutableListOf<Any?>()
    var peekingIterator: PeekingIterator? = null

    for (i in nums.indices) {
        when (nums[i]) {
            "PeekingIterator" -> {
                peekingIterator = PeekingIterator(commands[i].toIntArray().iterator())
                results.add(null) // 생성자는 결과가 null
            }
            "next" -> results.add(peekingIterator!!.next())
            "peek" -> results.add(peekingIterator!!.peek())
            "hasNext" -> results.add(peekingIterator!!.hasNext())
        }
    }

    println("Output: $results")
}

class PeekingIterator(iterator:Iterator<Int>): Iterator<Int> {
    private val iter: Iterator<Int> = iterator
    private var nextElement: Int? = null
    private var hasNextElement: Boolean = false

    init {
        if (iter.hasNext()) {
            nextElement = iter.next()
            hasNextElement = true
        }
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun peek(): Int {
        if (!hasNextElement) throw NoSuchElementException()

        return nextElement!!
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    override fun next(): Int {
        if (!hasNextElement) throw NoSuchElementException()

        val result = nextElement!!

        if (iter.hasNext()) {
            nextElement = iter.next()
            hasNextElement = true
        } else {
            nextElement = null
            hasNextElement = false
        }

        return result
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    override fun hasNext(): Boolean {
        return hasNextElement
    }
}
