plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    alias(libs.plugins.hilt.android)


}

android {
    namespace = "com.esum.network"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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
    implementation(libs.okhttp)
    implementation(libs.converter.gson)
    implementation(libs.okhttp.interceptor)


    //moshi
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin.convertor)


    //dataStore
    implementation(libs.dataStore)


    //hilt
    implementation(libs.hilt.compose)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

//    androidTestImplementation(libs.hilt.instrumented.test)
//    kaptAndroidTest(libs.hilt.test)

//    testImplementation(libs.hilt.instrumented.test)
    kaptTest(libs.hilt.test)
}
kapt {
    correctErrorTypes = true
}