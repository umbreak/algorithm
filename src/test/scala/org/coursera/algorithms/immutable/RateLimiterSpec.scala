package org.coursera.algorithms.immutable

import org.scalatest.{Inspectors, OptionValues}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

import java.time.Instant
import scala.concurrent.duration._

class RateLimiterSpec extends AnyWordSpecLike with Matchers with OptionValues with Inspectors {
  "A RateLimiter" should {
    val now         = Instant.now()
    val nowPlus90   = now.plusMillis(90)
    val nowPlus99   = now.plusMillis(99)
    val nowPlus101  = now.plusMillis(101)
    val nowPlus400  = now.plusMillis(400)
    val rateLimiter = RateLimiter(100.millis, now)

    "limit requests" in {
      rateLimiter.tryConsumeRequestBy("u", nowPlus90).value.tryConsumeRequestBy("u", nowPlus99) shouldEqual
        None

      rateLimiter
        .tryConsumeRequestBy("u", nowPlus90)
        .value
        .tryConsumeRequestBy("u", nowPlus101)
        .value
        .tryConsumeRequestBy("u", nowPlus400)
        .value
    }

    "limit request in burst" in {
      val nowPlus10000  = now.plusMillis(10000)
      val limiter = rateLimiter.tryConsumeRequestBy("u", nowPlus10000)
      val after11 = (0 until 11).foldLeft[Option[RateLimiter]](limiter)((acc, idx) => acc.flatMap(_.tryConsumeRequestBy("u", nowPlus10000.plusMillis(idx))))
      after11 shouldEqual None
    }
  }

}
