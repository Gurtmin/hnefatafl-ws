plugins {
    java
    id("org.jsonschema2pojo") version "1.1.3"
}

jsonSchema2Pojo {
    setSource(files("src/main/resources/schema"))
    targetPackage = "com.example.generated.mongo"
//    targetDirectory = file("$buildDir/generated/src/main/java")
    targetDirectory = file("src/main/java")
    isIncludeGeneratedAnnotation = true
    isUseJakartaValidation = true
}
