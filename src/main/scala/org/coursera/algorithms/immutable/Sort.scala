package org.coursera.algorithms.immutable
import math.Ordering.Implicits._
import scala.annotation.tailrec

trait Sort {
  def apply[A: Ordering](list: List[A]): List[A]
}

object Sort {

  /**
    * Implementation of sorting using insertion sort O(1/4 N²)
    */
  def insertion: Sort =
    new Sort {
      override def apply[A: Ordering](list: List[A]): List[A] = {

        @tailrec
        def insert(entry: A, list: List[A])(k: List[A] => List[A]): List[A] =
          list match {
            case Nil                       => k(List(entry))
            case head :: _ if entry < head => k(entry :: list)
            case head :: tail              => insert(entry, tail)(r => k(head :: r))
          }

        @tailrec
        def inner(sorted: List[A], rest: List[A]): List[A] =
          rest match {
            case Nil          => sorted
            case head :: tail => inner(insert(head, sorted)(identity), tail)
          }

        inner(Nil, list)
      }
    }

  /**
    * Implementation of sorting using selection sort O(1/2 N²)
    */
  def selection: Sort =
    new Sort {
      override def apply[A: Ordering](list: List[A]): List[A] = {

        @tailrec
        def inner(sorted: List[A], rest: List[A]): List[A] =
          rest match {
            case Nil          => sorted
            case head :: tail =>
              // finds the 'max' while removing it from the 'rest' list. O(rest.size)
              val (rest, max) = tail.foldLeft((List.empty[A], head)) {
                case ((rest, max), c) if c > max => (max :: rest, c)
                case ((rest, max), c)            => (c :: rest, max)
              }
              inner(max :: sorted, rest)
          }

        inner(Nil, list)
      }
    }
}
