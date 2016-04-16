package web

import scredis.Redis
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Redis operations for Statistics model.
  */
object RedisService {

  //redis configuration can be passed here: Redis(config)
  lazy val redis = Redis()

  // Import the internal ActorSystem's dispatcher (execution context) to register callbacks
  import redis.dispatcher

  def get(id: String): Set[String] =
    Await.result(redis.sMembers[String](id), Duration.Inf)

  def set(id: String, interest: String): Unit = {
    redis.sAdd(id, interest)
  }
}
