plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.example.phonehelper"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode(1)
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
    }
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(Dependencies.Kotlin.KOTLIN_STDLIB)

    implementation(Dependencies.AndroidX.CORE)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.MATERIAL)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Dependencies.AndroidX.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependencies.AndroidX.ACTIVITY_KTX)
    implementation(Dependencies.AndroidX.FRAGMENT_KTX)
    implementation(Dependencies.AndroidX.NAV_FRAGMENT)
    implementation(Dependencies.AndroidX.NAV_UI)

    implementation(Dependencies.Network.RETROFIT)
    implementation(Dependencies.Network.GSON)
    implementation(Dependencies.Network.OKHTTP)

    implementation(Dependencies.Hilt.CORE)
    implementation(Dependencies.Hilt.COMPILER)
    implementation(Dependencies.Hilt.PLUGIN)

    implementation(Dependencies.Coroutines.CORE)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")//flow importu için deneme


    implementation(Dependencies.UILibs.GLIDE)
    implementation(Dependencies.UILibs.GLIDE_PROCESSOR)
    implementation(Dependencies.UILibs.INTUIT_SDP)
    implementation(Dependencies.UILibs.INTUIT_SSP)

    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.EXT_JUNIT)
    androidTestImplementation(Dependencies.Test.ESPRESSO_CORE)
}