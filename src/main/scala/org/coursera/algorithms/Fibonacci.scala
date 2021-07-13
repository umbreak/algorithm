package org.coursera.algorithms

import scala.annotation.tailrec

object Fibonacci {
  def sequence(firstN: Int): Seq[Long] = {

    @tailrec
    def fibonacci(acc: Seq[Long], current: Long, next: => Long): Seq[Long] =
      if (acc.size < firstN)
        fibonacci(acc :+ current, current = next, next = current + next)
      else
        acc

    fibonacci(Vector.empty, 0, 1)

  }

  def at(n: Int): Long = {

    @tailrec
    def fibonacci(iteration: Int, current: Long, next: => Long): Long =
      if (iteration < n)
        fibonacci(iteration + 1, current = next, next = current + next)
      else
        current

    fibonacci(iteration = 0, current = 0, next = 1)

  }
}
