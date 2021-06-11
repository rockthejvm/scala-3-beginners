package com.rockthejvm.part2oop

object Generics {

  // goal: reuse code on different types

  // option 1: copy the code
  abstract class IntList {
    def head: Int
    def tail: IntList
  }
  class EmptyIntList extends IntList {
    override def head = throw new NoSuchElementException
    override def tail = throw new NoSuchElementException
  }
  class NonEmptyIntList(override val head: Int, override val tail: IntList) extends IntList

  abstract class StringList {
    def head: String
    def tail: StringList
  }
  class EmptyStringList extends StringList {
    override def head = throw new NoSuchElementException
    override def tail = throw new NoSuchElementException
  }
  class NonEmptyStringList(override val head: String, override val tail: StringList) extends StringList
  // ... and so on for all the types you want to support
  /*
    Pros:
    - keeps type safety: you know which list holds which kind of elements
    Cons:
    - boilerplate
    - unsustainable
    - copy/paste... really?
   */

  // option 2: make the list hold a big, parent type
  abstract class GeneralList {
    def head: Any
    def tail: GeneralList
  }
  class EmptyGeneralList extends GeneralList {
    override def head = throw new NoSuchElementException
    override def tail = throw new NoSuchElementException
  }
  class NonEmptyGeneralList(override val head: Any, override val tail: GeneralList) extends GeneralList
  /*
    Pros:
    - no more code duplication
    - can support any type
    Cons:
    - lost type safety: can make no assumptions about any element or method
    - can now be heterogeneous: can hold cats and dogs in the same list (not funny)
   */

  // solution: make the list generic with a type argument
  abstract class MyList[A] { // "generic" list; Java equivalent: abstract class MyList<A>
    def head: A
    def tail: MyList[A]
  }

  class Empty[A] extends MyList[A] {
    override def head: A = throw new NoSuchElementException
    override def tail: MyList[A] = throw new NoSuchElementException
  }

  class NonEmpty[A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  // can now use a concrete type argument
  val listOfIntegers: MyList[Int] = new NonEmpty[Int](1, new NonEmpty[Int](2, new Empty[Int]))

  // compiler now knows the real type of the elements
  val firstNumber = listOfIntegers.head
  val adding = firstNumber + 3

  // multiple type arguments
  trait MyMap[Key, Value]

  // generic methods
  object MyList {
    def from2Elements[A](elem1: A, elem2: A): MyList[A] =
      new NonEmpty[A](elem1, new NonEmpty[A](elem2, new Empty[A]))
  }

  // calling methods
  val first2Numbers = MyList.from2Elements[Int](1, 2)
  val first2Numbers_v2 = MyList.from2Elements(1, 2) // compiler can infer generic type from the type of the arguments
  val first2Numbers_v3 = new NonEmpty(1, new NonEmpty(2, new Empty))

  /**
   * Exercise: genericize LList.
   */

  def main(args: Array[String]): Unit = {

  }
}
