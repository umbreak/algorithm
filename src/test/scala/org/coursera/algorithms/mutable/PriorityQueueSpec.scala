package org.coursera.algorithms.mutable

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import org.scalatest.{Inspectors, OptionValues}

import scala.util.Random

class PriorityQueueSpec extends AnyWordSpecLike with Matchers with OptionValues with Inspectors {

  "A PriorityQueue" should {
    "return the biggest entries" in {
      val queue = PriorityQueue[String]()
      List('b', 'c', 'a', 'e', 'd').foreach(elem => queue.insert(elem.toString))
      queue.size shouldEqual 5
      queue.delMax().value shouldEqual "e"
      queue.delMax().value shouldEqual "d"
      Random.shuffle(('f' to 'j').toList).foreach(elem => queue.insert(elem.toString))
      queue.size shouldEqual 8
      forAll(('f' to 'j').toList.reverse) { expected =>
        queue.delMax().value shouldEqual expected.toString
      }

      forAll(('a' to 'c').toList.reverse) { expected =>
        queue.delMax().value shouldEqual expected.toString
      }
      queue.size shouldEqual 0
    }

    "return None When deleting from an empty queue" in {
      PriorityQueue[String]().delMax() shouldEqual None
      PriorityQueue[String]().size shouldEqual 0
      PriorityQueue[String]().isEmpty shouldEqual true
    }
  }

}
