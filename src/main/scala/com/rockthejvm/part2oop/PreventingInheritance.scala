package com.rockthejvm.part2oop

object PreventingInheritance {

  class Person(name: String) {
    final def enjoyLife(): Int = 42 // final = cannot be overridden
  }

  class Adult(name: String) extends Person(name) {
    // override def enjoyLife() = 999 // illegal
  }

  final class Animal // cannot be inherited
  // class Cat extends Animal // illegal

  // sealing a type hierarchy = inheritance only permitted inside this file
  sealed class Guitar(nStrings: Int)
  class ElectricGuitar(nStrings: Int) extends Guitar(nStrings)
  class AcousticGuitar extends Guitar(6)

  // no modifier = "not encouraging" inheritance
  // open = specifically marked for extension
  // not mandatory, good practice (should be accompanied by documentation on what extension implies)
  open class ExtensibleGuitar(nStrings: Int)

  def main(args: Array[String]): Unit = {

  }
}
