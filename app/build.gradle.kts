import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.detekt)
    alias(libs.plugins.graphql)
    kotlin("plugin.serialization") version "2.0.10"
}

detekt {
    allRules = true
    buildUponDefaultConfig = true
}

apollo {
    service("service") {
        packageName.set("co.daresay.gitwatch")
        schemaFile.set(file("src/main/graphql/schema.graphqls"))
    }
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
localProperties.load(FileInputStream(localPropertiesFile))

val privateKey: String = localProperties.getProperty("private_key")
val privateAccessToken: String = localProperties.getProperty("private_acess_token")

android {
    namespace = "co.daresay.gitwatch"
    compileSdk = 34

    defaultConfig {
        applicationId = "co.daresay.gitwatch"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "0.0.1"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "PRIVATE_KEY", "\"${privateKey}\"")
            buildConfigField("String", "PRIVATE_ACCESS_TOKEN", "\"${privateAccessToken}\"")
        }
        debug {
            buildConfigField("String", "PRIVATE_KEY", "\"${privateKey}\"")
            buildConfigField("String", "PRIVATE_ACCESS_TOKEN", "\"${privateAccessToken}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.play.services.wearable)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.activity.compose)
    implementation(libs.core.splashscreen)

    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.material3.android)
    testImplementation(libs.koin.test)

    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.apollo.runtime)
    implementation(libs.okhttp)

    implementation(libs.kotlinx.datetime)

    implementation(libs.swiperefreshlayout)
    implementation(libs.navigation.compose)

    implementation(libs.jjwt)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.wear.tooling.preview)

    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.kiolk.detekt.rules)
}
