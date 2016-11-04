package example

import akka.actor.{Actor, Props}
import example.ReverseStringActor.{ReversedString, ToReverseString}

/**
  * Created by root on 11/4/16.
  */
class ReverseStringActor extends Actor {
  private var result = Set[String]()

  def receive = {
    case ToReverseString(inputString) =>
      val re = inputString.reverse
      result = result + re
      sender() ! result.toList
  }
}

object ReverseStringActor {
  def props = Props(new ReverseStringActor)

  def name = "reverse-string-actor"

  case class ToReverseString(input: String)

  case class ReversedString(outPut: Set[String])

}
