plugins {
    id("java")
    id("application")
}

group = "ru.vsu.bardakova"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("org.flywaydb:flyway-core:10.10.0")
    implementation("org.flywaydb:flyway-database-postgresql:10.10.0")
}

application {
    mainClass = "ru.vsu.bardakova.Main"
    applicationDefaultJvmArgs = listOf(
        "-Dfile.encoding=UTF-8",
        "-Dstdout.encoding=UTF-8",
        "-Dstderr.encoding=UTF-8"
    )
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.test {
    useJUnitPlatform()
}