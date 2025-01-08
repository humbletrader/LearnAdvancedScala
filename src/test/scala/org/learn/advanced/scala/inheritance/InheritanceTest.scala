package org.learn.advanced.scala.inheritance

import org.scalatest.{FlatSpec, Matchers}

class InheritanceTest extends FlatSpec with Matchers{

  trait Property{
      val name: String
      override val toString : String = "Property "+name
  }

  "abstract vals" should "not be used" in {
    val x = new Property{
      override val name = "HI"
    }

    x.toString shouldEqual "Property null"
    //this is because x is an anonymous extension of Property
    // class AnonExtension extends Property
    // so the Property constructor is called first (i.e toString is evaluated as value = Property null because name is not initialized yet

    //in order to fix this we need an "early initializer"
    val y = new {val name: String = "Hi2"} with Property
    y.toString shouldEqual "Property Hi2"
  }


}
