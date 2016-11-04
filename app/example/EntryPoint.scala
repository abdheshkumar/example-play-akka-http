package example

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.concurrent.Future

/**
  * Created by root on 11/4/16.
  */
object EntryPoint extends App
  with TimeoutHelper {
  val config = ConfigFactory.load("resource.conf")
  val host = config.getString("http.host")
  val port = config.getInt("http.port")
  implicit val actorSystem = ActorSystem()
  val api = new RestApi(actorSystem, requestTimeout(config)).routes
  implicit val actorMaterializer = ActorMaterializer()
  val log = Logging(actorSystem.eventStream, "com.example")
  implicit val ex = actorSystem.dispatcher

  val bindingServer: Future[Http.ServerBinding] = Http().bindAndHandle(api, host, port)
  bindingServer.map {
    server =>
      log.info(s"Rest APP bound to ${server.localAddress}")
  }.onFailure {
    case ex: Exception =>
      log.error(ex, "Failed to bind to {}:{}", host, port)
      actorSystem.terminate()
  }
}
