import org.jetbrains.grammarkit.tasks.GenerateLexerTask
import org.jetbrains.grammarkit.tasks.GenerateParserTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val sourceBranch = "beta"

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
    id("org.jetbrains.intellij") version "1.10.1"
    id("org.jetbrains.grammarkit") version "2022.3.1"
}

group = "com.flipperplz"
version = "1.0-${sourceBranch}"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    implementation("com.code-disaster.steamworks4j:steamworks4j:1.9.0-SNAPSHOT")
    implementation("com.code-disaster.steamworks4j:steamworks4j-server:1.9.0-SNAPSHOT")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2022.1.4")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}

idea {
    module {
        generatedSourceDirs.add(file("src/${sourceBranch}/main/gen"))
    }
}

sourceSets {
    main {
        java.srcDirs("src/${sourceBranch}/main/gen")
        resources.srcDirs("src/${sourceBranch}/main/resources")
    }
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDirs("src/${sourceBranch}/main/kotlin")
        }
    }
}

idea {
    module {
        generatedSourceDirs.add(file("src/${sourceBranch}/main/gen"))
    }
}

tasks {


    val generateParamParser = register<GenerateParserTask>("generateParamParser") {
        sourceFile.set(file("src/${sourceBranch}/main/grammars/param/Param.bnf"))
        targetRoot.set("src/${sourceBranch}/main/gen/")
        pathToParser.set("/com/flipperplz/enfusionWorkbench/languages/param/parser/ParamParser.java")
        pathToPsiRoot.set("/com/flipperplz/enfusionWorkbench/languages/param/psi/")
        purgeOldFiles.set(true)
    }

    val generateParamLexer = register<GenerateLexerTask>("generateParamLexer") {
        sourceFile.set(file("src/${sourceBranch}/main/grammars/param/Param.flex"))
        targetDir.set("src/${sourceBranch}/main/gen/com/flipperplz/enfusionWorkbench/languages/param/lexer/")
        targetClass.set("ParamLexer")
        purgeOldFiles.set(true)
    }


    // Set the JVM compatibility versions
    val javaCompile = withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
        dependsOn(generateParamLexer)
        dependsOn(generateParamParser)
    }

    val kotlinCompile = withType<KotlinCompile>().configureEach {
        dependsOn(generateParamLexer)
        dependsOn(generateParamParser)
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    patchPluginXml {
        sinceBuild.set("221")
        untilBuild.set("231.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    clean {
        delete(file("/src/${sourceBranch}/main/gen"))
    }
}
