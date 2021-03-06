import scredis.Redis

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object RedisService {

  //redis configuration can be passed here: Redis(config)
  lazy val redis = Redis()

  // Import the internal ActorSystem's dispatcher (execution context) to register callbacks

  def get(id: String): Set[String] =
    Await.result(redis.sMembers[String](id), Duration.Inf)

  def set(id: String, interest: String): Unit = {
    redis.sAdd(id, interest)
  }
}
