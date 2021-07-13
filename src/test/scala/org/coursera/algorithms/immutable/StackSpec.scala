package org.coursera.algorithms.immutable

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import org.scalatest.{Inspectors, OptionValues}

class StackSpec extends AnyWordSpecLike with Matchers with OptionValues with Inspectors {
  "An immutable Stack" should {
    val stack = Stack[String]()
    "push and pop entries" in {
      val stack2        = stack.push("a").push("b").push("c")
      val (c, newStack) = stack2.pop().value
      c shouldEqual "c"
      val stack3 = newStack.push("d")
      List("d", "b", "a").foldLeft(stack3) { (stack, expected) =>
        val (pop, nextStack) = stack.pop().value
        pop shouldEqual expected
        nextStack
      }
    }

    "return None" in {
      stack.pop() shouldEqual None
    }
  }
}
