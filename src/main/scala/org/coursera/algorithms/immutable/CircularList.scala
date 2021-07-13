package org.coursera.algorithms.immutable

sealed abstract case class CircularList[+A] private (values: List[A], size: Int) {
  def +[B >: A](value: B): CircularList[B] =
    new CircularList(values = value :: values.take(size - 1), size) {}
}

object CircularList {
  def of[A](size: Int): CircularList[A] = new CircularList(List.empty[A], size) {}
}
