import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "smooks-play2-xml-to-jpa"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
      
    // Smooks dependencies added
    "org.milyn" % "milyn-smooks-core" % "1.5.1",
    "org.milyn" % "milyn-smooks-javabean" % "1.5.1",

    // Webjars dependencies added 
    "org.webjars" %% "webjars-play" % "2.1.0-3",
    
    // webjar components added	
    "org.webjars" % "jquery" % "1.10.2",
    "org.webjars" % "bootstrap" % "3.0.0",
    
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
      
    // jboss repository resolver added
    resolvers += "JBoss repository" at "http://repository.jboss.org/nexus/content/groups/public-jboss/"
 
  )

}
