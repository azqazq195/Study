import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"

    val kotlinVersion = "1.7.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

// TODO annotationProcessor vs kapt
dependencies {
    val querydslVersion = "5.0.0"

    // spring boot
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // swager
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
    // json
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    // ulid
    implementation("com.github.f4b6a3:ulid-creator:5.1.0")
    // h2 database
    implementation("com.h2database:h2")

    runtimeOnly("org.springframework.boot:spring-boot-devtools")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // kotest
    testImplementation("io.kotest:kotest-runner-junit5:4.4.3")
    testImplementation("io.kotest:kotest-assertions-core:4.4.3")
    testImplementation("io.kotest:kotest-property:4.4.3")
    testImplementation("io.kotest:kotest-extensions-spring:4.4.3")
    // mockk
    testImplementation("io.mockk:mockk:1.13.2")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    imageName.set("kopring")
    docker {
//        environment.set(mapOf("SPRING_PROFILES_ACTIVE" to "prod"))
        environment.set(mapOf("LC_ALL" to "C.UTF-8"))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        lifecycle {
            events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
            showStandardStreams = true
        }
        info.events = lifecycle.events
        info.exceptionFormat = lifecycle.exceptionFormat
    }

    // See https://github.com/gradle/kotlin-dsl/issues/836
    addTestListener(object : TestListener {
        override fun beforeSuite(suite: TestDescriptor) {}
        override fun beforeTest(testDescriptor: TestDescriptor) {
            println("Running test: ${testDescriptor.displayName}")
        }

        override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {}

        override fun afterSuite(suite: TestDescriptor, result: TestResult) {
            if (suite.parent == null) { // root suite
                println("------------------------")
                println("Test result: ${result.resultType}")
                println(
                    "Test summary: ${result.testCount} tests, " +
                            "${result.successfulTestCount} succeeded, " +
                            "${result.failedTestCount} failed, " +
                            "${result.skippedTestCount} skipped"
                )
            }
        }
    })
}
