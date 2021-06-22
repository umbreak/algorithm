package org.coursera.algorithms.immutable

import org.scalatest.{Inspectors, OptionValues}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class QueueSpec extends AnyWordSpecLike with Matchers with OptionValues with Inspectors {
  "An immutable Queue" should {
    val queue = Queue[String]()

    "enqueue and dequeue elements" in {
      val queue2 = queue.enqueue("a").enqueue("b")
      queue2.head.value shouldEqual "a"
      val queue3 = queue2.dequeue
      val queue4 = queue3.enqueue("c").enqueue("d").enqueue("e")
      queue4.head.value shouldEqual "b"

      List("c", "d", "e").foldLeft(queue4.dequeue) { (queue, expected) =>
        queue.head.value shouldEqual expected
        queue.dequeue
      }
    }

    "not be empty" in {
      queue.enqueue("f").isEmpty shouldEqual false
    }

    "be empty" in {
      queue.isEmpty shouldEqual true
      queue.dequeue.isEmpty shouldEqual true
    }

    "dequeue a None" in {
      queue.head shouldEqual None
    }
  }
}
