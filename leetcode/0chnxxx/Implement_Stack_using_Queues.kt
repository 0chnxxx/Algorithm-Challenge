import java.util.LinkedList
import java.util.Queue

/**
 * Implement a last-in-first-out (LIFO) stack using only two queues.
 * The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
 *
 * Implement the MyStack class:
 * void push(int x) Pushes element x to the top of the stack.
 * int pop() Removes the element on the top of the stack and returns it.
 * int top() Returns the element on the top of the stack.
 * boolean empty() Returns true if the stack is empty, false otherwise.
 *
 * Notes:
 * You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
 * Depending on your language, the queue may not be supported natively.
 * You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
 *
 * Constraints:
 * 1 <= x <= 9
 * At most 100 calls will be made to push, pop, top, and empty.
 * All the calls to pop and top are valid.
 *
 * Follow-up:
 * Can you implement the stack using only one queue?
 */

fun main() {
    val myStack = MyStack()

    myStack.push(1)
    myStack.push(2)
    myStack.top() // return 2
    myStack.pop() // return 2
    myStack.empty() // return False
}

class MyStack() {
    private var queue1: Queue<Int> = LinkedList()
    private var queue2: Queue<Int> = LinkedList()

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(N)
    fun push(x: Int) {
        queue2.add(x)

        while (queue1.isNotEmpty()) {
            queue2.add(queue1.remove())
        }

        val temp = queue1
        queue1 = queue2
        queue2 = temp
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun pop(): Int {
        return queue1.remove()
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun top(): Int {
        return queue1.peek()
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun empty(): Boolean {
        return queue1.isEmpty()
    }
}
