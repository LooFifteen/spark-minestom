plugins {
    `java-library`
    `maven-publish`
}

group = "dev.lu15"
version = "1.10-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://jitpack.io/")
}

dependencies {
    api("me.lucko:spark-minestom:${project.rootProject.version}") {
        version {
            branch = "master"
        }
    }
    compileOnlyApi("me.lucko:spark-common:${project.rootProject.version}") {
        version {
            branch = "master"
        }
    }
    compileOnlyApi("com.github.hollow-cube:minestom-ce:74ca1041f3")
    compileOnlyApi("org.jetbrains:annotations:24.0.1")

    testImplementation("com.github.hollow-cube:minestom-ce:74ca1041f3")
    testImplementation("org.slf4j:slf4j-simple:2.0.7")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "dev.lu15"
            artifactId = "spark-minestom"
            version = project.rootProject.version.toString()

            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "hypera"
            url = uri("https://repo.hypera.dev/snapshots/")
        }
    }
}