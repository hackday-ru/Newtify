import akka.actor.ActorSystem
import com.sksamuel.elastic4s.ElasticDsl.{search, _}
import com.sksamuel.elastic4s.{ElasticClient, ElasticsearchClientUri}
import spray.routing.SimpleRoutingApp

object ServerStarter extends App with SimpleRoutingApp {
  implicit val system = ActorSystem()
  val client = ElasticClient
    .transport(ElasticsearchClientUri("elasticsearch://localhost:9300"))

  startServer(interface = "localhost", port = 8080) {
    path("") {
      get {
        complete {
          <h2>whatsup</h2>
        }
      }
    }
    path("search") {
      get {
        parameters('q) { (q) =>
          val resp = client.execute {search in "article" query q}.await
          complete(resp.getHits.hits().map(x => x.getSource.get("url"))
            .mkString("[",",","]"))
        }
      }
    }
  }
}
