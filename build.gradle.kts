plugins {
    val indraVersion = "2.0.6"
    id("net.kyori.indra") version indraVersion
    id("net.kyori.indra.publishing") version indraVersion
    id("net.kyori.indra.checkstyle") version indraVersion
}

group = "love.broccolai"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

indra {
    javaVersions {
        target(17)
        minimumToolchain(17)
    }
}

dependencies {
    testImplementation("com.google.truth", "truth", "1.1.3")
    testImplementation("com.google.truth.extensions", "truth-java8-extension", "1.1.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly( "org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
