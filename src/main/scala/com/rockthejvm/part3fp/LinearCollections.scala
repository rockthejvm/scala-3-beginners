package com.rockthejvm.part3fp

import scala.util.Random

object LinearCollections {

  // Seq = well-defined ordering + indexing
  def testSeq(): Unit = {
    val aSequence = Seq(4,2,3,1)
    // main API: index an element
    val thirdElement = aSequence.apply(2) // element 3
    // map/flatMap/filter/for
    val anIncrementedSequence = aSequence.map(_ + 1) // [5,3,4,2]
    val aFlatMappedSequence = aSequence.flatMap(x => Seq(x, x + 1)) // [4,5,2,3,3,4,1,2]
    val aFilteredSequence = aSequence.filter(_ % 2 == 0) // [4,2]
    // other methods
    val reversed = aSequence.reverse
    val concatenation = aSequence ++ Seq(5,6,7)
    val sortedSequence = aSequence.sorted // [1,2,3,4]
    val sum = aSequence.foldLeft(0)(_ + _) // 10
    val stringRep = aSequence.mkString("[", ",", "]")

    println(aSequence)
    println(concatenation)
    println(sortedSequence)
  }

  // lists
  def testLists(): Unit = {
    val aList = List(1,2,3)
    // same API as Seq
    val firstElement = aList.head
    val rest = aList.tail
    // appending and prepending
    val aBiggerList = 0 +: aList :+ 4
    val prepending = 0 :: aList // :: equivalent to Cons in our LList
    // utility methods
    val scalax5 = List.fill(5)("Scala")
  }

  // ranges
  def testRanges(): Unit = {
    val aRange = 1 to 10
    val aNonInclusiveRange = 1 until 10 // 10 not included
    // same Seq API
    (1 to 10).foreach(_ => println("Scala"))
  }

  // arrays
  def testArrays(): Unit = {
    val anArray = Array(1,2,3,4,5,6) // int[] on the JVM
    // most Seq APIs
    // arrays are not Seqs
    val aSequence: Seq[Int] = anArray.toIndexedSeq
    // arrays are mutable
    anArray.update(2, 30) // no new array is allocated
  }

  // vectors = fast Seqs for a large amount of data
  def testVectors(): Unit = {
    val aVector: Vector[Int] = Vector(1,2,3,4,5,6)
    // the same Seq API
  }

  def smallBenchmark(): Unit = {
    val maxRuns = 1000
    val maxCapacity = 1000000

    def getWriteTime(collection: Seq[Int]): Double = {
      val random = new Random()
      val times = for {
        i <- 1 to maxRuns
      } yield {
        val index = random.nextInt(maxCapacity)
        val element = random.nextInt()

        val currentTime = System.nanoTime()
        val updatedCollection = collection.updated(index, element)
        System.nanoTime() - currentTime
      }

      // compute average
      times.sum * 1.0 / maxRuns
    }

    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector

    println(getWriteTime(numbersList))
    println(getWriteTime(numbersVector))
  }

  // sets
  def testSets(): Unit = {
    val aSet = Set(1,2,3,4,5,4) // no ordering guaranteed
    // equals + hashCode = hash set
    // main API: test if in the set
    val contains3 = aSet.contains(3) // true
    val contains3_v2 = aSet(3) // same: true
    // adding/removing
    val aBiggerSet = aSet + 4 // [1,2,3,4,5]
    val aSmallerSet = aSet - 4 // [1,2,3,5]
    // concatenation == union
    val anotherSet = Set(4,5,6,7,8)
    val muchBiggerSet = aSet.union(anotherSet)
    val muchBiggerSet_v2 = aSet ++ anotherSet // same
    val muchBiggerSet_v3 = aSet | anotherSet // same
    // difference
    val aDiffSet = aSet.diff(anotherSet)
    val aDiffSet_v2 = aSet -- anotherSet // same
    // intersection
    val anIntersection = aSet.intersect(anotherSet) // [4,5]
    val anIntersection_v2 = aSet & anotherSet // same
  }

  def main(args: Array[String]): Unit = {
    smallBenchmark()
  }
}
