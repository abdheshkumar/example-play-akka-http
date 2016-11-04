package example

import akka.actor.{Actor, ActorRef, Props}
import akka.pattern._
import akka.util.Timeout
import example.ReverseStringActor.{ReversedString, ToReverseString}
import example.ReverseStringResponseActor.Input

import scala.concurrent.ExecutionContext

/**
  * Created by root on 11/4/16.
  */
class ReverseStringResponseActor(implicit timeout: Timeout) extends Actor {

  import context._

  lazy val reverseStringActor = context.actorOf(ReverseStringActor.props, ReverseStringActor.name)

  def receive = {
    case Input(input) =>
      val result = reverseStringActor.ask(ToReverseString(input))
        .mapTo[List[String]]
      pipe(result) to sender()
  }
}

object ReverseStringResponseActor {
  def props(implicit timeout: Timeout) = Props(new ReverseStringResponseActor)

  def name = "response-actor"

  case class Input(input: String)

}

trait ReverseRepository {

  def createReverseStringResponseActor(): ActorRef

  implicit val requestTimeout: Timeout

  implicit def extractExecutionContext: ExecutionContext

  lazy val reverseActor = createReverseStringResponseActor()

  def reverseString(inputString: String) = {
    reverseActor.ask(Input(inputString))
      .mapTo[List[String]]
  }
}

