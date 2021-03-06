/*
scalafmt: {
  style = defaultWithAlign
  maxColumn = 150
  align.preset = most
  align.tokens.add = [
    { code = ":=", owner = "Term.ApplyInfix" }
    { code = "+=", owner = "Term.ApplyInfix" }
    { code = "++=", owner = "Term.ApplyInfix" }
    { code = "~=", owner = "Term.ApplyInfix" }
  ]
  version = 2.7.5
}
 */

val scalaCompilerVersion = "2.13.5"

val catsVersion      = "2.6.1"
val scalaTestVersion = "3.2.9"

lazy val catsCore  = "org.typelevel" %% "cats-core" % catsVersion
lazy val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion

lazy val root = project
  .in(file("."))
  .enablePlugins(JmhPlugin)
  .settings(name := "algorithms", moduleName := "algorithms")
  .settings(noPublish, compilation)
  .settings(
    libraryDependencies      ++= Seq(
      catsCore,
      scalaTest % Test
    ),
    Jmh / sourceDirectory     := (Test / sourceDirectory).value,
    Jmh / classDirectory      := (Test / classDirectory).value,
    Jmh / dependencyClasspath := (Test / dependencyClasspath).value,
    Jmh / compile             := (Jmh / compile).dependsOn(Test / compile).value,
    Jmh / run                 := (Jmh / run).dependsOn(Jmh / Keys.compile).evaluated,
    Test / fork               := true
  )

lazy val noPublish = Seq(publishLocal := {}, publish := {}, publishArtifact := false)

lazy val compilation = {
  import sbt.Keys._
  import sbt._

  Seq(
    scalaVersion                   := scalaCompilerVersion,
    scalacOptions                  ~= { options: Seq[String] => options.filterNot(Set("-Wself-implicit")) },
    Compile / doc / scalacOptions ++= Seq("-no-link-warnings")
  )
}
