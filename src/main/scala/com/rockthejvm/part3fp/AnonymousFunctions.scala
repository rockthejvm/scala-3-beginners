package com.rockthejvm.part3fp

object AnonymousFunctions {

  // instances of FunctionN
  val doubler: Int => Int = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  // lambdas = anonymous function instances
  val doubler_v2: Int => Int = (x: Int) => x * 2 // identical
  val adder: (Int, Int) => Int = (x: Int, y: Int) => x + y // new Function2[Int, Int, Int] { override def apply... }
  // zero-arg functions
  val justDoSomething: () => Int = () => 45
  val anInvocation = justDoSomething()

  // alt syntax with curly braces
  val stringToInt = { (str: String) =>
    // implementation: code block
    str.toInt
  }

  // type inference
  val doubler_v3: Int => Int = x => x * 2 // type inferred by compiler
  val adder_v2: (Int, Int) => Int = (x, y) => x + y

  // shortest lambdas
  val doubler_v4: Int => Int = _ * 2 // x => x * 2
  val adder_v3: (Int, Int) => Int = _ + _ // (x, y) => x + y
  // each underscore is a different argument, you can't reuse them

  /**
   * Exercises
   * 1. Replace all FunctionN instantiations with lambdas in LList implementation.
   * 2. Rewrite the "special" adder from WhatsAFunction using lambdas.
   */
  val superAdder = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int) = new Function1[Int, Int] {
      override def apply(y: Int) = x + y
    }
  }

  val superAdder_v2 = (x: Int) => (y: Int) => x + y
  val adding2 = superAdder_v2(2) // (y: Int) => 2 + y
  val addingInvocation = adding2(43) // 45
  val addingInvocation_v2 = superAdder_v2(2)(43) // same

  def main(args: Array[String]): Unit = {
    println(justDoSomething)
    println(justDoSomething())
  }
}
