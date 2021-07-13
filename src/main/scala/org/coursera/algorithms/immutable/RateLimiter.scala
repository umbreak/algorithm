package org.coursera.algorithms.immutable

import org.coursera.algorithms.immutable.RateLimiter.User

import java.time.Instant
import scala.concurrent.duration._

trait RateLimiter {
  def tryConsumeRequestBy(user: User, instant: Instant): Option[RateLimiter]
}

object RateLimiter {

  type User = String

  def apply(rate: FiniteDuration, creation: Instant): RateLimiter = {
    class RateLimiterImpl(usedTokens: Map[User, Long]) extends RateLimiter {

      private def diff(a: Instant, b: Instant): FiniteDuration =
        Math.abs(a.toEpochMilli - b.toEpochMilli).millis

      override def tryConsumeRequestBy(user: User, instant: Instant): Option[RateLimiter] = {
        val lapsedTime = diff(instant, creation)
        val newTokens  = lapsedTime.div(rate).longValue() + 1
        val userTokens = newTokens - usedTokens.getOrElse(user, 0L)
        Option.when(userTokens > 0)(
          new RateLimiterImpl(usedTokens.updatedWith(user)(_.map(_ + 1) orElse Some(1)))
        )
      }
    }
    new RateLimiterImpl(Map.empty)
  }
}
