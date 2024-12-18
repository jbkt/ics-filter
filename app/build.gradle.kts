plugins {
    kotlin("jvm") version "2.0.20"
    application
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Tests
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.assertj:assertj-core:3.26.3")

    // Logging
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.2")

    // Args parsing
    implementation("com.github.ajalt.clikt:clikt:5.0.2")

    // iCal parsing
    implementation("org.mnode.ical4j:ical4j:4.0.6")
}

application {
    mainClass.set("net.catte.ical.filter.FilterCli")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
