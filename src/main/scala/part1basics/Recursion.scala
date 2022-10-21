package part1basics

import scala.annotation.tailrec

object Recursion {

  // "repetition" = recursion
  def sumUntil(n: Int): Int = {
    if (n<=0) 0
    else n + sumUntil(n-1)
  }

  /*
    sut(10, 0) =
    sut(9, 10) =
    sut(8, 9+10) =
    ..
    sut(0, 1 + 2 + 3 + 4 + 5 + 6
    */
  // in tail recursion allows to have larger stack calls
  // v2 is tail recursion v1 is normal

  def sumUntil_v2(n: Int): Int = {
    @tailrec
    def sumUntilTailRec(x: Int, accumulator: Int): Int = {
      if (x <= 0) accumulator
      else sumUntilTailRec(x - 1, accumulator + x)
      // no further stack frames necessary
    }
    sumUntilTailRec(n, 0)
  }

  def sumNumbersBetween(a: Int, b: Int): Int = {
    if(a > b) 0
    else a + sumNumbersBetween(a + 1, b)
  }

  def sumNumbersBetween_V2(a: Int, b: Int): Int = {
    @tailrec
    def sumTailRec(currentNumber: Int, accumulator: Int): Int = {
      if(currentNumber > b) accumulator
      else sumTailRec(currentNumber + 1, currentNumber + accumulator)
    }
    sumTailRec(a, 0)
  }

  /**
   *
   * 1. concatenate a string n times
   * 2. fibonacci function, make it tail recursive
   * 3.
   * @param args
   */

  def concatenate(string: String, n: Int): String = {
    @tailrec
    def concatTailRec(remainingTimes: Int, accumulator: String): String = {
      if(remainingTimes <= 0) accumulator
      else concatTailRec(remainingTimes - 1, string + accumulator)
    }
    concatTailRec(n, "")
  }

  // two accumulators here !!!! below
  def fibonacci(n: Int): Int = {
    @tailrec
    def fiboTailrec(i: Int, last: Int, previous: Int): Int = {
      if (i >= n) last
      else fiboTailrec(i + 1, last + previous, last)
    }
    if (n <= 2) 1
    else fiboTailrec(2, 1, 1)
  }

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else if (n % t==0) false
      else isPrimeUntil(t - 1) //is in tail position for this reason

    isPrimeUntil(n / 2)
  }
  // major lessons
  // stack recursion vs tail recursion
  // accumulator will keep track of partial results as you keep going into the recursion
  
  def main(args: Array[String]): Unit = {
    println(sumUntil(10))
    println(sumUntil_v2(10))
    println(concatenate("Scala", 3))
    println(fibonacci(6))
  }
}
