import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {

    id("java")

    id("com.github.johnrengelman.shadow") version "8.1.1"

    id("io.freefair.lombok") version "8.13"

    application
    jacoco
    checkstyle
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("hexlet.code.App")
}

dependencies {

// https://mvnrepository.com/artifact/io.javalin/javalin
    implementation("io.javalin:javalin:6.5.0")

// https://mvnrepository.com/artifact/io.javalin/javalin-bundle
    implementation("io.javalin:javalin-bundle:6.5.0")

// https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    implementation("org.slf4j:slf4j-simple:2.0.17")

// JTE
    implementation("gg.jte:jte:3.1.16")

    implementation("io.javalin:javalin-rendering:6.5.0")

    implementation("com.h2database:h2:2.2.220")
// https://mvnrepository.com/artifact/com.zaxxer/HikariCP
    implementation("com.zaxxer:HikariCP:6.2.1")



// https://mvnrepository.com/artifact/org.assertj/assertj-core
    testImplementation("org.assertj:assertj-core:3.27.3")

    // https://mvnrepository.com/artifact/org.junit/junit-bom
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
        //     testImplementation(platform("org.junit:junit-bom:5.12.1"))


    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter
   // testImplementation("org.junit.jupiter:junit-jupiter:5.12.1")
    testImplementation("org.junit.jupiter:junit-jupiter")

// https://mvnrepository.com/artifact/com.konghq/unirest-java
   implementation("com.konghq:unirest-java:4.0.0-RC2")
    // https://mvnrepository.com/artifact/com.konghq/unirest-java-core
   // compileOnly("com.konghq:unirest-java-core:4.4.5")

    // https://mvnrepository.com/artifact/com.konghq/unirest-java-bom
    implementation("com.konghq:unirest-java-bom:4.4.5")

    // https://mvnrepository.com/artifact/org.jsoup/jsoup
    implementation("org.jsoup:jsoup:1.19.1")

// https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.5")


}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

tasks.test {
    useJUnitPlatform()
    // https://technology.lastminute.com/junit5-kotlin-and-gradle-dsl/
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        // showStackTraces = true
        // showCauses = true
        showStandardStreams = true
    }
}
