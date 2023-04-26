plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "1.8.10"
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
