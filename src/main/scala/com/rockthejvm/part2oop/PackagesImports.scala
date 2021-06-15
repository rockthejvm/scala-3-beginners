package com.rockthejvm.part2oop

import scala.collection.SortedSet

// can define values and methods top-level
// they will be included in a synthetic object
// can be imported via an mypackage.* import
val meaningOfLife = 42
def computeMyLife: String = "Scala"

object PackagesImports { // top-level definition
  // packages = form of organization of definitions, similar to a folder structure in a normal file system

  // fully qualified name
  val aList: com.rockthejvm.practice.LList[Int] = ??? // throws NotImplementedError

  // import
  import com.rockthejvm.practice.LList
  val anotherList: LList[Int] = ???

  // importing under an alias
  import java.util.{List as JList}
  val aJavaList: JList[Int] = ???

  // import everything
  import com.rockthejvm.practice.*
  val aPredicate: Cons[Int] = ???

  // import multiple symbols
  import PhysicsConstants.{SPEED_OF_LIGHT, EARTH_GRAVITY}
  val c = SPEED_OF_LIGHT

  // import everything EXCEPT something
  object PlayingPhysics {
    import PhysicsConstants.{PLACK as _, *}
    // val plank = PLANK // will not work
  }

  import com.rockthejvm.part2oop.* // import the mol and computeMyLife
  val mol = meaningOfLife

  // default imports:
  // scala.*, scala.Predef.*, java.lang.*

  // exports
  class PhysicsCalculator {
    import PhysicsConstants.*
    def computePhotonEnergy(wavelength: Double): Double =
      PLACK / wavelength
  }

  object ScienceApp {
    val physicsCalculator = new PhysicsCalculator

    // exports create aliases for fields/methods to use locally
    export physicsCalculator.computePhotonEnergy

    def computeEquivalentMass(wavelength: Double): Double =
      computePhotonEnergy(wavelength) / (SPEED_OF_LIGHT * SPEED_OF_LIGHT)
      // ^^ the computePhotonEnergy method can be used directly (instead of physicsCalculator.computePhotonEnergy)
      // useful especially when these uses are repeated
  }

  def main(args: Array[String]): Unit = {
    // for testing
  }
}

// usually organizing "utils" and constants in separate objects
object PhysicsConstants {
  // constants
  val SPEED_OF_LIGHT = 299792458
  val PLACK = 6.62e-34 // scientific notation
  val EARTH_GRAVITY = 9.8
}