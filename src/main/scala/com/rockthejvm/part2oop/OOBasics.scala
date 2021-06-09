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
    val charlesDickens = new Writer("Charles", "Dickens", 1812)
    val charlesDickensImpostor = new Writer("Charles", "Dickens", 2021)

    val novel = new Novel("Great Expectations", 1861, charlesDickens)
    val newEdition = novel.copy(1871)

    println(charlesDickens.fullName)
    println(novel.authorAge)
    println(novel.isWrittenBy(charlesDickensImpostor)) // false
    println(novel.isWrittenBy(charlesDickens)) // true
    println(newEdition.authorAge)

    val counter = new Counter()
    counter.print() // 0
    counter.increment().print() // 1
    counter.increment() // always returns new instances
    counter.print() // 0

    counter.increment(10).print() // 10
    counter.increment(20000).print() //
  }
}

/**
  Exercise: imagine we're creating a backend for a book publishing house.
  Create a Novel and a Writer class.

  Writer: first name, surname, year
    - method fullname

  Novel: name, year of release, author
    - authorAge
    - isWrittenBy(author)
    - copy (new year of release) = new instance of Novel
 */

class Writer(firstName: String, lastName: String, val yearOfBirth: Int) {
  def fullName: String = s"$firstName $lastName"
}

class Novel(title: String, yearOfRelease: Int, val author: Writer) {
  def authorAge: Int = yearOfRelease - author.yearOfBirth
  def isWrittenBy(author: Writer): Boolean = this.author == author
  def copy(newYear: Int): Novel = new Novel(title, newYear, author)
}

/**
 * Exercise #2: an immutable counter class
 * - constructed with an initial count
 * - increment/decrement => NEW instance of counter
 * - increment(n)/decrement(n) => NEW instance of counter
 * - print()
 *
 * Benefits:
 * + well in distributed environments
 * + easier to read and understand code
 */

class Counter(count: Int = 0) {
  def increment(): Counter =
    new Counter(count + 1)

  def decrement(): Counter =
    if (count == 0) this
    else new Counter(count - 1)

  def increment(n: Int): Counter =
    if (n <= 0) this
    else increment().increment(n - 1) // vulnerable to SOs

  def decrement(n: Int): Counter =
    if (n <= 0) this
    else decrement().decrement(n - 1)

  def print(): Unit =
    println(s"Current count: $count")
}