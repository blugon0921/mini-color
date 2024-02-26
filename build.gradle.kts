plugins {
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.jetbrains.dokka") version "1.5.0"
    `maven-publish`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

val repoUser = project.properties["repoUser"] as String
val repoPassword = project.properties["repoPassword"] as String

val mcVersion = "1.20.4"
val kotlinVersion = kotlin.coreLibrariesVersion

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:${mcVersion}-R0.1-SNAPSHOT")
}

extra.apply {
    set("ProjectName", project.name)
    set("ProjectVersion", project.version)
    set("KotlinVersion", kotlinVersion)
    set("MinecraftVersion", mcVersion.split(".").subList(0, 2).joinToString("."))
}

tasks {
    javadoc {
        options.encoding = "UTF-8"
    }

    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }

    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    create<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")
        dependsOn("dokkaHtml")
        from("$buildDir/dokka/html")
    }

    jar {
        archiveBaseName.set(project.name) //Project Name
        archiveFileName.set("${project.name}-${project.version}.jar") //Build File Name
        archiveVersion.set(project.version.toString()) //Version
        from(sourceSets["main"].output)
    }

    shadowJar {
        archiveBaseName.set(project.name) //Project Name
        archiveFileName.set("${project.name}-${project.version}.jar") //Build File Name
        archiveVersion.set(project.version.toString()) //Version
        from(sourceSets["main"].output)
    }

    artifacts {
        publishing {
            repositories {
                maven {
                    val mavenUrl = "https://repo.blugon.kr/repository/maven-" +
                            if(project.version.toString().endsWith("SNAPSHOT")) "snapshots/"
                            else "releases/"
                    url = uri(mavenUrl)
                    credentials {
                        username = repoUser
                        password = repoPassword
                    }
                }
            }
            publications {
                create<MavenPublication>("maven") {
                    artifact("build/libs/${project.name}-${project.version}.jar") {
                        extension = "jar"
                    }
                }
            }
        }
    }
}