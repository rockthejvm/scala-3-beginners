package part1basics

object CBNvsCBV {

  // CBV is call by value invoking a function with arguments
  // arguments are evaluated before function invocation
  def aFunction(arg: Int): Int = arg + 1 // evaulated right away
  val aComputation = aFunction(23 + 67)
  // 23 + 67 is evaluated before calling the function. standard way of passing arguments into the function
  // most of the langauges out there work in the same way
  // call by name CBN = arguments are passed in as literally expressions
  // expressions are evaluated within the function
  def aByNameFunction(arg: => Int): Int = arg + 1 //arg is only evaluated when the function is called CBN
  val anotherComputation = aByNameFunction(23 + 67)

  def printTwiceByValue(x: Long): Unit = {
    println("By value: " + x)
    println("By value: " + x)
  }

  // delayed evaluation of the argument
  // argument is evaluated every time it is used
  def printTwiceByName(x: => Long): Unit = {
    println("By name: " + x)
    println("By name: " + x)
  }

  def main(args: Array[String]): Unit = {
    println(aComputation)
    println(anotherComputation)
    println(printTwiceByValue(System.nanoTime()))
    println(printTwiceByName(System.nanoTime())) //passed when the function is called
  }
}
