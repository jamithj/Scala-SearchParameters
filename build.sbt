organization := "monash"

name := "CanonicalSearchParameters"

version := "0.2.0"

scalaVersion := "2.10.4"

resolvers ++= Seq("snapshots" at "https://artifactory.its.monash.edu/artifactory/repo",
"release" at "https://artifactory.its.monash.edu/artifactory/repo"
)

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

scalacOptions ++= Seq("-deprecation", "-unchecked")

publishMavenStyle := true

credentials += Credentials("Artifactory Realm","artifactory.its.monash.edu","checkin","checkin")

publishTo := Some("monash-artifactory" at "https://artifactory.its.monash.edu/artifactory/libs-release-local")

libraryDependencies ++= {
  Seq(
    "org.specs2" %% "specs2" % "1.13" % "test",
    "com.novocode" % "junit-interface" % "0.9" % "test"
  )
}
