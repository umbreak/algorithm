package org.coursera.algorithms

import org.coursera.algorithms.models.SortedSeq

import scala.annotation.tailrec
import scala.math.Ordering.Implicits._

trait Search[A] {
  def indexOf(seq: SortedSeq[A])(value: A): Option[Int]
}

object Search {
  def binarySearch[A: Ordering]: Search[A] =
    new Search[A] {
      override def indexOf(seq: SortedSeq[A])(value: A): Option[Int] = {

        @tailrec
        def inner(lo: Int, hi: Int): Option[Int] = {
          if (lo > hi) None
          else {
            val mid = lo + (hi - lo) / 2
            if (seq.values(mid) > value) inner(lo, mid - 1)
            else if (seq.values(mid) < value) inner(mid + 1, hi)
            else Some(mid)
          }
        }
        inner(0, seq.values.size - 1)
      }
    }
}
