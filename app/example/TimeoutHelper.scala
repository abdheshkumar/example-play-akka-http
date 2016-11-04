package example

import akka.util.Timeout
import com.typesafe.config.Config

import scala.concurrent.duration.{Duration, FiniteDuration}

/**
  * Created by root on 11/4/16.
  */
trait TimeoutHelper {
  def requestTimeout(config: Config): Timeout = {
    val timeout = config.getString("akka.http.server.timeout")
    val duration = Duration(timeout)
    FiniteDuration(duration.length, duration.unit)
  }
}
