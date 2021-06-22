package org.coursera.algorithms

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class BinarySpec extends AnyWordSpecLike with Matchers {
  "An Int" should {
    "be converted to binary" in {
      Binary.toBinary(50) shouldEqual "110010"
    }
  }

}
