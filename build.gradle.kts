plugins {
    java
    war
    id("org.springframework.boot") version "2.7.14"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "demo"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    testCompileOnly {
        extendsFrom(configurations.testAnnotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc") {
        exclude(group="com.zaxxer", module="HikariCP")
    }
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("com.alibaba:druid-spring-boot-starter:1.2.18")
    implementation("com.github.pagehelper:pagehelper-spring-boot-starter:1.4.7")
    implementation("com.ctrip.framework.apollo:apollo-client:2.0.1")
    implementation("com.google.guava:guava:32.1.1-jre")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.3.1")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("mysql:mysql-connector-java:5.1.34")
    annotationProcessor("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:2.3.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
