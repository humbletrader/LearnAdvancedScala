package org.learn.advanced.scala.linearization

import org.scalatest.{FlatSpec, _}

class LinearizationTest extends FlatSpec with Matchers{

  "constructors" should "be called from parent types to derived types" in {
    class BaseClass {
      print("BaseClass ")
    }
    trait TraitOne extends BaseClass {
      print("TraitOne ")
    }
    trait TraitTwo extends BaseClass {
      print("TraitTwo ")
    }
    class ClassExtendingBothTraits extends TraitOne with TraitTwo {
      print("ClassExtendingBothTraits ")
    }
    val c2 = new ClassExtendingBothTraits //prints BaseClass TraitOne TraitTwo ClassExtendingBothTraits

    // how linearization works ?
    // we iterate from left to right into the list of traits extended by this class (i.e TraitTwo, TraitOne)
    // so Lin(CEBT) = Lin(TraitTwo) > Lin(TraitOne)
    //
    // Lin(TraitTwo) = TraitTwo -> BaseClass
    // Lin(TraitOne) = TraitOne -> BaseClass
    //
    // TraitTwo > BaseClass > TraitOne > BaseClass
    // we remove the duplicates (from right)
    // TraitTwo > TraitOne > BaseClass
    // so we have the hierarcy of ClassExtendingBothTraits > TraitTwo > TraitOne > BaseClass
    // now the order of construction is exactly the opposite : BaseClass, TraitOne, TraitTwo, ClassExtendingBothTraits (as it is printed on the console)

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

    //linearization : T2, T1 (we start from left to right)
    // Lin(T2) > Lin(T1)
    //Lin (T2) = T2 > C1
    //Lin (T1) = T1 > C1
    //add Lin(T2) > Lin(T1) : T2 > C1 > T1 > C1
    //remove duplicates (keep only the rightmost ones)
    //T2 > T1 > C1
    //so our hierarchy is : C2 > T2 > T1 > C1

    // constructor calls C1 T1 T2 C2

  }

  it should "remove any type if it appears again to the right" in {
    class C2A extends T2 {
      override def identification: String = "c2a " + super.identification
    }

    //so T2 should appear twice in the linearization process
    class C2 extends C2A with T1 with T2 {
      override def identification: String = "c2 " + super.identification
    }

    val c2 = new C2

    // C2 > Lin(T2) > Lin(T1) > Lin(C2A)
    //T2 C1          <- linearization of the right most type (T2)
    //T2 C1 T1 C1    <- linearization of T1
    //T2 C1 T1 C1 C2A T2 C1      <- linearization of C2A
    //_  _  T1 _  C2A T2 C1      <- remove duplicates of C1 and T2 and keep only the right most one
    //C2 > T1  > C2A >  T2 >  C1               <- the result
    c2.identification shouldEqual "c2 t1 c2a t2 c1 "
  }
}
