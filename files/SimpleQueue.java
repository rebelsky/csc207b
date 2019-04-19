import java.util.NoSuchElementException;

/**
 * A simple (and not thoroughly tested) implementation of
 * queues.
 */
public class SimpleQueue<T> {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The front of the queue.  Set to null if the queue is empty.
   */
  QNode<T> front;

  /**
   * The back of the queue.  Set to null if the queue is empty.
   */
  QNode<T> back;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new queue.
   */
  public SimpleQueue() {
    this.front = null;
    this.back = null;
  } // SimpleQueue

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Remove an element from the front of the queue.
   *
   * @throws NoSuchElementException if there is no element in the queue
   */
  public T dequeue() {
    if (this.front == null) {
      throw new NoSuchElementException("Empty queue");
    }
    T value = this.front.value;
    this.front = this.front.next;
    if (this.front == null) {
      this.back = null;
    } // if
    return value;
  } // dequeue()

  /**
   * Add an element to the queue.
   */
  public void enqueue(T value) {
    QNode<T> newNode = new QNode<T>(value);
    if (this.front == null) {
      this.front = newNode;
      this.back = newNode;
    } else {
      this.back.next = newNode;
      this.back = newNode;
    } // if/else
  } // enqueue(T)

  /**
   * Look at the element in the front of the queue, but do not
   * remove it.
   *
   * @throws NoSuchElementException if there is no element in the queue
   */
  public T front() {
    if (this.front == null) {
      throw new NoSuchElementException("Empty queue");
    }
    return this.front.value;
  } // front

  /**
   * Determine if the queue is empty.
   */
  boolean isEmpty() {
    return this.front == null;
  } // isEmpty(0

} // class SimpleQueue


/**
 * Nodes for our simple queues.
 */
class QNode<T> {
  /**
   * The value in the node.
   */
  T value;

  /**
   * The next node in the queue.
   */
  QNode<T> next;

  /**
   * Create a new node with no next node.
   */
  public QNode(T value) {
    this.value = value;
    this.next = null;
  } // QNode(T)
} // QNode<T>
