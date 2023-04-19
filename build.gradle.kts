import org.jetbrains.grammarkit.tasks.GenerateLexerTask
import org.jetbrains.grammarkit.tasks.GenerateParserTask
import org.jetbrains.intellij.tasks.RunIdeTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val sourceBranch = "beta"

group = "com.flipperplz"
version = "1.0-${sourceBranch}"

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

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
  compileOnly(kotlin("stdlib-jdk8"))
  implementation("com.code-disaster.steamworks4j:steamworks4j:1.9.0-SNAPSHOT")
  implementation("com.code-disaster.steamworks4j:steamworks4j-server:1.9.0-SNAPSHOT")
}

configure<JavaPluginExtension> {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

java {
  targetCompatibility=JavaVersion.VERSION_17
  sourceCompatibility=JavaVersion.VERSION_17
  sourceSets.main {
    java.srcDirs(
      "src/${sourceBranch}/main/gen",
      "src/${sourceBranch}/main/java"
    )
  }
}


kotlin {
  sourceSets.main {
    kotlin.srcDirs("src/${sourceBranch}/main/kotlin")
  }
}

sourceSets {
  main {
    kotlin {
      srcDirs(
        "src/${sourceBranch}/main/kotlin"
      )
    }

    java {
      srcDirs(
        "src/${sourceBranch}/main/gen",
        "src/${sourceBranch}/main/java"
      )
    }

    resources {
      srcDirs(
        "src/${sourceBranch}/main/resources"
      )
    }
  }
}

val generateParamParser = tasks.register<GenerateParserTask>("generateParamParser") {
  sourceFile.set(file("src/${sourceBranch}/main/grammars/param/Param.bnf"))
  targetRoot.set("src/${sourceBranch}/main/gen/")
  pathToParser.set("/com/flipperplz/enfusionWorkbench/psi/languages/param/parser/ParamParser.java")
  pathToPsiRoot.set("/com/flipperplz/enfusionWorkbench/psi/languages/param/psi/")

  purgeOldFiles.set(true)
}
val generateParamLexer = tasks.register<GenerateLexerTask>("generateParamLexer") {
  sourceFile.set(file("src/${sourceBranch}/main/grammars/param/Param.flex"))
  targetDir.set("src/${sourceBranch}/main/gen/com/flipperplz/enfusionWorkbench/psi/languages/param/lexer/")
  targetClass.set("ParamLexer")
  purgeOldFiles.set(true)
}

val generateEnforceParser = tasks.register<GenerateParserTask>("generateEnforceParser") {
  sourceFile.set(file("src/${sourceBranch}/main/grammars/enforce/Enforce.bnf"))
  targetRoot.set("src/${sourceBranch}/main/gen/")
  pathToParser.set("/com/flipperplz/enfusionWorkbench/psi/languages/enforce/parser/EnforceParser.java")
  pathToPsiRoot.set("/com/flipperplz/enfusionWorkbench/psi/languages/enforce/psi/")

  purgeOldFiles.set(true)
}
val generateEnforceLexer = tasks.register<GenerateLexerTask>("generateEnforceLexer") {
  sourceFile.set(file("src/${sourceBranch}/main/grammars/enforce/Enforce.flex"))
  targetDir.set("src/${sourceBranch}/main/gen/com/flipperplz/enfusionWorkbench/psi/languages/enforce/lexer/")
  targetClass.set("EnforceLexer")
  purgeOldFiles.set(true)
}

tasks {
  val cleanupGenerated = register("cleanupGenerated") {
    delete("src/${sourceBranch}/main/gen")
  }

  val createGenerated = register("generateCode") {
    dependsOn(cleanupGenerated)
    dependsOn(generateParamParser, generateParamLexer)
    dependsOn(generateEnforceParser, /*generateEnforceLexer*/)
  }


  withType<KotlinCompile>().configureEach {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_17.toString()
      languageVersion = "1.8"
      apiVersion = "1.7"
      freeCompilerArgs = listOf("-Xjvm-default=all")
    }
    dependsOn(createGenerated)
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


  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}
