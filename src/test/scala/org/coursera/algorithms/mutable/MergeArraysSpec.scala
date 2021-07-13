package org.coursera.algorithms.mutable

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class MergeArraysSpec extends AnyWordSpecLike with Matchers {

  "A MergeArrays" should {

    "merge two arrays in the first of the arrays (which contains a buffer)" in {
      val arrayA = Array(1, 20, 300, 4000, 50000, -1, -1, -1)
      val arrayB = Array(-1, 2, 99)
      val expected = Array(-1, 1, 2, 20, 99, 300, 4000, 50000)
      MergeArrays(arrayA, arrayB) shouldEqual expected
      arrayA shouldEqual expected
    }
  }

}
