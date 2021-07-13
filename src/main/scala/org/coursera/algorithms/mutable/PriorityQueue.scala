package org.coursera.algorithms.mutable

import math.Ordering.Implicits._
import scala.annotation.tailrec
import scala.reflect.ClassTag

trait PriorityQueue[A] {
  def insert(value: A): Unit
  def delMax(): Option[A]
  var size: Int
  def isEmpty: Boolean
}

object PriorityQueue {
  def apply[A >: Null: Ordering : ClassTag](capacity: Int = 1001): PriorityQueue[A] = new PriorityQueue[A] {

    private val array: Array[A]      = Array.ofDim(capacity)
    override var size: Int = 0

    override def insert(value: A): Unit = {
      size = size + 1
      array(size) = value
      moveUp(size)
    }

    override def delMax(): Option[A] =
      Option.when(!isEmpty) {
        val max = array(1)
        swap(1, size)
        size = size - 1
        moveDown(1)
        array(size + 1) = null
        max
      }

    override def isEmpty: Boolean = size == 0

    private def parent(idx: Int): Int = if(idx > 1) idx / 2 else 1

    private def children(idx: Int): (Int, Int) = (idx * 2, idx * 2 + 1)

    @tailrec
    private def moveUp(idx: Int): Unit = {
      val parentIdx = parent(idx)
      if (array(parentIdx) < array(idx)) {
        swap(parentIdx, idx)
        moveUp(parentIdx)
      }
    }

    @tailrec
    private def moveDown(idx: Int): Unit = {
      val (childLeft, childRight) = children(idx)
      val biggerChildIdx = for {
        left  <- fetchSafe(childLeft)
        right <- fetchSafe(childRight) orElse Some(left)
      } yield if (right > left) childRight else childLeft
      biggerChildIdx match {
        case Some(childIdx) if array(childIdx) > array(idx) =>
          swap(childIdx, idx)
          moveDown(childIdx)
        case _ => ()
      }
    }

    private def fetchSafe(idx: Int): Option[A] =
      Option.when(idx < size)(array(idx))

    private def swap(a: Int, b: Int): Unit = {
      val tmp = array(a)
      array(a) = array(b)
      array(b) = tmp
    }
  }
}
