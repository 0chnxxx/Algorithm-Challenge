/**
 * Design your implementation of the circular queue.
 * The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle, and the last position is connected back to the first position to make a circle.
 * It is also called "Ring Buffer".
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue.
 * In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue.
 * But using the circular queue, we can use the space to store new values.
 *
 * Implement the MyCircularQueue class:
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 * You must solve the problem without using the built-in queue data structure in your programming language.
 *
 * Constraints:
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull.
 */

fun main() {
    val myCircularQueue = MyCircularQueue(3)

    println(myCircularQueue.enQueue(1))
    println(myCircularQueue.enQueue(2))
    println(myCircularQueue.enQueue(3))
    println(myCircularQueue.enQueue(4))
    println(myCircularQueue.Rear())
    println(myCircularQueue.isFull())
    println(myCircularQueue.deQueue())
    println(myCircularQueue.enQueue(4))
    println(myCircularQueue.Rear())
}

class MyCircularQueue(k: Int) {
    private val queue = IntArray(k)
    private val capacity = k
    private var head = 0
    private var tail = 0
    private var size = 0

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(K)
    fun enQueue(value: Int): Boolean {
        if (isFull()) return false

        queue[tail] = value
        tail = (tail + 1) % capacity
        size++
        return true
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(K)
    fun deQueue(): Boolean {
        if (isEmpty()) return false

        head = (head + 1) % capacity
        size--
        return true
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(K)
    fun Front(): Int {
        if (isEmpty()) return -1
        return queue[head]
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(K)
    fun Rear(): Int {
        if (isEmpty()) return -1
        return queue[(tail - 1 + capacity) % capacity]
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(K)
    fun isEmpty(): Boolean {
        return size == 0
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(K)
    fun isFull(): Boolean {
        return size == capacity
    }
}