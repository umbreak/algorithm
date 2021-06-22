package org.coursera.algorithms.immutable

trait Queue[+A] {
  def dequeue: Queue[A]
  def enqueue[U >: A](value: U): Queue[U]
  def head: Option[A]
  def isEmpty: Boolean
}

object Queue {
  def apply[A](): Queue[A] = new QueueImpl(List.empty, List.empty)

  final private class QueueImpl[+A](private[this] var leading: List[A], private[this] var trailing: List[A])
      extends Queue[A] {
    private def mirror(): Unit =
      if (leading.isEmpty && trailing.nonEmpty) {
        leading = trailing.reverse
        trailing = List.empty
      }

    override def dequeue: Queue[A] =
      if (isEmpty) this
      else {
        mirror()
        new QueueImpl(leading.tail, trailing)
      }

    override def enqueue[U >: A](value: U): Queue[U] = new QueueImpl(leading, value :: trailing)

    override def head: Option[A] = {
      mirror()
      leading.headOption
    }

    override def isEmpty: Boolean = leading.isEmpty && trailing.isEmpty
  }
}
