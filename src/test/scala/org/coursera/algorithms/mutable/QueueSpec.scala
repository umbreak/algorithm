package org.coursera.algorithms.mutable

import org.scalatest.{Inspectors, OptionValues}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class QueueSpec extends AnyWordSpecLike with Matchers with OptionValues with Inspectors {
  "A mutable Queue" should {
    val queue = Queue[String]()

    "enqueue and dequeue elements" in {
      queue.enqueue("a")
      queue.enqueue("b")
      queue.dequeue.value shouldEqual "a"
      queue.enqueue("c")
      queue.enqueue("d")
      queue.enqueue("e")
      queue.dequeue.value shouldEqual "b"
      forAll(List("c", "d", "e")) { expected =>
        queue.dequeue.value shouldEqual expected
      }
    }

    "not be empty" in {
      queue.enqueue("f")
      queue.isEmpty shouldEqual false
      queue.dequeue.value shouldEqual "f"
    }

    "be empty" in {
      queue.isEmpty shouldEqual true
    }

    "dequeue a None" in {
      queue.dequeue shouldEqual None
    }
  }
}
