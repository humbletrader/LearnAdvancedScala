package org.learn.advanced.scala.reflection.classtags

import org.scalatest.{FlatSpec, Matchers}

import scala.reflect.runtime.universe._
import scala.reflect._


class TypeTagsTest extends FlatSpec with Matchers{

  "implicits typeTag and classtag" should "be provided for most common classes" in {
    typeTag[Int]
    classTag[String]
  }

  def paramInfo[T](t : T)(implicit ev: TypeTag[T]) : String = {
    ev.tpe match{
      case TypeRef(_, _, args) => s"type of $t requires the following arguments $args"
    }
  }

  "type tags" should "carry compile information at runtime" in {
    paramInfo(42) shouldEqual "type of 42 requires the following arguments List()"
    paramInfo(Vector(42, 43, 44)) shouldEqual "type of Vector(42, 43, 44) requires the following arguments List(Int)"
    paramInfo(Some[Vector[Int]](Vector(42, 43, 44))) shouldEqual "type of Some(Vector(42, 43, 44)) requires the following arguments List(Vector[Int])"
  }

}
