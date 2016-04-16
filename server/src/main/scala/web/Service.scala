package web

import akka.actor.Actor
import spray.http.MediaTypes._
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

trait MainController extends HttpService {

  val route =
    path("") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Worked!</h1>
              </body>
            </html>
          }
        }
      }
    }

}
