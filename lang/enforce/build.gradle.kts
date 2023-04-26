plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "1.8.10"
  id("org.jetbrains.intellij") version "1.13.2"
  id("org.jetbrains.grammarkit") version "2022.3.1"
}


intellij {
  version.set("2022.3.3")
  type.set("IC")
  plugins.set(listOf(

  ))
}

group = "com.flipperplz"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(kotlin("test"))
  implementation("com.google.guava:guava:29.0-jre")

}

tasks.test {
  useJUnitPlatform()
}

kotlin {
  jvmToolchain(11)
}



