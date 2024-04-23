plugins {
    id(Dependencies.Plugins.android_application)
    id(Dependencies.Plugins.kotlin_android)
    id(Dependencies.Plugins.ksp) version Dependencies.Versions.ksp_version
    id(Dependencies.Plugins.serialization) version Dependencies.Versions.kotlin
}

android {
    namespace = Dependencies.Application.id
    compileSdk = Dependencies.Versions.compileSDK

    defaultConfig {
        applicationId = Dependencies.Application.id
        minSdk = Dependencies.Versions.minsdk
        targetSdk = Dependencies.Versions.targetsdk
        versionCode = Dependencies.Application.version_code
        versionName = Dependencies.Application.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = Dependencies.Application.releaseMinify
            isShrinkResources = Dependencies.Application.releaseMinify

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = Dependencies.Application.debugMinify
            isShrinkResources = Dependencies.Application.debugMinify

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Dependencies.Java.java_compile
        targetCompatibility = Dependencies.Java.java_compile
    }
    kotlinOptions {
        jvmTarget = Dependencies.Java.java_versions
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.kotlinCompiler
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.Dependencies.destinations_core)
    ksp(Dependencies.Dependencies.destinations_ksp)

    ksp(Dependencies.AnnotationProcessing.room_compiler)
    implementation(Dependencies.Dependencies.room_ktx)
    implementation(Dependencies.Dependencies.room_runtime)

    implementation(Dependencies.Dependencies.paging)
    implementation(Dependencies.Dependencies.pagingCompose)

    implementation(Dependencies.Dependencies.orbit_compose)
    implementation(Dependencies.Dependencies.orbit_viewModel)

    implementation(Dependencies.Dependencies.retrofit)
    implementation(Dependencies.Dependencies.converter_gson)
    implementation(Dependencies.Dependencies.kotlinJson)
    implementation(Dependencies.Dependencies.kotlinxSerializationConverter)

    implementation(Dependencies.Compose.permission)

    //android-core
    implementation(Dependencies.Dependencies.android_core_ktx)
    implementation(Dependencies.Dependencies.lifecycle_runtime)
    implementation(Dependencies.Dependencies.material)

    implementation(platform(Dependencies.Compose.composeBoom))
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeUiGraphics)
    implementation(Dependencies.Compose.composeFoundation)
    implementation(Dependencies.Compose.systemUiController)
    implementation(Dependencies.Compose.fullSetIconsCompose)

    //material
    implementation(Dependencies.Compose.material3)

    //preview
    implementation(Dependencies.Compose.toolingPreview)
    debugImplementation(Dependencies.Compose.debugUiTooling)

    //koin di
    implementation(Dependencies.Dependencies.koin_android)
    implementation(Dependencies.Dependencies.koin_compose)

    // Screen Size
    implementation(Dependencies.Dependencies.window)
}