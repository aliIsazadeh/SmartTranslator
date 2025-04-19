plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.compose.compiler)

}
android {
    namespace = "com.esum.feature.card.presentation"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "com.esum.feature.card.presentation.PresentTestRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    packagingOptions {
        resources {
            excludes += setOf(
                "META-INF/gradle/incremental.annotation.processors",
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md"
            )
        }
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
    implementation(project(":feature:translating:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:feature-api"))
    androidTestImplementation(project(":core:database"))

    implementation(libs.androidx.runner)
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

    implementation(libs.androidx.compose.materialWindow)

    androidTestImplementation(libs.hilt.test)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test)

    debugImplementation(libs.tooling.ui)
    debugImplementation(libs.manifest.test)
    debugImplementation(libs.leakcanary)

    implementation(libs.kotlinx.metadata.jvm)


    //lottie
    implementation(libs.lottie.compose)
    implementation(libs.lottie)

    //mockk
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk)




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