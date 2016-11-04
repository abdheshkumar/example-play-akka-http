name := "example_assignment"

version := "1.0"

lazy val `example_assignment` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

val akkaVersion = "2.4.11"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "com.typesafe.akka" %%  "akka-actor"              % akkaVersion,
  "com.typesafe.akka" %%  "akka-remote"             % akkaVersion,
  "com.typesafe.akka" %%  "akka-multi-node-testkit" % akkaVersion,
  "com.typesafe.akka" %%  "akka-contrib"            % akkaVersion,
  "com.typesafe.akka" %%  "akka-http-core"          % akkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json-experimental"  % akkaVersion,
  "com.typesafe.akka" %%  "akka-http-experimental"  % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j"      % akkaVersion,
  "ch.qos.logback"    %  "logback-classic" % "1.1.3",
  "com.typesafe.akka" %%  "akka-testkit"            % akkaVersion  % Test,
  "org.scalatest"     %%  "scalatest"               % "2.2.6"      % Test,
  specs2 % Test )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  