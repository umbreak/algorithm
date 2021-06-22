package org.coursera.algorithms.mutable

import org.coursera.algorithms.mutable.SortSpec.SortSpecBehaviours
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

import scala.util.Random

class SortSpec extends AnyFlatSpecLike with Matchers with SortSpecBehaviours {

  "An Array" should behave like sortingAlgorithm(Sort.selection, array.clone(), "selection")
  "An Array" should behave like sortingAlgorithm(Sort.insertion, array.clone(), "insertion")

}

object SortSpec {

  trait SortSpecBehaviours extends Matchers { this: AnyFlatSpecLike =>
    val arraySize: Int        = 1000
    val sortedArr: Array[Int] = (0 until arraySize).toArray
    val array: Array[Int]     = Random.shuffle((0 until arraySize).toList).toArray

    def sortingAlgorithm[A: Ordering](sort: Sort, array: Array[A], algorithm: String): Unit = {
      it should s"be sorted using $algorithm" in {
        sort(array)
        array shouldEqual sortedArr
      }
    }
  }
}
