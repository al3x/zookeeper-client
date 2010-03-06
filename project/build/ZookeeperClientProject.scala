import sbt._
import Process._


class ZookeeperClientProject(info: ProjectInfo) extends DefaultProject(info) {
  // Maven repositories
  val scalaToolsTesting = "testing.scala-tools.org" at "http://scala-tools.org/repo-releases/"
  val powerMock = "powermock-api" at "http://powermock.googlecode.com/svn/repo/"
  val mavenDotOrg = "repo1" at "http://repo1.maven.org/maven2/"
  val scalaToolsReleases = "scala-tools.org" at "http://scala-tools.org/repo-releases/"
  val reucon = "reucon" at "http://maven.reucon.com/public/"
  val lagDotNet = "lag.net" at "http://www.lag.net/repo/"
  val oauthDotNet = "oauth.net" at "http://oauth.googlecode.com/svn/code/maven"
  val javaDotNet = "download.java.net" at "http://download.java.net/maven/2/"
  val jBoss = "jboss-repo" at "http://repository.jboss.org/maven2/"
  val nest = "nest" at "http://www.lag.net/nest/"

  // dependencies
  val specs = "org.scala-tools.testing" % "specs" % "1.6.2"
  val vscaladoc = "org.scala-tools" % "vscaladoc" % "1.1-md-3"
  val markdownj = "markdownj" % "markdownj" % "1.0.2b4-0.3.0"
  val slf4jApi = "org.slf4j" % "slf4j-api" % "1.5.8"
  val slf4jLog = "org.slf4j" % "slf4j-log4j12" % "1.5.8"
  val log4j = "apache-log4j" % "log4j" % "1.2.15"
  val commonsLogging = "commons-logging" % "commons-logging" % "1.1"
  val commonsLang = "commons-lang" % "commons-lang" % "2.2"
  val oro = "oro" % "oro" % "2.0.7"
  val configgy = "net.lag" % "configgy" % "1.4.7"
  val mockito = "org.mockito" % "mockito-core" % "1.8.1"
  val xrayspecs = "com.twitter" % "xrayspecs" % "1.0.5"
  val hamcrest = "org.hamcrest" % "hamcrest-all" % "1.1"
  val asm = "asm" % "asm-all" % "2.2"
  val objenesis = "org.objenesis" % "objenesis" % "1.1"
  val javautils = "org.scala-tools" % "javautils" % "2.7.4-0.1"
  val ostrich = "com.twitter" % "ostrich" % "1.1.6"
}
