package org.coursera.algorithms

import org.coursera.algorithms.models.SortedSeq
import org.scalatest.{Inspectors, OptionValues}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class SearchSpec extends AnyWordSpecLike with Matchers with OptionValues with Inspectors {

  "A Search" should {
    val values = SortedSeq(Vector(6, 13, 14, 25, 33, 43, 51, 53, 64, 72, 84, 93, 95, 96, 97))
    val search = Search.binarySearch[Int]
    "find the index of a sequence of entries" in {
      forAll(values.values.zipWithIndex) {
        case (value, idx) =>
          search.indexOf(values)(value).value shouldEqual idx
      }
    }
    "return None when the value is not on the entries" in {
      search.indexOf(values)(98) shouldEqual None
      search.indexOf(values)(5) shouldEqual None
    }
  }

}
