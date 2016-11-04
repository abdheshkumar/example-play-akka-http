import model.ExampleUtils
import org.scalatest.{FlatSpec, FunSuite, ShouldMatchers}

/**
  * Created by root on 11/4/16.
  */
class ExampleUtilsTest
  extends FlatSpec
    with ShouldMatchers {

  "reverseString function" should " return reverse of the sting" in {
    val reversedString = ExampleUtils.reverseString("Hello test")
    reversedString shouldBe "tset olleH"
  }

  "reverseString function" should " return empty string if actual string is empty" in {
    val reversedString = ExampleUtils.reverseString("")
    reversedString shouldBe empty
  }

  "displayDivisibleNumber function" should " return list of number that are divisible by 3" in {
    val results = ExampleUtils.displayDivisibleNumber(1, 12, _ % 3 == 0)
    results shouldBe List(3, 6, 9, 12)
  }

  "displayDivisibleNumber function" should " return empty list if there is no number divisible by  3" in {
    val results = ExampleUtils.displayDivisibleNumber(1, 2, _ % 3 == 0)
    results shouldBe empty
  }

  "displayDivisibleNumber function" should "return numbers from 1 to 100 that are divisible by  3" in {
    val results = ExampleUtils.displayDivisibleNumber(1, 100, _ % 3 == 0)
    val expected = 3 to 100 by 3
    results shouldBe expected.toList
  }
}
