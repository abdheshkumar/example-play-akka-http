package example

import example.ReverseStringActor.ReversedString
import spray.json._

/**
  * Created by root on 11/4/16.
  */
trait AppMarshalling extends DefaultJsonProtocol {
  implicit val reversedStringFormat = jsonFormat1(ReversedString)
}
