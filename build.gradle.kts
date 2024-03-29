import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.flipperplz"
version = "1.0-beta"

plugins {
  id("java")
  id("idea")
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

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
  compileOnly(kotlin("stdlib-jdk8"))
  implementation(project(":libs:bisutils"))
  implementation(project(":lang:enforce"))
  implementation(project(":lang:param"))
  implementation(project(":vfs:pbo"))
  implementation(project(":sdk:dayz"))
}

configure<JavaPluginExtension> {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
  sourceSets.main {
    kotlin.srcDirs("idea/main/kotlin")
  }
}

sourceSets {
  main {
    kotlin {
      srcDirs(
        "idea/main/kotlin"
      )
    }
    resources {
      srcDirs(
        "idea/main/resources"
      )
    }
  }
}

tasks {


  withType<KotlinCompile>().configureEach {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_17.toString()
      languageVersion = "1.8"
      apiVersion = "1.7"
      freeCompilerArgs = listOf("-Xjvm-default=all")
    }
  }

  patchPluginXml {
    sinceBuild.set("222")
    untilBuild.set("232.*")
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  jar {
    doFirst {
      from(zipTree(project(":libs:bisutils").tasks.getByName("jar").outputs.files.singleFile).asFileTree)
      from(zipTree(project(":vfs:pbo").tasks.getByName("jar").outputs.files.singleFile).asFileTree)
      from(zipTree(project(":lang:param").tasks.getByName("jar").outputs.files.singleFile).asFileTree)
      from(zipTree(project(":lang:enforce").tasks.getByName("jar").outputs.files.singleFile).asFileTree)
      from(zipTree(project(":sdk:dayz").tasks.getByName("jar").outputs.files.singleFile).asFileTree)

    }
   }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}
