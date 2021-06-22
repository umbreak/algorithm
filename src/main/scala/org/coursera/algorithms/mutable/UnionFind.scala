package org.coursera.algorithms.mutable

import scala.annotation.tailrec

trait UnionFind[A] {
  def union(a: A, b: A): Unit
  def connected(a: A, b: A): Boolean
  def allConnected: Boolean
}

object UnionFind {
  final private case class QuickUnion(treeSize: Array[Int], entries: Array[Int], var numConnectedComponents: Int)
      extends UnionFind[Int] { self =>
    @tailrec
    private def root(value: Int): Int =
      if (value == entries(value))
        value
      else {
        entries(value) = entries(entries(value))
        root(entries(value))
      }

    override def union(a: Int, b: Int): Unit = {
      val rootA = root(a)
      val rootB = root(b)
      if (rootA != rootB) {
        numConnectedComponents = numConnectedComponents - 1
        if (treeSize(rootA) < treeSize(rootB)) {
          entries(rootA) = rootB
          treeSize(rootB) += treeSize(rootA)
        } else {
          entries(rootB) = rootA
          treeSize(rootA) += treeSize(rootB)
        }
      }
    }

    override def connected(a: Int, b: Int): Boolean =
      root(a) == root(b)

    override def allConnected: Boolean =
      numConnectedComponents == 1
  }

  def apply(entries: Int): UnionFind[Int] =
    QuickUnion(Array.fill(entries)(1), Array.tabulate(entries)(identity), entries)

  def apply[A](entries: Seq[A]): UnionFind[A] =
    new UnionFind[A] { self =>
      private val underlying: UnionFind[Int] = apply(entries.size)
      private val map: Map[A, Int]           = entries.zipWithIndex.toMap

      override def union(a: A, b: A): Unit =
        underlying.union(map(a), map(b))

      override def connected(a: A, b: A): Boolean =
        underlying.connected(map(a), map(b))

      override def allConnected: Boolean =
        underlying.allConnected
    }

}
