package org.learn.advanced.scala.implicits

package object parameters {

  case class Blah(id : String)

  implicit val blah = Blah("object defined inside the package")

}
