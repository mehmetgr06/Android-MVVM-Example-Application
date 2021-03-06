plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.mgstudio.phonehelper"
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

    flavorDimensions("version")
    productFlavors {
        create("prod") {
            versionName = "0.1"
            buildConfigField("String","BASE_URL","\"https://covid-193.p.rapidapi.com/\"")
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
    kapt(Dependencies.Hilt.COMPILER)

    implementation(Dependencies.Coroutines.CORE)

    implementation(Dependencies.UILibs.GLIDE)
    implementation(Dependencies.UILibs.GLIDE_PROCESSOR)
    implementation(Dependencies.UILibs.INTUIT_SDP)
    implementation(Dependencies.UILibs.INTUIT_SSP)
    implementation(Dependencies.UILibs.CONCAT_ADAPTER)
    implementation(Dependencies.UILibs.TEXT_INPUT_LAYOUT)

    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    testImplementation(Dependencies.Test.JUNIT)
    testImplementation(Dependencies.Test.MOCKITO)
    testImplementation(Dependencies.Test.MOCKITO_INLINE)
    testImplementation(Dependencies.Test.COROUTINE_TEST)
    testImplementation(Dependencies.Test.COROUTINE_TEST_ANDROID)
    testImplementation(Dependencies.Test.ANDROIDX_CORE_TEST)
    testImplementation(Dependencies.Test.TURBINE)
    testImplementation(Dependencies.Test.ROBOLECTRIC)
    testImplementation(Dependencies.Test.ANDROIDX_TEST)

    kaptAndroidTest(Dependencies.Test.HILT_COMPILER_TEST)

    androidTestImplementation(Dependencies.Test.HILT_ANDROID_TEST)
    androidTestImplementation(Dependencies.Test.EXT_JUNIT)
    androidTestImplementation(Dependencies.Test.ESPRESSO_CORE)
}