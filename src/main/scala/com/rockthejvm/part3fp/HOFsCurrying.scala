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

  def main(args: Array[String]): Unit = {
    println(tenThousand)
    println(oneHundred)
    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))
  }
}
