plugins {
    id("java")
    id("application")
}

group = "com.superalice"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Lombok
    implementation("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    // Commons-Cli
    implementation("commons-cli:commons-cli:1.5.0")

    // Logger
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("org.slf4j:slf4j-simple:2.0.9")

    // Jackson - Json Utils
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.superalice.Main")
}