package org.learn.advanced.scala.bindings

import org.scalatest.{FlatSpec, Matchers}

class BindingTest extends FlatSpec with Matchers{

  "Entity Binding" should "should see the objects in the same package" in {
      x.toString shouldEqual "object x in file x.scala package bindings"
    }

  it should "prioritize the wildcard import over the same object in the same package " in {
      import Wildcard._
      x.toString shouldEqual "object x in file Wildard.scala"
  }

  it should "prioritize the explicit binding over wildard and same package binding" in {
      import Explicit.x
      import Wildcard._
      x.toString shouldEqual "object x in file Explicit.scala"
  }

}
