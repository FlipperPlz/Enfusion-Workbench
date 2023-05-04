plugins {
  id("java")
  id("idea")
  id("org.jetbrains.kotlin.jvm") version "1.8.10"
  id("org.jetbrains.intellij") version "1.13.2"
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
  implementation(project(":libs:bisutils"))
  implementation(project(":lang:enforce"))
  implementation(project(":lang:param"))
  implementation(project(":vfs:pbo"))
}

tasks.test {
  useJUnitPlatform()
}

kotlin {
}



