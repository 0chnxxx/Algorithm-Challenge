/**
 * Design your implementation of the circular double-ended queue (deque).
 *
 * Implement the MyCircularDeque class:
 * MyCircularDeque(int k) Initializes the deque with a maximum size of k.
 * boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
 * int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
 * int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
 * boolean isEmpty() Returns true if the deque is empty, or false otherwise.
 * boolean isFull() Returns true if the deque is full, or false otherwise.
 *
 * Constraints:
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull.
 */

fun main() {
    val myCircularDeque = MyCircularDeque(3)

    println(myCircularDeque.insertLast(1)) // return True
    println(myCircularDeque.insertLast(2)) // return True
    println(myCircularDeque.insertFront(3)) // return True
    println(myCircularDeque.insertFront(4)) // return False, the queue is full.
    println(myCircularDeque.getRear()) // return 2
    println(myCircularDeque.isFull()) // return True
    println(myCircularDeque.deleteLast()) // return True
    println(myCircularDeque.insertFront(4)) // return True
    println(myCircularDeque.getFront()) // return 4
}

class MyCircularDeque(k: Int) {
    private val size = k + 1
    private val deque = IntArray(size)
    private var front = 0
    private var rear = 0

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun insertFront(value: Int): Boolean {
        if (isFull()) return false

        front = (front - 1 + size) % size
        deque[front] = value
        return true
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun insertLast(value: Int): Boolean {
        if (isFull()) return false

        deque[rear] = value
        rear = (rear + 1) % size
        return true
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun deleteFront(): Boolean {
        if (isEmpty()) return false

        front = (front + 1) % size
        return true
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun deleteLast(): Boolean {
        if (isEmpty()) return false

        rear = (rear - 1 + size) % size
        return true
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun getFront(): Int {
        return if (isEmpty()) -1 else deque[front]
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun getRear(): Int {
        return if (isEmpty()) -1 else deque[(rear - 1 + size) % size]
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun isEmpty(): Boolean {
        return front == rear
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun isFull(): Boolean {
        return (rear + 1) % size == front
    }
}
