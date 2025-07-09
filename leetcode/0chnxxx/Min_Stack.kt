import java.util.LinkedList

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 *
 * You must implement a solution with O(1) time complexity for each function.
 *
 * Constraints:
 * -2^31 <= val <= 2^31 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
 */

fun main() {
    val minStack = MinStack()

    println(minStack.push(-2))
    println(minStack.push(0))
    println(minStack.push(-3))
    println(minStack.getMin())
    println(minStack.pop())
    println(minStack.top())
    println(minStack.getMin())
}

// 시간 복잡도 : O(1)
// 공간 복잡도 : O(1)
class MinStack() {
    private val stack = LinkedList<Int>()
    private val minStack = LinkedList<Int>()

    fun push(`val`: Int) {
        stack.push(`val`)

        if (minStack.isEmpty() || `val` <= minStack.peek()) {
            minStack.push(`val`)
        }
    }

    fun pop() {
        val popped = stack.pop()

        if (popped == minStack.peek()) {
            minStack.pop()
        }
    }

    fun top(): Int {
        return stack.peek()
    }

    fun getMin(): Int {
        return minStack.peek()
    }
}

//// 시간 복잡도 : O(N)
//// 공간 복잡도 : O(1)
//class MinStack() {
//    private val stack = LinkedList<Int>()
//
//    fun push(`val`: Int) {
//        stack.push(`val`)
//    }
//
//    fun pop() {
//        stack.pop()
//    }
//
//    fun top(): Int {
//        return stack.peek()
//    }
//
//    fun getMin(): Int {
//        return stack.min()
//    }
//}