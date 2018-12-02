package org.learn.advanced.scala.implicits.bounds

import org.scalatest.{FlatSpec, Matchers}

class ViewBoundsTest extends FlatSpec with Matchers{

  class SpecialType{
    def specialMethod : String = "this value is spacial"
  }

  //this is the implicit function ( or implicit evidence) required by the fncWithViewBounds
  implicit def stringToSpecial(str: String) : SpecialType = new SpecialType

  def fncWithViewBounds[A <% SpecialType](x: A) : String = x.specialMethod

  "functions with view bounds" should "accept parameters for which there's an implicit view" in {
    fncWithViewBounds("abc") shouldEqual "this value is spacial"
  }

  it should "not compile when wrong parameters provided" in {
    //fncWithViewBounds(123) //does not compile
  }

}
