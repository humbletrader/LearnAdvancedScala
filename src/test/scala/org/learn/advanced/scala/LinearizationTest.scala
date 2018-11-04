package org.learn.advanced.scala

import org.scalatest.FlatSpec

import org.scalatest._

class LinearizationTest extends FlatSpec with Matchers{

  "constructors" should "be called from parent types to derived types" in {
    class C1{
      print("c1 ")
    }
    trait T1 extends C1 {
      print("t1 ")
    }
    trait T2 extends C1 {
      print("t2 ")
    }
    class C2 extends T1 with T2 {
      print("c2 ")
    }
    val c2 = new C2 //prints c1 t1 t2 c2
  }


  class C1{
    def identification: String = "c1 "
  }

  trait T1 extends C1 {
    override def identification : String = "t1 " + super.identification
  }

  trait T2 extends C1 {
    override def identification : String = "t2 " + super.identification
  }

  "linearization" should "be done from right to left (the reverse of constructor calls)" in {

    class C2 extends T1 with T2 {
      override def identification : String = "c2 " + super.identification
    }

    val c2 = new C2
    c2.identification shouldEqual "c2 t2 t1 c1 "
  }

  it should "remove any type if it appears again to the right" in {
    class C2A extends T2 {git git
      override def identification: String = "c2a " +super.identification
    }

    //so T2 should appear twice in the linearization process
    class C2 extends C2A with T1 with T2 {
      override def identification: String = "c2 "+super.identification
    }

    val c2 = new C2

    //C2                <- copy the type of the instance
    //C2 T2 C1          <- linearization of the right most type (T2)
    //C2 T2 C1 T1 C1    <- linearization of T1
    //C2 T2 C1 T1 C1 C2A T2 C1      <- linearization of C2A
    //C2 _  _  T1 _  C2A T2 C1      <- remove duplicates of C1 and T2 and keep only the right most one
    //C2 T1 C2A T2 C1               <- the result
    c2.identification shouldEqual "c2 t1 c2a t2 c1 "
  }
}
