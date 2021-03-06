package org.learn.advanced.scala.implicits.parameters

object domain {

  class Foo(val id: String)

  object Foo {
    implicit val foo = new Foo("foo created in Foo's companion")
    implicit val list = List(new Foo("foo created in Foo's companion inside list"))

    class Nested(val id: String)
    implicit def nested = new Nested("nested object built inside Foo's companion")

  }

  trait BarParent
  object BarParent{
    implicit val bar = new Bar ("bar created in BarParent's companion")
  }
  //class without companion object
  //the implicit resolution for Bar will check the BarParent's companion
  //because Bar extends BarParent
  class Bar(val id: String) extends BarParent


  object MySingleton {
      override def toString = "MySingletonAsString"
  }

  implicit val singletonImplicitDefinedInOuterScope = MySingleton
//  implicit val blahType : MySingleton.type = MySingleton //in scala 2.10

}
