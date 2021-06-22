package org.coursera.algorithms.benchmarks

import org.coursera.algorithms.immutable.{Sort => ImmutableSort}
import org.coursera.algorithms.mutable.{Sort => MutableSort}
import org.openjdk.jmh.annotations._

import scala.util.Random
//noinspection TypeAnnotation
/**
  * Benchmark on Sort algorithms (between algorithms and between implementations mutable/immutable)
  * To run it, execute on the sbt shell: ''jmh:run''
  */
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(1)
@Threads(1)
class SortBenchmarks {

  @Benchmark
  def mutableSelectionSort10000(suite: MutableSuite): Array[Int] = {
    suite.selectionSort(suite.array10000Selection)
    suite.array10000Selection
  }

  @Benchmark
  def mutableSelectionSort100000(suite: MutableSuite): Array[Int] = {
    suite.selectionSort(suite.array100000Selection)
    suite.array100000Selection
  }

  @Benchmark
  def immutableSelectionSort10000(suite: ImmutableSuite): List[Int] =
    suite.selectionSort(suite.list10000)

  @Benchmark
  def immutableSelectionSort100000(suite: ImmutableSuite): List[Int] =
    suite.selectionSort(suite.list100000)

  @Benchmark
  def mutableInsertionSort10000(suite: MutableSuite): Array[Int] = {
    suite.selectionSort(suite.array10000Insertion)
    suite.array10000Insertion
  }

  @Benchmark
  def mutableInsertionSort100000(suite: MutableSuite): Array[Int] = {
    suite.selectionSort(suite.array100000Insertion)
    suite.array100000Insertion
  }

  @Benchmark
  def immutableInsertionSort10000(suite: ImmutableSuite): List[Int] =
    suite.insertionSort(suite.list10000)

  @Benchmark
  def immutableInsertionSort100000(suite: ImmutableSuite): List[Int] =
    suite.insertionSort(suite.list100000)

}

@State(Scope.Benchmark)
class ImmutableSuite {
  val list10000  = Random.shuffle((0 until 10000).toList)
  val list100000 = Random.shuffle((0 until 100000).toList)

  val insertionSort = ImmutableSort.insertion
  val selectionSort = ImmutableSort.selection
}

@State(Scope.Benchmark)
class MutableSuite {
  val array10000Selection  = Random.shuffle((0 until 10000).toList).toArray
  val array10000Insertion  = Random.shuffle((0 until 10000).toList).toArray
  val array100000Selection = Random.shuffle((0 until 100000).toList).toArray
  val array100000Insertion = Random.shuffle((0 until 100000).toList).toArray

  val insertionSort = MutableSort.insertion
  val selectionSort = MutableSort.selection
}
