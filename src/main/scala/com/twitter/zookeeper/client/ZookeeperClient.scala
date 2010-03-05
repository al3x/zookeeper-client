package com.twitter.zookeeperloadtest

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

  private lazy val zkClient = new ZooKeeper(hostnamePortPairs, sessionTimeout, watcher)

  def close {
    send { zk => zk.close() }
  }

  def get(path: String): Array[Byte] = {
    send { zk =>
      val stat: Stat = zk.exists(path, false)
      zk.getData(path, false, stat)
    }
  }

  // FIXME update to 2.8 Java collection conversions
  def getChildren(path: String): Seq[String] = {
    send { zk =>
      zk.getChildren(path, false).asScala
    }
  }

  def create(path: String, data: Array[Byte], acl: java.util.List[ACL], createMode: CreateMode): String = {
    send { zk =>
      zk.create(path, data, acl, createMode)
    }
  }

  def delete(path: String) {
    send { zk =>
      zk.delete(path, -1)
    }
  }

  def isAlive: Boolean = {
    val result: Stat = send { zk =>
      zk.exists("/", false)  // do not watch
    }

    if (result.getVersion >= 0) {
      true
    } else {
      false
    }
  }

  private def send[T](f: ZooKeeper => T): T = {
    try {
      f(zkClient)
    } catch {
      case e: KeeperException => {
        log.error(e, "Error performing Zookeeper operation")
        throw e
      }
    }
  }
}
