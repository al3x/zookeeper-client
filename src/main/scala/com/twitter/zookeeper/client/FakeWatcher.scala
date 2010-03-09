package com.twitter.zookeeper.client

import org.apache.zookeeper.{Watcher, WatchedEvent}


class FakeWatcher extends Watcher {
  def process(event: WatchedEvent) {
    // nop
  }
}
