package com.rockthejvm.part2oop

object AbstractDataTypes {

  abstract class Animal {
    val creatureType: String // abstract
    def eat(): Unit
    // non-abstract fields/methods allowed
    def preferredMeal: String = "anything" // "accessor methods"
  }

  // abstract classes can't be instantiated
  // val anAnimal: Animal = new Animal

  // non-abstract classes must implement the abstract fields/methods
  class Dog extends Animal {
    override val creatureType = "domestic"
    override def eat(): Unit = println("crunching this bone")
    // overriding is legal for everything
    override val preferredMeal: String = "bones" // overriding accessor method with a field
  }

  val aDog: Animal = new Dog

  // traits
  trait Carnivore { // Scala 3 - traits can have constructor args
    def eat(animal: Animal): Unit
  }

  class TRex extends Carnivore {
    override def eat(animal: Animal): Unit = println("I'm a T-Rex, I eat animals")
  }

  // practical difference abstract classes vs traits
  // one class inheritance
  // multiple traits inheritance
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType = "croc"
    override def eat(): Unit = println("I'm a croc, I just crunch stuff")
    override def eat(animal: Animal): Unit = println("croc eating animal")
  }

  /*
    philosophical difference abstract classes vs traits
    - abstract classes are THINGS
    - traits are BEHAVIORS
   */

  /*
    Any
      AnyRef
        All classes we write
          scala.Null (the null reference)
      AnyVal
        Int, Boolean, Char, ...


          scala.Nothing
   */

  val aNonExistentAnimal: Animal = null
  val anInt: Int = throw new NullPointerException

  def main(args: Array[String]): Unit = {

  }
}
