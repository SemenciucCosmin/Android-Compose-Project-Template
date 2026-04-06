import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_SRC_DIR_JAVA
import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_SRC_DIR_KOTLIN

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.detekt)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "com.example.androidcomposeprojecttemplate"
    compileSdk {
        version = release(libs.versions.compileSdk.get().toInt()) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.androidcomposeprojecttemplate"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // ANDROIDX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // COMPOSE
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // COIL
    implementation(platform(libs.coil.bom))
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil.gif)
    implementation(libs.coil.network)

    // DATA STORE
    implementation(libs.data.store)

    // DETEKT
    detektPlugins(libs.detekt.formatting)

    // KOIN
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.navigation)

    // KOTLIN
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.immutableCollections)
    implementation(libs.kotlinx.serialization.json)

    // NAVIGATION
    implementation(libs.androidx.navigation.ui)
    implementation(libs.navigation.compose)

    // NETWORK
    implementation(platform(libs.retrofit.bom))
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(platform(libs.okhttp3.bom))
    implementation(libs.okhttp3)

    // PAGING
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)

    // ROOM
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    androidTestImplementation(libs.androidx.room.testing)

    // STARTUP
    implementation(libs.androidx.startup.runtime)

    // MODULES
    implementation(projects.initializer)
    implementation(projects.ui.catalog)
    implementation(projects.ui.core)
}

detekt {
    source.setFrom(
        DEFAULT_SRC_DIR_JAVA,
        DEFAULT_SRC_DIR_KOTLIN,
        "${project.rootDir}/initializer/$DEFAULT_SRC_DIR_JAVA",
        "${project.rootDir}/initializer/$DEFAULT_SRC_DIR_KOTLIN",
        "${project.rootDir}/ui/catalog/$DEFAULT_SRC_DIR_JAVA",
        "${project.rootDir}/ui/catalog/$DEFAULT_SRC_DIR_KOTLIN",
        "${project.rootDir}/ui/core/$DEFAULT_SRC_DIR_JAVA",
        "${project.rootDir}/ui/core/$DEFAULT_SRC_DIR_KOTLIN",
    )

    buildUponDefaultConfig = true
    parallel = true
    autoCorrect = true
    config.setFrom("detekt/detekt-config.yml")
    baseline = file("detekt/detekt-baseline.xml")
}
