package model

/**
  * Created by root on 11/4/16.
  */

import com.google.inject.AbstractModule
import play.api.{Configuration, Environment}

class GuiceModule(environment: Environment, configuration: Configuration)
  extends AbstractModule {

  override def configure() = {
    bind(classOf[BooksService]).to(classOf[CachingBooksService])
  }
}