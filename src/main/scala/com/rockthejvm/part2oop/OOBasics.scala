package com.rockthejvm.part2oop

object OOBasics {

  // classes
  class Person(val name: String, age: Int) { // constructor signature
    // fields
    val allCaps = name.toUpperCase()

    // methods
    def greet(name: String): String =
      s"${this.name} says: Hi, $name"

    // signature differs
    // OVERLOADING
    def greet(): String =
      s"Hi, everyone, my name is $name"

    // aux constructor
    def this(name: String) =
      this(name, 0)

    def this() =
      this("Jane Doe")
  }

  val aPerson: Person = new Person("John", 26)
  val john = aPerson.name // class parameter != field
  val johnSayHiToDaniel = aPerson.greet("Daniel")
  val johnSaysHi = aPerson.greet()
  val genericPerson = new Person()

  def main(args: Array[String]): Unit = {
    println(johnSayHiToDaniel)

  }
}
