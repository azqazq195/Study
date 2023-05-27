import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    kotlin("jvm") version "1.8.20"
    /**
     * kotlin-spring 은 all-open 위에 래핑됩니다.
     */
    kotlin("plugin.spring") version "1.8.20"
    /**
     * kotlin-jpa 는 no-arg 위에 래핑됩니다.
     * 플러그인은 @Entity, @Embeddable 및 @MappedSuperclass no-arg 주석을 자동으로 지정합니다.
     * @link <a href="https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support">JPA Support</a>
     */
    kotlin("plugin.jpa") version "1.8.20"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

val asciidoctorExt: Configuration by configurations.creating
val snippetsDir by extra { "build/generated-snippets" }

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // database
    implementation("mysql:mysql-connector-java:8.0.32")
    implementation("org.flywaydb:flyway-core:9.17.0")
    implementation("org.flywaydb:flyway-mysql:9.17.0")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "org.mockito")
    }
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    testImplementation("it.ozimov:embedded-redis:0.7.2")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("org.springframework.security:spring-security-test:6.0.3")

    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }

    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    test {
        useJUnitPlatform()
        outputs.dir(snippetsDir)
    }

    asciidoctor {
        dependsOn(test)
        inputs.dir(snippetsDir)
        configurations(asciidoctorExt.name)
        baseDirFollowsSourceFile()
    }

    register<Copy>("copyDocs") {
        dependsOn(asciidoctor)
        from("${asciidoctor.get().outputDir}")
        into("src/main/resources/static/docs")
    }

    build {
        dependsOn(asciidoctor)
    }

    bootJar {
        dependsOn(asciidoctor)
        from("${asciidoctor.get().outputDir}/index.html") {
            into("static/docs")
        }
    }
}
