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
    }
}

//// Публикация артефакта
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                // Если вы публикуете AAR напрямую
                artifact("library/contract-release.aar")

                groupId = "com.github.arthur-morosov"
                artifactId = "axiom-sdk-api"
                version = "0.0.19"
            }
        }
    }
}