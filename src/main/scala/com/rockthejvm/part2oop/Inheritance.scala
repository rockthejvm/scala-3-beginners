package com.rockthejvm.part2oop

object Inheritance {

  class Animal {
    val creatureType = "wild"
    def eat(): Unit = println("nomnomnom")
  }

  class Cat extends Animal { // a cat "is an" animal
    def crunch() = {
      eat()
      println("crunch, crunch")
    }
  }

  val cat = new Cat

  class Person(val name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name) // must specify super-constructor

  // overriding
  class Dog extends Animal {
    override val creatureType = "domestic"
    override def eat(): Unit = println("mmm, I like this bone")

    // popular overridable method
    override def toString: String = "a dog"
  }

  // subtype polymorphism
  val dog: Animal = new Dog
  dog.eat() // the most specific method will be called

  // overloading vs overriding
  class Crocodile extends Animal {
    override val creatureType = "very wild"
    override def eat(): Unit = println("I can eat anything, I'm a croc")

    // overloading: multiple methods with the same name, different signatures
    // different signature =
    //    different argument list (different number of args + different arg types)
    //    + different return type (optional)
    def eat(animal: Animal): Unit = println("I'm eating this poor fella")
    def eat(dog: Dog): Unit = println("eating a dog")
    def eat(person: Person): Unit = println(s"I'm eating a human with the name ${person.name}")
    def eat(person: Person, dog: Dog): Unit = println("I'm eating a human AND the dog")
    // def eat(): Int = 45 // not a valid overload
    def eat(dog: Dog, person: Person): Unit = println("I'm eating a human AND the dog")

  }

  def main(args: Array[String]): Unit = {
    println(dog) // println(dog.toString)

  }
}
