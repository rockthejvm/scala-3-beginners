package com.rockthejvm.part4power

object PatternsEverywhere {

  // big idea #1: catches are actually MATCHES
  val potentialFailure = try {
    // code
  } catch {
    case e: RuntimeException => "runtime ex"
    case npe: NullPointerException => "npe"
    case _ => "some other exception"
  }

  /*
    try { .. code }
    catch (e) {
      e match {
        case e: RuntimeException => "runtime ex"
        case npe: NullPointerException => "npe"
        case _ => "some other exception"
      }
    }
   */

  // big idea #2: for comprehensions (generators) are based on PM
  val aList = List(1,2,3,4)
  val evenNumbers = for {
    n <- aList if n % 2 == 0
  } yield 10 * n

  val tuples = List((1,2), (3,4))
  val filterTuples = for {
    (first, second) <- tuples if first < 3
  } yield second * 100

  // big idea #3: new syntax (python-like)
  val aTuple = (1,2,3)
  val (a, b, c) = aTuple

  val head :: tail = tuples

  def main(args: Array[String]): Unit = {

  }
}
