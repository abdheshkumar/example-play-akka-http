package model

import javax.inject.Inject

import play.api.cache.CacheApi
import play.api.libs.json.Json

import scala.concurrent.duration._
import scala.collection.mutable

/**
  * Created by root on 11/4/16.
  */
case class Book(id: Int, title: String)

object Book {
  implicit val jsonFormat = Json.format[Book]
}

trait BooksService {
  def list: Seq[Book]

  def get(id: Int): Option[Book]

  def save(book: Book): Unit
}

class CachingBooksService @Inject()(cache: CacheApi) extends BooksService {

  private val db = mutable.Map(
    1 -> Book(1, "Twilight"),
    2 -> Book(2, "50 Shades of Grey")) //simulates some persistent storage

  override def list: Seq[Book] = {
    //get "books" entry from cache, if it doesn't exist fetch fresh list from the "DB"
    cache.getOrElse("books") {
      def freshBooks = fetchFreshBooks()
      cache.set("books", freshBooks, 2.minutes) //cache freshly fetched books for 2 minutes
      freshBooks
    }
  }

  override def get(id: Int): Option[Book] = {
    cache.getOrElse(s"book$id") {
      def freshBook = fetchFreshBook(id)
      cache.set(s"book$id", freshBook, 2.minutes)
      freshBook
    }
  }

  override def save(book: Book): Unit = {
    db(book.id) = book
  }

  private def fetchFreshBooks(): Seq[Book] = {
    db.values.toSeq.sortBy(_.id)
  }

  private def fetchFreshBook(id: Int): Option[Book] = {
    db.get(id)
  }
}
