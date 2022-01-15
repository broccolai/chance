plugins {
    `java-library`
}

repositories {
    mavenCentral()
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
