package com.rockthejvm.part2oop

object AccessModifiers {

  class Person(val name: String) {
    // protected == access to inside the class + children classes
    protected def sayHi(): String = s"Hi, my name is $name."
    // private = only accessible inside the class
    private def watchNetflix(): String = "I'm binge watching my favorite series..."
  }

  class Kid(override val name: String, age: Int) extends Person(name) {
    def greetPolitely(): String = // no modifier = "public"
      sayHi() + "I love to play!"
  }

  val aPerson = new Person("Alice")
  val aKid = new Kid("David", 5)

  // complication
  class KidWithParents(override val name: String, age: Int, momName: String, dadName: String) extends Person(name) {
    val mom = new Person(momName)
    val dad = new Person(dadName)

    // can't call a protected method on ANOTHER instance of Person

    //    def everyoneSayHi(): String =
    //      this.sayHi() + s"Hi, I'm $name, and here are my parents: " + mom.sayHi() + dad.sayHi()
  }

  def main(args: Array[String]): Unit = {
    println(aKid.greetPolitely())
  }
}
