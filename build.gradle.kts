plugins {
    id("java")
    id ("io.qameta.allure") version ("2.11.2")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

//tasks {
//    wrapper {
//        gradleVersion = "8.4" // Используйте последнюю версию Gradle
//        distributionType = Wrapper.DistributionType.ALL
//    }
//}

dependencies {

//    implementation("org.seleniumhq.selenium:selenium-java:31.0.1")
//    testImplementation("org.testng:testng:7.7.0")

    testImplementation("com.codeborne:selenide:7.3.2")
    testImplementation("org.seleniumhq.selenium:selenium-chrome-driver:4.20.0")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.7.0")
    testImplementation("org.slf4j:slf4j-simple:2.0.13")

    testImplementation ("org.aspectj:aspectjweaver:1.9.22")
    testImplementation ("io.qameta.allure:allure-junit5:2.27.0")
    testImplementation ("io.qameta.allure:allure-bom:2.27.0")
    testImplementation ("io.qameta.allure:allure-commandline:2.27.0")
    testImplementation ("io.qameta.allure:allure-assertj:2.27.0")
    testImplementation ("io.qameta.allure:allure-rest-assured:2.27.0")
    testImplementation ("io.qameta.allure:allure-java-commons:2.27.0")
    testImplementation ("io.qameta.allure:allure-selenide:2.27.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation("com.codeborne:xls-test:1.7.1")
    testImplementation("org.apache.poi:poi-ooxml:5.2.5")

    implementation("com.fasterxml.jackson.core:jackson-core:2.17.1")


//    testImplementation("junit:junit:4.13.2")

//    testImplementation("org.springframework.boot:spring-boot-starter-test:2.3.0")
//    testImplementation("org.springframework.boot:spring-boot-starter-web:2.3.0")
//
//    testImplementation("org.hibernate:hibernate-core")
//    testImplementation("org.hibernate:hibernate-gradle-plugin:5.6.7")
//    testImplementation("org.postgresql:postgresql:42.4.23"){
//        exclude(group = "com.google.protobuf", module = "protobuf-java")
//    }
//    implementation(kotlin("script-runtime"))

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = JavaVersion.VERSION_17.toString()
    targetCompatibility = JavaVersion.VERSION_17.toString()
}

tasks.test {
    systemProperty("selenide.browser", System.getProperty("browser", "chrome"))
    systemProperty("selenide.headless", System.getProperty("headless", "false"))
    useJUnitPlatform()
}


