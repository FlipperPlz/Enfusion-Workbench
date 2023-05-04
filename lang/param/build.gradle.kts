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

group = "com.flipperplz"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(kotlin("test"))
}

java {
  targetCompatibility=JavaVersion.VERSION_17
  sourceCompatibility=JavaVersion.VERSION_17
  sourceSets.main {
    java.srcDirs(
      "src/main/gen",
      "src/main/java"
    )
  }
}

tasks {
  test {
    useJUnitPlatform()
  }

  compileKotlin {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_17.toString()
      languageVersion = "1.8"
      apiVersion = "1.7"
      freeCompilerArgs = listOf("-Xjvm-default=all")
    }
    dependsOn(generateParser, generateLexer)
  }

  generateLexer {
    sourceFile.set(file("src/main/grammars/Param.flex"))
    targetDir.set("src/main/gen/com/flipperplz/enfusionWorkbench/languages/param/lexer/")
    targetClass.set("ParamLexer")
    purgeOldFiles.set(true)
  }

  generateParser {
    sourceFile.set(file("src/main/grammars/Param.bnf"))
    targetRoot.set("src/main/gen/")
    pathToParser.set("/com/flipperplz/enfusionWorkbench/languages/param/parser/ParamParser.java")
    pathToPsiRoot.set("/com/flipperplz/enfusionWorkbench/languages/param/psi/")

    purgeOldFiles.set(true)
  }
}

tasks.test {
  useJUnitPlatform()
}

kotlin {
  sourceSets.main {
    kotlin.srcDirs("src/main/kotlin")
  }
}


