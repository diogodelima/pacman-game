plugins {
    id("java")
}

group = "pt.dioguin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    val lombok = "org.projectlombok:lombok:1.18.24"
    compileOnly(lombok)
    annotationProcessor(lombok)
}

tasks.test {
    useJUnitPlatform()
}