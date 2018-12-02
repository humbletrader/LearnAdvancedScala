package org.learn.advanced.scala.implicits.bounds

import org.scalatest.{FlatSpec, Matchers}

class ContextBoundsTest extends FlatSpec with Matchers{

  class SpecialContainer[T]{
    def specialMethod(x : T) : String = s"special value $x of type ${x.getClass.getSimpleName}"
  }

  implicit val stringContainer = new SpecialContainer[String]

  def fncWithContextBounds[A : SpecialContainer](x : A) : String = implicitly[SpecialContainer[A]].specialMethod(x)

  "function with context bounds" should "accept types which " in {
    fncWithContextBounds("abc") shouldEqual "special value abc of type String"
  }
}
