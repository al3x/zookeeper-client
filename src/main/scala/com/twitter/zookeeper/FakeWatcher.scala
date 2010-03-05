package com.twitter.zookeeperloadtest

import org.apache.zookeeper.{Watcher, WatchedEvent}


class FakeWatcher extends Watcher {
  def process(event: WatchedEvent) {
    // nop
  }
}
