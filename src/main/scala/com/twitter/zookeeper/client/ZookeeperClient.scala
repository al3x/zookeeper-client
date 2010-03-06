package com.twitter.zookeeper.client

import org.scala_tools.javautils.Imports._
import net.lag.configgy.{Config, Configgy}
import net.lag.logging.Logger
import org.apache.zookeeper.{CreateMode, KeeperException, Watcher, ZooKeeper}
import org.apache.zookeeper.data.{ACL, Stat}


class ZookeeperClient(watcher: Watcher, hostnamePortPairs: String) {
  Configgy.configure("src/main/resources/config.conf")

  private val log = Logger.get
  private val config = Configgy.config

  val sessionTimeout = config.getInt("session-timeout", 3000)

  private lazy val zk = new ZooKeeper(hostnamePortPairs, sessionTimeout, watcher)

  def close {
    zk.close()
  }

  def get(path: String): Array[Byte] = {
    val stat: Stat = zk.exists(path, false)
    zk.getData(path, false, stat)
  }

  // FIXME update to 2.8 Java collection conversions
  def getChildren(path: String): Seq[String] = {
    zk.getChildren(path, false).asScala
  }

  def create(path: String, data: Array[Byte], acl: java.util.List[ACL], createMode: CreateMode): String = {
    zk.create(path, data, acl, createMode)
  }

  def delete(path: String) {
    zk.delete(path, -1)
  }

  def isAlive: Boolean = {
    val result: Stat = zk.exists("/", false)  // do not watch

    if (result.getVersion >= 0) {
      true
    } else {
      false
    }
  }
}
