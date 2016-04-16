package web

import akka.actor.Actor
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import spray.routing.HttpService

class ApiActor extends Actor with MainController {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(route)
}

case class UserArticle(text: String, link_title: String, url: String, user_id: Long)

object UserArticleJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val Formats = jsonFormat4(UserArticle)
}

trait MainController extends HttpService {

  import UserArticleJsonSupport._

  //TODO: implement saving user interests.
  val route = post {
    path("add" / "article") {
      entity(as[UserArticle]) { userArticle =>
        println(s"UserArticle: ${userArticle.user_id} - text: ${userArticle.text}")
        complete(s"UserArticle: ${userArticle.user_id} - text: ${userArticle.text}")
      }
    }
  }
}
