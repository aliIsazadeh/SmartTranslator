@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.compose.compiler)

}

android {
    namespace = "com.esum.feature.image_convertor.presentation"
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
    buildFeatures {
        compose = true
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


    implementation(project(":core:ui"))
    implementation(project(":feature:card:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:feature-api"))
    implementation(libs.androidx.runner)
    androidTestImplementation(project(":core:database"))


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.graphics)
    implementation(libs.compose.preview)
    implementation(libs.coil)
    implementation(libs.coil.gif)
    implementation(libs.material.icons)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.compose.material3)



    implementation(libs.androidx.compose.materialWindow)

    androidTestImplementation(libs.hilt.test)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test)

    debugImplementation(libs.tooling.ui)
    debugImplementation(libs.manifest.test)

    //camera
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)
    implementation(libs.camera2)

    //firebase
    implementation(libs.mlkit)
    implementation(libs.text.recognition)
    implementation(libs.text.recognition.common)

    implementation(libs.kotlinx.metadata.jvm)

    //lottie
    implementation(libs.lottie.compose)
    implementation(libs.lottie)

    //permission
    implementation(libs.systemuicontroller)
    implementation(libs.permissions)

    //cropper
    implementation(libs.cropper)


    //hilt
    implementation(libs.hilt.compose)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.instrumented.test)
    kspAndroidTest(libs.hilt.test)
}

//kapt {
//    correctErrorTypes = true
//}