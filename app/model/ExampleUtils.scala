package model

import scala.annotation.tailrec

/**
  * Created by root on 11/4/16.
  */
object ExampleUtils {

  def reverseString(str: String): String = innerReverseString(str.toCharArray.toList, result = "")


  /**
    * innerReverseString function reverse the string using tail recursive call
    *
    * @param stringChars : List[Char] list of character of string
    * @param result      : String it's unity value of string
    * @return String reversed string
    */
  @tailrec
  def innerReverseString(stringChars: List[Char], result: String): String = {
    stringChars match {
      case Nil => result
      case head :: tail => innerReverseString(tail, head.toString + result)
    }
  }

  /**
    * Write code which prints any integers
    * divisible by 3 between 1 and 100 along with
    * positive and negative unit tests
    */
  def displayDivisibleNumber(startRange: Int,
                             endRange: Int,
                             divisibleBy: Int => Boolean): List[Int] =
    (startRange to endRange)
      .filter(divisibleBy)
      .toList
}
