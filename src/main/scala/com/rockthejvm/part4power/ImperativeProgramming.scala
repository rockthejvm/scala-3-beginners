package com.rockthejvm.part4power

object ImperativeProgramming {

  val meaningOfLife: Int = 42

  var aVariable = 99
  aVariable = 100 // vars can be reassigned
  // aVariable = "Scala" // types cannot be changed

  // modify a variable in place
  aVariable += 10 // aVariable = aVariable + 10

  // increment/decrement operators don't exist in Scala
  // aVariable++ // illegal in Scala

  // loops
  def testLoop(): Unit = {
    var i = 0
    while (i < 10) {
      println(s"Counter at $i")
      i += 1
    }
  }

  /*
    Imperative programming (loops/variables/mutable data) are not recommended:
    - code becomes hard to read and understand (especially in growing code bases)
    - vulnerable to concurrency problems (e.g. need for synchronization)

    Imperative programming can help
    - for performance-critical applications (0.1% of cases; Akka/ZIO/Cats are already quite fast)
    - for interactions with Java libraries (usually mutable)

    Using imperative programming in Scala for no good reason defeats the purpose of Scala.
   */

  val anExpression: Unit = aVariable += 10
  val aLoop: Unit = while (aVariable < 130) {
    println("counting more")
    aVariable += 1
  }

  def main(args: Array[String]): Unit = {
    testLoop()
  }
}
