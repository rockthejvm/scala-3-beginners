package com.rockthejvm.part3fp

import scala.annotation.tailrec

object HOFsCurrying {

  // higher order functions (HOFs)
  val aHof: (Int, (Int => Int)) => Int = (x, func) => x + 1
  val anotherHof: Int => (Int => Int) = x => (y => y + 2 * x)

  // quick exercise
  val superfunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = (x, func) => (y => x + y)

  // examples: map, flatMap, filter

  // more examples
  // f(f(f(...(f(x)))
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x + 1
  val tenThousand = nTimes(plusOne, 10000, 0)

  /*
    ntv2(po, 3) =
    (x: Int) => ntv2(po, 2)(po(x)) = po(po(po(x)))

    ntv2(po, 2) =
    (x: Int) => ntv2(po, 1)(po(x)) = po(po(x))

    ntv2(po, 1) =>
    (x: Int) => ntv2(po, 0)(po(x)) = po(x)

    ntv2(po, 0) = (x: Int) => x
   */
  def nTimes_v2(f: Int => Int, n: Int): Int => Int =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimes_v2(f, n-1)(f(x))

  val plusOneHundred = nTimes_v2(plusOne, 100) // po(po(po(po... risks SO if the arg is too big
  val oneHundred = plusOneHundred(0)

  // currying = HOFs returning function instances
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val add3: Int => Int = superAdder(3)
  val invokeSuperAdder = superAdder(3)(100) // 103

  // curried methods = methods with multiple arg list
  def curriedFormatter(fmt: String)(x: Double): String = fmt.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f") // (x: Double) => "%4.2f".format(x)
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f") // (x: Double) => "%10.8f".format(x)

  /**
   * 1. LList exercises
   *    - foreach(A => Unit): Unit
   *      [1,2,3].foreach(x => println(x))
   *
   *    - sort((A, A) => Int): LList[A]
   *      [3,2,4,1].sort((x, y) => x - y) = [1,2,3,4]
   *      (hint: use insertion sort)
   *
   *    - zipWith[B](LList[A], (A, A) => B): LList[B]
   *      [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]
   *
   *    - foldLeft[B](start: B)((A, B) => B): B
   *      [1,2,3,4].foldLeft[Int](0)(x + y) = 10
   *      0 + 1 = 1
   *      1 + 2 = 3
   *      3 + 3 = 6
   *      6 + 4 = 10
   *
   *  2. toCurry(f: (Int, Int) => Int): Int => Int => Int
   *     fromCurry(f: (Int => Int => Int)): (Int, Int) => Int
   *
   *  3. compose(f,g) => x => f(g(x))
   *     andThen(f,g) => x => g(f(x))
   */

  // 2
  def toCurry[A, B, C](f: (A, B) => C): A => B => C =
    x => y => f(x, y)

  val superAdder_v2 = toCurry[Int, Int, Int](_ + _) // same as superAdder

  def fromCurry[A, B, C](f: A => B => C): (A, B) => C =
    (x, y) => f(x)(y)

  val simpleAdder = fromCurry(superAdder)

  // 3
  def compose[A, B, C](f: B => C, g: A => B): A => C =
    x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  val incrementer = (x: Int) => x + 1
  val doubler = (x: Int) => 2 * x
  val composedApplication = compose(incrementer, doubler)
  val aSequencedApplication = andThen(incrementer, doubler)

  def main(args: Array[String]): Unit = {
    println(tenThousand)
    println(oneHundred)
    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))
    println(simpleAdder(2,78)) // 80
    println(composedApplication(14)) // 29 = 2 * 14 + 1
    println(aSequencedApplication(14)) // 30 = (14 + 1) * 2


  }
}
