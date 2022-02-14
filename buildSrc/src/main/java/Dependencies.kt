import Versions.GLIDE_VERSION
import Versions.KOTLIN_VERSION
import Versions.LIFE_CYCLE_VERSION
import Versions.NAVIGATION_VERSION
import Versions.OKHTTP_VERSION
import Versions.RETROFIT_VERSION

object Versions {
    const val KOTLIN_VERSION = "1.5.30"
    const val NAVIGATION_VERSION = "2.3.5"
    const val LIFE_CYCLE_VERSION = "2.4.0-alpha02"
    const val RETROFIT_VERSION = "2.9.0"
    const val OKHTTP_VERSION = "5.0.0-alpha.2"
    const val GLIDE_VERSION = "4.12.0"
}

object Dependencies {

    object Kotlin {
        const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.7.0-alpha01"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.4.0-alpha03"
        const val MATERIAL = "com.google.android.material:material:1.4.0"
        const val LIFECYCLE_LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:$LIFE_CYCLE_VERSION"
        const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFE_CYCLE_VERSION"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.0-beta02"
        const val ACTIVITY_KTX = "androidx.activity:activity-ktx:1.3.0-rc01"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:1.4.0-alpha04"
        const val NAV_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION"
        const val NAV_UI = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_VERSION"
    }

    object Hilt {
        const val CORE = "com.google.dagger:hilt-android:2.37"
        const val PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:2.37"
        const val COMPILER = "com.google.dagger:hilt-android-compiler:2.37"
    }

    object Coroutines {
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0"
    }

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
        const val GSON = "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"
        const val OKHTTP = "com.squareup.okhttp3:logging-interceptor:$OKHTTP_VERSION"
    }

    object UILibs {
        const val GLIDE = "com.github.bumptech.glide:glide:$GLIDE_VERSION"
        const val GLIDE_PROCESSOR = "com.github.bumptech.glide:compiler:$GLIDE_VERSION"
        const val INTUIT_SDP = "com.intuit.ssp:ssp-android:1.0.6"
        const val INTUIT_SSP = "com.intuit.sdp:sdp-android:1.0.6"
        const val CONCAT_ADAPTER = "androidx.recyclerview:recyclerview:1.2.1"
        const val TEXT_INPUT_LAYOUT = "cz.ackee.ui:TextInputLayout:1.1.0"
    }

    object Test {
        const val MOCKITO = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
        const val MOCKITO_INLINE = "org.mockito:mockito-inline:3.11.2"
        const val COROUTINE_TEST_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0"
        const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0"
        const val ANDROIDX_CORE_TEST = "androidx.arch.core:core-testing:2.1.0"
        const val ANDROIDX_TEST = "androidx.test:core:1.0.0"
        const val TURBINE = "app.cash.turbine:turbine:0.5.1"
        const val ROBOLECTRIC = "org.robolectric:robolectric:4.6-alpha-1"
        const val HILT_ANDROID_TEST = "com.google.dagger:hilt-android-testing:2.37"
        const val HILT_COMPILER_TEST = "com.google.dagger:hilt-compiler:2.37"
        const val EXT_JUNIT="androidx.test.ext:junit:1.1.3"
        const val JUNIT="junit:junit:4.13.2"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"
    }

}