@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt.android)
    kotlin("kapt")

}

android {
    namespace = "com.esum.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 25

        testInstrumentationRunner = "com.esum.network.TestRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core:common"))


    implementation(libs.androidx.core.ktx)
    implementation(libs.appCompat)
    implementation(libs.material)
    implementation(libs.androidx.runner)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.test.ext)
//    androidTestImplementation(libs.espresso)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)

    //moshi
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin.convertor)




    //hilt
    implementation(libs.hilt.compose)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    androidTestImplementation(libs.hilt.instrumented.test)
    kaptAndroidTest(libs.hilt.test)

    testImplementation(libs.hilt.instrumented.test)
    kaptTest(libs.hilt.test)
}
kapt {
    correctErrorTypes = true
}