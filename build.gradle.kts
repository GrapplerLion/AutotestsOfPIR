plugins {
    id("java")
    id ("io.qameta.allure") version ("2.9.6")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

tasks {
    wrapper {
        gradleVersion = "7.3" // Используйте последнюю версию Gradle
        distributionType = Wrapper.DistributionType.ALL
    }
}

dependencies {
    testImplementation("com.codeborne:selenide:5.23.3")
    testImplementation("org.seleniumhq.selenium:selenium-chrome-driver:3.141.59")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.6.2")
    testImplementation("org.slf4j:slf4j-simple:2.0.9")

    testImplementation ("org.aspectj:aspectjweaver:1.9.5")
    testImplementation ("io.qameta.allure:allure-junit5:2.12.1")
    testImplementation ("io.qameta.allure:allure-commandline:2.12.1")
    testImplementation ("io.qameta.allure:allure-assertj:2.12.1")
    testImplementation ("io.qameta.allure:allure-rest-assured:2.12.1")
    testImplementation ("io.qameta.allure:allure-java-commons:2.12.1")
    testImplementation ("io.qameta.allure:allure-selenide:2.12.1")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
    testImplementation("com.codeborne:xls-test:1.7.0")
    testImplementation("org.apache.poi:poi-ooxml:5.2.3")

    testImplementation("junit:junit:4.13.2")

//    testImplementation("org.springframework.boot:spring-boot-starter-test:2.3.0")
//    testImplementation("org.springframework.boot:spring-boot-starter-web:2.3.0")
//
//    testImplementation("org.hibernate:hibernate-core")
//    testImplementation("org.hibernate:hibernate-gradle-plugin:5.6.7")
//    testImplementation("org.postgresql:postgresql:42.4.23"){
//        exclude(group = "com.google.protobuf", module = "protobuf-java")
//    }

}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
}

tasks.test {
    systemProperty("selenide.browser", System.getProperty("browser", "chrome"))
    systemProperty("selenide.headless", System.getProperty("headless", "false"))
    useJUnitPlatform()
}