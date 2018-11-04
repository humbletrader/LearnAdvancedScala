package org.learn.advanced.scala.implicits

import org.scalatest.{FlatSpec, Matchers}
import org.learn.advanced.scala.implicits.domain._


class ImplicitResolutionTest extends FlatSpec with Matchers {

  //method to test foo implicits
  def implicitFoo(implicit param: Foo) : String = param.id

  //method to test bar implicits
  def implicitBar(implicit param: Bar) : String = param.id

  "Implicit Resolution" should "check the local implicit variables first" in {
    implicit val x : Foo = new Foo("local")
    implicitFoo shouldEqual "local"
  }

  it should "check the companion object second" in {
    implicitFoo shouldEqual "foo created in Foo's companion"
  }

  it should "check the companion of all extended traits / classes " in {
    implicitBar shouldEqual "bar created in BarParent's companion"
  }

  it should "check the companions of type parameters" in {
    implicitly[List[Foo]].head.id shouldEqual "foo created in Foo's companion inside list"
  }

  it should "check the companion of outer scopes" in {
    implicitly[Foo.Nested].id shouldEqual "nested object built inside Foo's companion"
  }

  it should "check in the package object" in {
    implicitly[Blah].id shouldEqual "object defined inside the package"
  }

}
