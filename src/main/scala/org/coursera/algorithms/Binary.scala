package org.coursera.algorithms

import scala.annotation.tailrec

object Binary {

  def toBinary(n: Int): String = {
    @tailrec
    def inner(acc: List[Int], n: Int): List[Int] =
      if (n > 0) inner((n % 2) :: acc, n / 2)
      else acc

    inner(List.empty, n).mkString("")
  }
}
