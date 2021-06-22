package org.coursera.algorithms.mutable

trait Queue[A] {
  def enqueue(value: A): Unit
  def dequeue: Option[A]
  def isEmpty: Boolean
}

object Queue {
  final private case class Node[A](value: A, var next: Option[Node[A]])

  def apply[A](): Queue[A] =
    new Queue[A] {
      var first: Option[Node[A]] = None
      var last: Option[Node[A]]  = None

      override def enqueue(value: A): Unit = {
        val oldLast = last
        last = Some(Node(value, None))
        oldLast.foreach(_.next = last)
        first = first orElse last
        ()
      }

      override def dequeue: Option[A] = {
        val oldFirst = first
        first = oldFirst.flatMap(_.next)
        if (isEmpty) last = first
        oldFirst.map(_.value)
      }

      override def isEmpty: Boolean = first.isEmpty
    }
}
