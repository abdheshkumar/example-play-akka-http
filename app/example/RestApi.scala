package example

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.util.Timeout
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._


/**
  * Created by root on 11/4/16.
  */
class RestApi(system: ActorSystem, timeout: Timeout) extends RestRoutes {
  implicit val requestTimeout = timeout

  implicit def extractExecutionContext = system.dispatcher

  def createReverseStringResponseActor() = system.actorOf(ReverseStringResponseActor.props, ReverseStringResponseActor.name)
}


trait RestRoutes
  extends ReverseRepository
    with AppMarshalling {

  def routes =
    path("send" / Segment) { string =>
      get {
        onSuccess(reverseString(string)) {
          output =>
            complete(output)
        }
      }
    }
}