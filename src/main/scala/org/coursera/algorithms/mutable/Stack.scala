package org.coursera.algorithms.mutable

trait Stack[A] {
  def pop(): Option[A]
  def push(value: A): Unit
  def isEmpty: Boolean
}

object Stack {
  final private case class Node[A](value: A, next: Option[Node[A]])

  def apply[A](): Stack[A] =
    new Stack[A] {
      var current: Option[Node[A]] = None

      override def pop(): Option[A] = {
        val oldCurrent = current
        current = oldCurrent.flatMap(_.next)
        oldCurrent.map(_.value)
      }

      override def push(value: A): Unit = {
        current = Some(Node(value, current))
        ()
      }

      override def isEmpty: Boolean = current.isEmpty
    }
}
