package org.coursera.algorithms.mutable

import scala.annotation.tailrec
import scala.math.Ordering.Implicits._

trait Sort {
  def apply[A: Ordering](arr: Array[A]): Unit
}

object Sort {

  def insertion: Sort =
    new Sort {

      override def apply[A: Ordering](values: Array[A]): Unit = {

        @tailrec
        def swapIfSmaller(pos: Int): Unit = {
          if (values(pos) <= values(pos + 1))
            ()
          else if (pos == 0)
            swap(values, pos, pos + 1)
          else {
            swap(values, pos, pos + 1)
            swapIfSmaller(pos - 1)
          }
        }

        values.indices.dropRight(1).foreach { swapIfSmaller }
      }
    }

  def selection: Sort =
    new Sort {
      override def apply[A: Ordering](values: Array[A]): Unit =
        values.indices.foreach { i =>
          val min = (i until values.length).foldLeft(i) { (min, j) =>
            if (values(j) < values(min)) j
            else min
          }
          swap(values, i, min)
        }
    }

  private def swap[A](array: Array[A], aPos: Int, bPos: Int): Unit = {
    val b = array(bPos)
    array(bPos) = array(aPos)
    array(aPos) = b
  }
}
