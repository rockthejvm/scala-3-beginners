package com.rockthejvm.part2oop

object Exceptions {

  val aString: String = null
  // aString.length crashes with a NPE

  // 1 - throw exceptions
  // val aWeirdValue: Int = throw new NullPointerException // returns Nothing

  // Exception hieararchy:
  //
  // Throwable:
  //    Error, e.g. SOError, OOMError
  //    Exception, e.g. NPException, NSEException, ....

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  // 2 - catch exceptions
  val potentialFail = try {
    // code that might fail
    getInt(true) // an Int
  } catch {
    // most specific exceptions first
    case e: NullPointerException => 35
    case e: RuntimeException => 54 // an Int
    // ...
  } finally {
    // executed no matter what
    // closing resources
    // Unit here
  }

  // 3 - custom exceptions
  class MyException extends RuntimeException {
    // fields or methods
    override def getMessage = "MY EXCEPTION"
  }

  val myException = new MyException

  /**
   * Exercises:
   *
   * 1. Crash with SOError
   * 2. Crash with OOMError
   * 3. Find an element matching a predicate in LList
   */

  def soCrash(): Unit = {
    def infinite(): Int = 1 + infinite()
    infinite()
  }

  def oomCrash(): Unit = {
    def bigString(n: Int, acc: String): String =
      if (n == 0) acc
      else bigString(n - 1, acc + acc)

    bigString(56175363, "Scala")
  }


  def main(args: Array[String]): Unit = {
    println(potentialFail)
    // val throwingMyException = throw myException

    // soCrash()
    oomCrash()
  }
}
