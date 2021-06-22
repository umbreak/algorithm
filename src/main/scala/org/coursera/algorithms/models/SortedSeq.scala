package org.coursera.algorithms.models

sealed abstract case class SortedSeq[A](values: IndexedSeq[A]) {
  def apply(idx: Int): A = values(idx)
}

object SortedSeq {
  def apply[A: Ordering](seq: IndexedSeq[A]): SortedSeq[A] =
    new SortedSeq[A](seq.sorted) {}
}
