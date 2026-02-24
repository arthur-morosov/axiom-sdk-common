import org.gradle.api.publish.maven.MavenPublication

plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.library)
    `maven-publish`
}

android {
    namespace = "ru.alfapos"
    compileSdk = 36 // Укажите актуальную версию
    defaultConfig {
        minSdk = 24
//        consumerProguardFiles("consumer-rules.pro") // Правила ProGuard
    }

    // Настройка публикации AAR
    publishing {
        singleVariant("release") {
//            withSourcesJar() // Исходники (опционально)
//            withJavadocJar() // Документация (опционально)
        }
    }
}

//// Публикация артефакта
publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.arthur-morosov"  // Ваш GitHub username или организация
            artifactId = "axiom-sdk-common"        // Имя артефакта
            version = "0.0.4"                      // Версия релиза

            artifact("library/common-release.aar")
//            from(components["common-release.aar"])            // Берет скомпилированный AAR

            // Метаданные (для Maven Central, для GitHub опционально)
//            pom {
//                name.set("Axiom SDK Common")
//                description.set("Common utilities for Axiom SDK")
//                url.set("https://github.com/arthur-morosov/axiom-sdk-common")
//                licenses {
//                    license {
//                        name.set("Proprietary")
//                        url.set("https://example.com/license")
//                    }
//                }
//            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/arthur-morosov/axiom-sdk-common")
//            credentials {
//                username = System.getenv("GITHUB_ACTOR")
//                password = System.getenv("GITHUB_TOKEN")
//            }
        }
    }
}