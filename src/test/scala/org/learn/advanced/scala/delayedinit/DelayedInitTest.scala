package org.learn.advanced.scala.delayedinit

import org.scalatest.{FlatSpec, Matchers}

class DelayedInitTest extends FlatSpec with Matchers{

  "DelayedInit" should "blah" in {

    class MyDelayedApp extends DelayedInit{
      println("this code is executed during initialization")

      override def delayedInit(x: => Unit): Unit = {
        println("inside delayed init")
        x
      }
    }

    val delayedApp = new MyDelayedApp


  }
}
