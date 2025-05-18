plugins {
    kotlin("jvm") version "1.9.23"
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.23"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

configurations {
    val openapiGenerator by configurations.creating
//    create("openapiGenerator")
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.15")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    add("openapiGenerator", "org.openapitools:openapi-generator-cli:7.4.0")
}

tasks.register<JavaExec>("generateApi") {
    group = "data"
    description = "Generuje kód z OpenAPI specifikace"
    classpath = configurations.named("openapiGenerator").get()
    mainClass.set("org.openapitools.codegen.OpenAPIGenerator")
    args = listOf(
        "generate",
        "-g", "spring",
        "-i", "src/main/resources/openapi/api.yaml",
        "-o", "./",
        "--additional-properties=useSpringBoot=true,interfaceOnly=true,modelPackage=com.example.generated.api.model,apiPackage=com.example.generated.api.controller,basePackage=com.example.generated.api,useJakartaEe=true"
    )
}

tasks.register<JavaExec>("generateFrontendApi") {
    group = "data"
    description = "Generuje kód z OpenAPI specifikace"
    classpath = configurations.named("openapiGenerator").get()
    mainClass.set("org.openapitools.codegen.OpenAPIGenerator")
    args = listOf(
        "generate",
        "-g", "typescript-fetch",
        "-i", "src/main/resources/openapi/api.yaml",
        "-o",  buildDir.resolve("generated/frontend_objects").toString(),
//    targetDirectory = file("$buildDir/generated/src/main/java")
        "--additional-properties=supportsES6=true,typescriptThreePlus=true,modelPropertyNaming=camelCase"
    )
}

tasks {
    val bootJar by getting(org.springframework.boot.gradle.tasks.bundling.BootJar::class) {
        // Optionally configure the JAR name or other settings
        mainClass.set("com.example.hnefatafl.HnefataflBeApplication") // Rep
        archiveFileName.set("hnefatafl-ws.jar")
        // Additional configurations if needed
    }
}

tasks.register<Exec>("generatePojo") {
    group = "data"
    description = "Generuje POJO třídy z JSON schema přes externí skript"
    commandLine = listOf(
        "./gradlew.bat",
        "-b", "generate-pojo.gradle.kts",
        "generateJsonSchema2Pojo"
    )
}

sourceSets {
    named("main") {
        java.srcDir("src/generated/java")
//        java.srcDir("build/generated/src/main/java")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().named("compileTestKotlin") {
    kotlinOptions.jvmTarget = "17"
}



//plugins {
//    kotlin("jvm") version "1.9.23"
//    id 'org.springframework.boot' version '3.2.3'
//    id 'io.spring.dependency-management' version '1.1.4'
//    id 'org.jsonschema2pojo' version '1.1.3'
//    id 'org.jetbrains.kotlin.jvm' version '1.9.23'
//    id 'org.jetbrains.kotlin.plugin.spring' version '1.9.23'
//}
//
//group = 'com.example'
//version = '0.0.1-SNAPSHOT'
//sourceCompatibility = JavaVersion.VERSION_17
//
//repositories {
//    mavenCentral()
//}
//
//configurations {
//    openapiGenerator
//}
//
//dependencies {
//    implementation 'com.fasterxml.jackson.core:jackson-databind'
//    implementation "com.fasterxml.jackson.module:jackson-module-kotlin"
//    implementation 'io.swagger.core.v3:swagger-annotations:2.2.15'
//    implementation 'jakarta.validation:jakarta.validation-api:3.0.2'
//    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
//    implementation "org.jetbrains.kotlin:kotlin-reflect"
//    implementation 'org.jetbrains:annotations:23.0.0'
//    implementation 'org.openapitools:jackson-databind-nullable:0.2.6'
//    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
//    implementation 'org.springframework.boot:spring-boot-starter-web'
//    testImplementation 'org.springframework.boot:spring-boot-starter-test'
//    openapiGenerator 'org.openapitools:openapi-generator-cli:7.4.0'
//}
//
//task generateApi(type: JavaExec) {
//    group = 'data'
//    description = 'Generuje kód z OpenAPI specifikace'
//    classpath = configurations.openapiGenerator
//    mainClass.set('org.openapitools.codegen.OpenAPIGenerator')
//
//    args = [
//            'generate',
//            '-g', 'spring',
//            '-i', 'src/main/resources/openapi/api.yaml',
//            '-o', "$buildDir/generated",
//            '--additional-properties=useSpringBoot=true,interfaceOnly=true,modelPackage=com.example.api.model,apiPackage=com.example.api.controller,basePackage=com.example.api,useJakartaEe=true'
//    ]
//}
//
//task generateFrontendApi(type: JavaExec) {
//    group = 'data'
//    description = 'Generuje kód z OpenAPI specifikace'
//    classpath = configurations.openapiGenerator
//    mainClass.set('org.openapitools.codegen.OpenAPIGenerator')
//
//    args = [
//            'generate',
//            '-g', 'typescript-fetch',
//            '-i', 'src/main/resources/openapi/api.yaml',
//            '-o', "$buildDir/generated-frontend",
//            '--additional-properties=supportsES6=true,typescriptThreePlus=true,modelPropertyNaming=camelCase'
//    ]
//}
//
//jsonSchema2Pojo {
//    source = files("src/main/resources/schema")
//    targetPackage = "com.example.mongo.model"
//    targetDirectory = file("$buildDir/generated/src/main/java")
//    includeGeneratedAnnotation = true
//    useJakartaValidation = true
//}
//
////task generateKotlinFromSchema(type: Exec){
////    group = 'data'
////    description = 'Generuje Kotlin třídy z JSON Schema přes quicktype'
////    def npxCmd = System.getenv('NVM_BIN') ? "${System.getenv('NVM_BIN')}/npx" : 'npx'
////
////    getLogger().debug( "1 npxCmd: $npxCmd")
////    doFirst {
////        if (!exec { commandLine npxCmd, '--version' }) {
////            throw new GradleException("npx is not installed or not in PATH.")
////        }
////    }
////    getLogger().debug( "2 npxCmd: $npxCmd")
////
////    commandLine npxCmd, 'quicktype',
////            'src/main/resources/schema/Game.json',
////            '--lang', 'kotlin',
////            '--top-level', 'Game',
////            '--out', "$buildDir/generated/schema/Game.kt"
////}
//
//sourceSets {
//    main {
//        java {
//            srcDir 'build/generated/src/main/java'
//        }
//    }
//}
//
//tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).configureEach {
//    kotlinOptions {
//        jvmTarget = "17"
//    }
//}
////compileKotlin {
////    kotlinOptions {
////        jvmTarget = "17"
////    }
////}
//
//compileTestKotlin {
//    kotlinOptions {
//        jvmTarget = "17"
//    }
//}
