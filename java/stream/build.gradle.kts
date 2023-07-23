plugins {
    id("java")
    id("me.champeau.jmh") version "0.7.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    jmh("org.openjdk.jmh:jmh-core:1.36")
    testImplementation("org.openjdk.jmh:jmh-core:1.36")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

jmh {
    // fork.set(1): 벤치마크를 실행하는 JVM 프로세스의 수를 설정합니다.
    // 설정된 값만큼 각 벤치마크가 별도의 JVM 프로세스에서 독립적으로 실행됩니다.
    // 이렇게 하면 각각의 벤치마크 실행이 서로 영향을 미치지 않도록 할 수 있습니다.
    fork.set(1)

    // iterations.set(1): 실제 벤치마크를 반복 실행하는 횟수를 설정합니다.
    // 설정된 값만큼 각 벤치마크가 반복해서 실행되며, 이때의 실행 결과를 기록합니다.
    // 여러 번 반복 실행하여 얻은 결과를 평균내거나 분석하여,
    // 벤치마크 대상 코드의 성능을 좀 더 정확하게 측정할 수 있습니다.
    iterations.set(5)

    // warmupIterations.set(1): "예열" 벤치마크를 반복 실행하는 횟수를 설정합니다.
    // 설정된 값만큼 각 벤치마크를 실행하기 전에 먼저 반복 실행합니다.
    // 이 예열 실행은 JVM이 최적화를 수행하게 하여 실제 벤치마크 실행에서는
    // 최적화된 코드가 실행되도록 하는 역할을 합니다.
    warmupIterations.set(2)
}