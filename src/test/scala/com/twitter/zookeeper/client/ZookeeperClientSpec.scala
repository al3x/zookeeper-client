package com.twitter.zookeeperloadtest

import org.scala_tools.javautils.Imports._
import org.apache.zookeeper.{CreateMode, Watcher, WatchedEvent}
import org.apache.zookeeper.CreateMode._
import org.apache.zookeeper.KeeperException.NoNodeException
import org.apache.zookeeper.data.{ACL, Id}
import org.specs._


object ZookeeperClientSpec extends Specification {
  "ZookeeperClient" should {
    val watcher = new FakeWatcher
    val zkClient = new ZookeeperClient(watcher, "localhost:2181")

    doLast {
      zkClient.close
    }

    // TODO need a doFirst to ensure that a Zookeeper server is running
    // before proceeding with the other tests, ala Robey's Ostrich stuff

    "be able to be instantiated with a FakeWatcher" in {
      zkClient mustNot beNull
    }

    "connect to local Zookeeper server and retrieve version" in {
      zkClient.isAlive mustBe true
    }

    "get data at a known-good specified path" in {
      val results: Array[Byte] = zkClient.get("/")
      results.size must beGreaterThanOrEqualTo(0)
    }

    "get data at a known-bad specified path" in {
      zkClient.get("/thisdoesnotexist") must throwA[NoNodeException]
    }

    "get list of children" in {
      zkClient.getChildren("/") must notBeEmpty
    }

    "create a node at a specified path" in {
      val data: Array[Byte] = Array(0x63)
      val id = new Id("world", "anyone")
      val acl = new ACL(0, id)
      val aclList = List[ACL](acl).asJava
      val createMode = EPHEMERAL

      zkClient.create("/foo", data, aclList, createMode) mustEqual "/foo"
      zkClient.delete("/foo")
    }
  }
}

