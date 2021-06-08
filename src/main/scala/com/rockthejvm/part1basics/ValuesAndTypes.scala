package com.rockthejvm.part1basics

object ValuesAndTypes {

  // values
  val meaningOfLife: Int = 42 // const int meaningOfLife = 42

  // reassigning is not allowed
  // meaningOfLife = 45

  // type inferrence
  val anInteger = 67 // : Int is optional

  // common types
  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = 78 // 4 bytes
  val aShort: Short = 5263 // 2 bytes
  val aLong: Long = 52789572389234L // 8 bytes
  val aFloat: Float = 2.4f // 4 bytes
  val aDouble: Double = 3.14 // 8 bytes

  // string
  val aString: String = "Scala"

  def main(args: Array[String]): Unit = {

  }
}
