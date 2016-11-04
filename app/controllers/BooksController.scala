package controllers

import javax.inject.Inject

import model.BooksService
import play.api.libs.json.Json
import play.api.mvc._

/**
  * Created by root on 11/4/16.
  */
class BooksController @Inject()(booksService: BooksService) extends Controller {
  def get(id: Int) = Action {
    booksService.get(id).fold(NotFound: Result) { book =>
      Ok(Json.toJson(book))
    }
  }

  def list = Action {
    def books = booksService.list
    Ok(Json.toJson(books))
  }

  def updateTitle(id: Int) = Action(parse.text) { request =>
    booksService.get(id).fold(NotFound: Result) { book =>
      val updatedBook = book.copy(title = request.body)
      booksService.save(updatedBook)
      NoContent
    }
  }
}