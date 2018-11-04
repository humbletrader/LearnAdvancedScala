package org.learn.advanced.scala

package object implicits {
  case class Blah(id : String)

  implicit val blah = Blah("object defined inside the package")
}
