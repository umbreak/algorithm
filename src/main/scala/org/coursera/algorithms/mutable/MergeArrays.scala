package org.coursera.algorithms.mutable

import scala.annotation.tailrec
import scala.math.Ordering.Implicits._

final class MergeArrays[A: Ordering](arrayA: Array[A], arrayB: Array[A]) {

  def apply(): Array[A] = {
    appendEnd(arrayA.length - arrayB.length - 1, arrayB.length - 1, arrayA.length - 1)
    arrayA
  }

  @tailrec
  def appendEnd(idxA: Int, idxB: Int, end: Int): Unit =
    (fetch(idxB, arrayB), fetch(idxA, arrayA)) match {
      case (Some(valueB), maybeValueA) if maybeValueA.forall(valueB > _) =>
        arrayA(end) = valueB
        appendEnd(idxA, idxB - 1, end - 1)
      case (maybeValueB, Some(valueA)) if maybeValueB.forall(valueA > _) =>
        arrayA(end) = valueA
        appendEnd(idxA - 1, idxB, end - 1)
      case _ => ()
    }

  private def fetch(idx: Int, array: Array[A]): Option[A] =
    Option.when(idx > -1)(array(idx))
}

object MergeArrays {
  def apply[A: Ordering](arrayA: Array[A], arrayB: Array[A]): Array[A] =
    new MergeArrays(arrayA, arrayB).apply()
}
