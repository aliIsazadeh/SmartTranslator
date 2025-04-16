@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt.android)
    kotlin("kapt")
    alias(libs.plugins.room)



}
room {
    schemaDirectory("$projectDir/schemas")
}


android {
    namespace = "com.esum.database"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "com.esum.database.DatabaseTestRunner"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.appCompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso)
    //hilt
    implementation(libs.hilt.compose)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.instrumented.test)
    kaptAndroidTest(libs.hilt.test)
    testImplementation(libs.hilt.instrumented.test)
    kaptTest(libs.hilt.test)

    implementation(libs.converter.gson)

    //room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)
//    implementation(libs.room.compiler)
    testImplementation(libs.room.testing)


}
kapt {
    correctErrorTypes = true
}