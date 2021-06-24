plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    id("org.jetbrains.kotlin.kapt") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "1.5.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.32"
    id("com.github.davidmc24.gradle.plugin.avro") version "1.2.0"
}

version = "0.1"
group = "com.epsilon.orders"

val kotlinVersion = project.properties["kotlinVersion"]
repositories {
    mavenCentral()
    maven("https://packages.confluent.io/maven/")
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.epsilon.orders.*")
    }
}

dependencies {
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.discovery:micronaut-discovery-client")
    implementation("io.micronaut.kafka:micronaut-kafka")
    implementation("io.confluent:kafka-avro-serializer:6.0.0")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.apache.logging.log4j:log4j-core:2.14.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("org.apache.logging.log4j:log4j-api:2.14.1")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
    implementation("io.micronaut:micronaut-validation")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

}


application {
    mainClass.set("com.epsilon.orders.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

avro {
    isCreateSetters.set(false)
    isGettersReturnOptional.set(true)
    isOptionalGettersForNullableFieldsOnly.set(true)
    fieldVisibility.set("PRIVATE")
}
