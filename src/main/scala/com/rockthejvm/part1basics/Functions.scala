package com.rockthejvm.part1basics

object Functions {

  // function = reusable piece of code that you can invoke with some arguments and return a result
  def aFunction(a: String, b: Int): String =
    a + " " + b // ONE expression

  // function invocation
  val aFunctionInvocation = aFunction("Scala", 999999999)

  def aNoArgFunction(): Int = 45
  def aParamterlessFunction: Int = 45

  // functions can be recursive
  def stringConcatenation(str: String, n: Int): String =
    if (n == 0) ""
    else if (n == 1) str
    else str + stringConcatenation(str, n - 1)

  /*
    sc("Scala", 3) = "Scala" + sc("Scala", 2) = "Scala" + "ScalaScala" = "ScalaScalaScala"
    sc("Scala", 2) = "Scala" + sc("Scala", 1) = "Scala" + "Scala" = "ScalaScala"
    sc("Scala", 1) = "Scala"
   */
  val scalax3 = stringConcatenation("Scala", 3)
  // when you need loops, use RECURSION.

  // "void" functions
  def aVoidFunction(aString: String): Unit =
    println(aString)

  def computeDoubleStringWithSideEffect(aString: String): String = {
    aVoidFunction(aString) // Unit
    aString + aString // meaningful value
  } // discouraging side effects

  def aBigFunction(n: Int): Int = {
    // small, auxiliary functions inside
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n + 1)
  }

  /**
   * Exercises
   * 1. A greeting function (name, age) => "Hi my name is $name and I am $age years old."
   * 2. Factorial function n => 1 * 2 * 3 * .. * n
   * 3. Fibonacci function
   *    fib(1) = 1
   *    fib(2) = 1
   *    fib(3) = 1 + 1
   *    fib(n) = fib(n-1) + fib(n-2)
   *
   * 4. Tests if a number is prime
   */

  // 1
  def greetingForKids(name: String, age: Int): String =
    "Hi, my name is " + name + " and I am " + age + " years old."

  // 2
  /*
    f(5) = 5 * f(4) = 120
    f(4) = 4 * f(3) = 24
    f(3) = 3 * f(2) = 6
    f(2) = 2 * f(1) = 2
    f(1) = 1
   */
  def factorial(n: Int): Int =
    if (n <= 0) 0
    else if (n == 1) 1
    else n * factorial(n - 1)

  // 3
  /*
    fib(5) = fib(4) + fib(3) = 5
    fib(4) = fib(3) + fib(2) = 3
    fib(3) = fib(2) + fib(1) = 2
    fib(2) = 1
    fib(1) = 1
   */
  def fibonacci(n: Int): Int =
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)

  // 4
  /*
    isPrime(7) = isPrimeUntil(3) = true
    ipu(3) = 7 % 3 != 0 && ipu(2) = true
    ipu(2) = 7 % 2 != 0 && ipu(1) = true
    ipu(1) = true
   */
  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  def main(args: Array[String]): Unit = {
    println(greetingForKids("Daniel", 9))
    println(factorial(5))
    println(fibonacci(5))
    println(isPrime(7))
  }
}
