package org.coursera.algorithms.immutable

trait Stack[+A] {
  def pop(): Option[(A, Stack[A])]
  def push[U >: A](value: U): Stack[U]
  def isEmpty: Boolean
}

object Stack {
  def apply[A](list: List[A] = List.empty): Stack[A] =
    new Stack[A] {
      override def pop(): Option[(A, Stack[A])] =
        list match {
          case head :: tail => Some(head -> Stack(tail))
          case Nil          => None
        }

      override def push[U >: A](value: U): Stack[U] =
        Stack(value :: list)

      override def isEmpty: Boolean = list.isEmpty
    }
}
