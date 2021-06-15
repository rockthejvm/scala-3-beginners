package com.rockthejvm.practice

import scala.annotation.tailrec

// singly linked list
// [1,2,3] = [1] -> [2] -> [3] -> |
abstract class LList[A] {
  def head: A
  def tail: LList[A]
  def isEmpty: Boolean
  def add(element: A): LList[A] = Cons(element, this)

  // concatenation
  infix def ++(anotherList: LList[A]): LList[A]

  def map[B](transformer: A => B): LList[B]
  def filter(predicate: A => Boolean): LList[A]
  def flatMap[B](transformer: A => LList[B]): LList[B]
}

case class Empty[A]() extends LList[A] {
  override def head: A = throw new NoSuchElementException
  override def tail: LList[A] = throw new NoSuchElementException
  override def isEmpty = true
  override def toString = "[]"

  override infix def ++(anotherList: LList[A]): LList[A] = anotherList

  override def map[B](transformer: A => B): LList[B] = Empty()
  override def filter(predicate: A => Boolean): LList[A] = this
  override def flatMap[B](transformer: A => LList[B]): LList[B] = Empty()
}

case class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A] {
  override def isEmpty: Boolean = false
  override def toString = {
    @tailrec
    def concatenateElements(remainder: LList[A], acc: String): String =
      if (remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

    s"[${concatenateElements(this.tail, s"$head")}]"
  }

  /*
    example
    [1,2,3] ++ [4,5,6]
    new Cons(1, [2,3] ++ [4,5,6]) =
    new Cons(1, new Cons(2, [3] ++ [4,5,6])) =
    new Cons(1, new Cons(2, new Cons(3, [] ++ [4,5,6]))) =
    new Cons(1, new Cons(2, new Cons(3, [4,5,6]))) =
    [1,2,3,4,5,6]
   */
  override infix def ++(anotherList: LList[A]): LList[A] =
    Cons(head, tail ++ anotherList)

  /*
    example
    [1,2,3].map(n * 2) =
    new Cons(2, [2,3].map(n * 2)) =
    new Cons(2, new Cons(4, [3].map(n * 2))) =
    new Cons(2, new Cons(4, new Cons(6, [].map(n * 2)))) =
    new Cons(2, new Cons(4, new Cons(6, [])))) =
    [2,4,6]
   */
  override def map[B](transformer: A => B): LList[B] =
    Cons(transformer(head), tail.map(transformer))

  /*
    example
    [1,2,3].filter(n % 2 == 0) =
    [2,3].filter(n % 2 == 0) =
    new Cons(2, [3].filter(n % 2 == 0)) =
    new Cons(2, [].filter(n % 2 == 0)) =
    new Cons(2, []) =
    [2]
   */
  override def filter(predicate: A => Boolean): LList[A] =
    if (predicate(head)) Cons(head, tail.filter(predicate))
    else tail.filter(predicate)

  /*
    [1,2,3].flatMap(n => [n, n + 1]) =
    [1,2] ++ [2,3].flatMap(trans) =
    [1,2] ++ [2,3] ++ [3].flatMap(trans) =
    [1,2] ++ [2,3] ++ [3,4] ++ [].flatMap(trans) =
    [1,2] ++ [2,3] ++ [3,4] ++ [] =
    [1,2,2,3,3,4]
   */
  override def flatMap[B](transformer: A => LList[B]): LList[B] =
    transformer(head) ++ tail.flatMap(transformer)
}

/**
    Exercise: LList extension

    1.  Generic trait Predicate[T] with a little method test(T) => Boolean
    2.  Generic trait Transformer[A, B] with a method transform(A) => B
    3.  LList:
        - map(transformer: Transformer[A, B]) => LList[B]
        - filter(predicate: Predicate[A]) => LList[A]
        - flatMap(transformer from A to LList[B]) => LList[B]

        class EvenPredicate extends Predicate[Int]
        class StringToIntTransformer extends Transformer[String, Int]

        [1,2,3].map(n * 2) = [2,4,6]
        [1,2,3,4].filter(n % 2 == 0) = [2,4]
        [1,2,3].flatMap(n => [n, n+1]) => [1,2, 2,3, 3,4]
 */

// (replaced with function types)
//trait Predicate[T] {
//  def test(element: T): Boolean // T => Boolean
//}
//
//class EvenPredicate extends Predicate[Int] {
//  override def test(element: Int) =
//    element % 2 == 0
//}
//
//trait Transformer[A, B] {
//  def transform(value: A): B // A => B
//}
//
//class Doubler extends Transformer[Int, Int] {
//  override def transform(value: Int) = value * 2
//}
//
//class DoublerList extends Transformer[Int, LList[Int]] {
//  override def transform(value: Int) =
//    Cons(value, Cons(value + 1, Empty()))
//}

object LList {
  def find[A](list: LList[A], predicate: A => Boolean): A = {
    if (list.isEmpty) throw new NoSuchElementException
    else if (predicate(list.head)) list.head
    else find(list.tail, predicate)
  }
}

object LListTest {
  def main(args: Array[String]): Unit = {
    val empty = Empty[Int]()
    println(empty.isEmpty)
    println(empty)

    val first3Numbers = Cons(1, Cons(2, Cons(3, empty)))
    println(first3Numbers)

    val first3Numbers_v2 = empty.add(1).add(2).add(3)
    println(first3Numbers_v2)
    println(first3Numbers_v2.isEmpty)

    val someStrings = Cons("dog", Cons("cat", Empty()))
    println(someStrings)

    val evenPredicate = new Function1[Int, Boolean] {
      override def apply(element: Int) =
        element % 2 == 0
    }

    val doubler = new Function1[Int, Int] {
      override def apply(value: Int) = value * 2
    }

    val doublerList = new Function1[Int, LList[Int]] {
      override def apply(value: Int) =
        Cons(value, Cons(value + 1, Empty()))
    }

    // map testing
    val numbersDoubled = first3Numbers.map(doubler)
    println(numbersDoubled)
    val numbersNested = first3Numbers.map(doublerList)
    println(numbersNested)

    // filter testing
    val onlyEvenNumbers = first3Numbers.filter(evenPredicate) // [2]
    println(onlyEvenNumbers)

    // test concatenation
    val listInBothWays = first3Numbers ++ first3Numbers_v2
    println(listInBothWays)

    // test flatMap
    val flattenedList = first3Numbers.flatMap(doublerList)
    println(flattenedList)

    // find test
    println(LList.find[Int](first3Numbers, evenPredicate)) // 2
    //    println(LList.find[Int](first3Numbers, new Predicate[Int] {
    //      override def test(element: Int) = element > 5
    //    })) // throws a NSEException
  }
}



