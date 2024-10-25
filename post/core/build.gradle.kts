plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "seungkyu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation(project(":post:persistence"))

    //WEBFLUX
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    //MONGO
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")

    //lombok
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    //COROUTINE
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx3")

    //SPRING_SECURITY
    implementation("org.springframework.boot:spring-boot-starter-security")

    //AOP
    implementation("org.springframework.boot:spring-boot-starter-aop:3.1.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}