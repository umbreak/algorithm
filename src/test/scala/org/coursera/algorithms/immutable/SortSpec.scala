package org.coursera.algorithms.immutable

import org.coursera.algorithms.immutable.SortSpec.SortSpecBehaviours
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

import scala.util.Random

class SortSpec extends AnyFlatSpecLike with Matchers with SortSpecBehaviours {

  private val list = Random.shuffle((0 until 1000).toList)

  "A List" should behave like sortingAlgorithm(Sort.selection, list, "selection")
  "A List" should behave like sortingAlgorithm(Sort.insertion, list, "insertion")

}

object SortSpec {
  trait SortSpecBehaviours extends Matchers { this: AnyFlatSpecLike =>
    def sortingAlgorithm[A: Ordering](sort: Sort, list: List[A], algorithm: String): Unit = {
      it should s"be sorted using $algorithm" in {
        sort(list) shouldEqual list.sorted
      }
    }
  }
}
