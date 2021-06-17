package com.rockthejvm.part3fp

object MapFlatMapFilterFor {

  // standard list
  val aList = List(1,2,3) // [1] -> [2] -> [3] -> Nil // [1,2,3]
  val firstElement = aList.head
  val restOfElements = aList.tail

  // map
  val anIncrementedList = aList.map(_ + 1)

  // filter
  val onlyOddNumbers = aList.filter(_ % 2 != 0)

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  val aFlatMappedList = aList.flatMap(toPair) // [1,2,2,3,3,4]

  // All the possible combinations of all the elements of those lists, in the format "1a - black"
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white", "red")

  /*
    lambda = num => chars.map(char => s"$num$char")
    [1,2,3,4].flatMap(lambda) = ["1a", "1b", "1c", "1d", "2a", "2b", "2c", "2d", ...]
    lambda(1) = chars.map(char => s"1$char") = ["1a", "1b", "1c", "1d"]
    lambda(2) = .. = ["2a", "2b", "2c", "2d"]
    lambda(3) = ..
    lambda(4) = ..
   */
  val combinations = numbers.withFilter(_ % 2 == 0).flatMap(number => chars.flatMap(char => colors.map(color => s"$number$char - $color")))

  // for-comprehension = IDENTICAL to flatMap + map chains
  val combinationsFor = for {
    number <- numbers if number % 2 == 0 // generator
    char <- chars
    color <- colors
  } yield s"$number$char - $color" // an EXPRESSION

  // for-comprehensions with Unit
  // if foreach

  /**
   * Exercises
   * 1. LList supports for comprehensions?
   * 2. A small collection of AT MOST ONE element - Maybe[A]
   *  - map
   *  - flatMap
   *  - filter
   */
  import com.rockthejvm.practice.*
  val lSimpleNumbers: LList[Int] = Cons(1, Cons(2, Cons(3, Empty())))
  // map, flatMap, filter
  val incrementedNumbers = lSimpleNumbers.map(_ + 1)
  val filteredNumbers = lSimpleNumbers.filter(_ % 2 == 0)
  val toPairLList: Int => LList[Int] = (x: Int) => Cons(x, Cons(x + 1, Empty()))
  val flatMappedNumbers = lSimpleNumbers.flatMap(toPairLList)

  // map + flatMap = for comprehensions
  val combinationNumbers = for {
    number <- lSimpleNumbers if number % 2 == 0
    char <- Cons('a', Cons('b', Cons('c', Empty())))
  } yield s"$number-$char"

  def main(args: Array[String]): Unit = {
    numbers.foreach(println)
    for {
      num <- numbers
    } println(num)

    println(combinations)
    println(combinationsFor)

    println(incrementedNumbers)
    println(filteredNumbers)
    println(flatMappedNumbers)
    println(combinationNumbers)
  }
}
