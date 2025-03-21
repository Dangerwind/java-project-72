plugins {
    id("java")
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"

    id("io.freefair.lombok") version "8.13"
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
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("io.javalin:javalin:6.1.3")
    implementation("io.javalin:javalin-bundle:6.1.3")

    implementation("org.slf4j:slf4j-simple:2.0.9")

// JTE
    implementation("gg.jte:jte:3.1.16")

    implementation("io.javalin:javalin-rendering:6.5.0")

    implementation("com.h2database:h2:2.2.220")
// https://mvnrepository.com/artifact/com.zaxxer/HikariCP
    implementation("com.zaxxer:HikariCP:6.2.1")


}

tasks.test {
    useJUnitPlatform()
}