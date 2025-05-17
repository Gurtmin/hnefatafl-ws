plugins {
    java
    id("org.jsonschema2pojo") version "1.1.3"
}

jsonSchema2Pojo {
    setSource(files("src/main/resources/schema"))
    targetPackage = "com.example.mongo.model"
//    targetDirectory = file("$buildDir/generated/src/main/java")
    targetDirectory = file("src/generated/java")
    isIncludeGeneratedAnnotation = true
    isUseJakartaValidation = true
}
