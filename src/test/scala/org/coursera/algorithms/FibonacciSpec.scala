package org.coursera.algorithms

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class FibonacciSpec extends AnyWordSpecLike with Matchers {

  "A fibonacci" should {
    "compute its sequence" in {
      Fibonacci.sequence(5) shouldEqual Vector(0, 1, 1, 2, 3)
      Fibonacci.sequence(29) shouldEqual
        Vector(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711,
          28657, 46368, 75025, 121393, 196418, 317811)
    }
    "get the fibonacci value" in {
      Fibonacci.at(0) shouldEqual 0
      Fibonacci.at(1) shouldEqual 1
      Fibonacci.at(3) shouldEqual 2
      Fibonacci.at(28) shouldEqual 317811
    }
  }

}
