package org.coursera.algorithms.mutable

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class UnionFindSpec extends AnyWordSpecLike with Matchers {

  "A UnionFind" when {

    "using Int" should {
      val entries = UnionFind(10)

      "verify its connections" in {
        entries.union(4, 3)
        entries.connected(4, 3) shouldEqual true
        entries.connected(2, 3) shouldEqual false
        entries.connected(4, 4) shouldEqual true

        entries.union(3, 8)
        entries.connected(8, 4) shouldEqual true
        entries.connected(1, 4) shouldEqual false

        entries.union(6, 5)
        entries.connected(6, 5) shouldEqual true
        entries.connected(4, 6) shouldEqual false

        entries.union(9, 4)
        entries.connected(8, 9) shouldEqual true
        entries.connected(3, 9) shouldEqual true

        entries.union(2, 1)
        entries.connected(2, 1) shouldEqual true

        entries.union(5, 0)
        entries.connected(0, 6) shouldEqual true

        entries.union(7, 2)
        entries.connected(7, 1) shouldEqual true

        entries.union(6, 1)
        entries.connected(1, 7) shouldEqual true
        entries.connected(2, 5) shouldEqual true
        entries.connected(4, 7) shouldEqual false

        entries.union(7, 3)
        entries.connected(4, 7) shouldEqual true

      }
    }
  }
}
