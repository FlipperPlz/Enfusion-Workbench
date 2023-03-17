//import org.jetbrains.grammarkit.tasks.GenerateLexerTask
//import org.jetbrains.grammarkit.tasks.GenerateParserTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val sourceBranch = "beta"

plugins {
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.intellij") version "1.10.1"
}

apply {
    plugin("kotlin")
}

java.targetCompatibility=JavaVersion.VERSION_11
java.sourceCompatibility=JavaVersion.VERSION_11

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


    withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "11"
            languageVersion = "1.8"
            // see https://plugins.jetbrains.com/docs/intellij/using-kotlin.html#kotlin-standard-library
            apiVersion = "1.7"
            freeCompilerArgs = listOf("-Xjvm-default=all")
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
