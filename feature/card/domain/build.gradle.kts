@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)


}

android {
    namespace = "com.esum.feature.card.domain"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()



        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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



    implementation(project(":core:database"))
    implementation(project(":core:common"))
    implementation(project(":core:feature-api"))


    implementation(libs.androidx.core.ktx)
    implementation(libs.appCompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso)

    implementation(libs.kotlinx.metadata.jvm)


    //hilt
    implementation(libs.hilt.compose)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.instrumented.test)
    kspAndroidTest(libs.hilt.test)
    kspTest(libs.hilt.test)
}
//kapt {
//    correctErrorTypes = true
//}