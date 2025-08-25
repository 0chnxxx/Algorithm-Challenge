import java.util.Stack

/**
 * Implement a first in first out (FIFO) queue using only two stacks.
 * The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 *
 * Implement the MyQueue class:
 * void push(int x) Pushes element x to the back of the queue.
 * int pop() Removes the element from the front of the queue and returns it.
 * int peek() Returns the element at the front of the queue.
 * boolean empty() Returns true if the queue is empty, false otherwise.
 *
 * Notes:
 * You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 *
 * Constraints:
 * 1 <= x <= 9
 * At most 100 calls will be made to push, pop, peek, and empty.
 * All the calls to pop and peek are valid.
 *
 * Follow-up:
 * Can you implement the queue such that each operation is amortized O(1) time complexity?
 * In other words, performing n operations will take overall O(n) time even if one of those operations may take longer.
 */

fun main() {
    val myQueue = MyQueue();
    myQueue.push(1) // queue is: [1]
    myQueue.push(2) // queue is: [1, 2] (leftmost is front of the queue)
    myQueue.peek() // return 1
    myQueue.pop() // return 1, queue is [2]
    myQueue.empty() // return false
}

class MyQueue() {
    val inStack = Stack<Int>()
    val outStack = Stack<Int>()

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun push(x: Int) {
        inStack.push(x)
    }

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun pop(): Int {
        shiftStacks()
        return outStack.pop()
    }

    // 시간 복잡도 : O(N)
    // 공간 복잡도 : O(1)
    fun peek(): Int {
        shiftStacks()
        return outStack.peek()
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun empty(): Boolean {
        return inStack.isEmpty && outStack.isEmpty()
    }

    private fun shiftStacks() {
        if (outStack.isEmpty()) {
            while (inStack.isNotEmpty()) {
                outStack.push(inStack.pop())
            }
        }
    }
}
