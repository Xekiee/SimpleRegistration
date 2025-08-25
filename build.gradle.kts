plugins {
    id("java")
}

group = "me.faun"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    implementation("com.formdev:flatlaf:3.4")
    implementation("org.apache.commons:commons-csv:1.10.0")
    implementation("com.google.inject:guice:7.0.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.11.0")
}
tasks.test {
    useJUnitPlatform()
}