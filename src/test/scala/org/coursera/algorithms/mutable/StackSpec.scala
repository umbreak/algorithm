package org.coursera.algorithms.mutable

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import org.scalatest.{Inspectors, OptionValues}

class StackSpec extends AnyWordSpecLike with Matchers with OptionValues with Inspectors {
  "A mutable Stack" should {
    val stack = Stack[String]()

    "push and pop entries" in {
      stack.push("a")
      stack.push("b")
      stack.push("c")
      stack.pop().value shouldEqual "c"
      stack.push("d")
      forAll(List("d", "b", "a")) { expected =>
        stack.pop().value shouldEqual expected
      }
    }

    "return None" in {
      stack.pop() shouldEqual None
    }
  }

}
