package org.learn.advanced.scala.bindings

import org.scalatest.{FlatSpec, Matchers}

class BindingPriorityTest extends FlatSpec with Matchers{

  "Name Resolution" should "see the objects in the same package" in {
      x.toString shouldEqual "object x in file x.scala"
    }

  it should "give a higher priority to the wildcard import which shadows the same-package object" in {
      import Wildcard._

      //here both bindings.x and Wildard.x are available and the winner is :
      x.toString shouldEqual "object x in file Wildard.scala"
  }

  it should "give a higher priority to explicit import which shadow wildcard and same package bindings" in {
      import Explicit.x
      import Wildcard._

      //here all bindings.x, Wildrard.x and Explicit.x are available and the priority war is won by :
      x.toString shouldEqual "object x in file Explicit.scala"
  }

  it should "give the highest priority to the local variable" in {
    val x = "inline x"
    import Explicit.x
    import Wildcard.x

    x.toString shouldEqual "inline x"
  }

  it should "not compile if there are two variables with the same precendence" in {
    val x = "inline x"

    {
      import Explicit.x
      //reference to x is ambiguous
      // uncomment below to break compilation (reference to x is ambiguous)
      //x

      // The value x from the outer scope isn’t eligible to shadow within the nested scope,
      // and the imported value x doesn’t have high enough precedence to shadow.
    }
  }

}
