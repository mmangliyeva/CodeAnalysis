plugins {
    kotlin("jvm") version "1.9.23"
    application
}
group="org.example"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.javaparser:javaparser-symbol-solver-core:3.25.10")
    implementation("com.github.javaparser:javaparser-core-serialization:3.25.10")
    implementation("com.github.javaparser:javaparser-core:3.25.10")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.23")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.9.23")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()

}
application {
    mainClass.set("org.example.MainKt")
}

kotlin {
    jvmToolchain(17)
}


