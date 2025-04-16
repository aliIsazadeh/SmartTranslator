plugins {
//    alias(libs.plugins.androidApplication)

    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.compose.compiler)

}

android {
    namespace = "com.esum.smarttranslator"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.esum.smarttranslator"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        freeCompilerArgs += listOf(
            "-Xcontext-receivers",
            "-Xopt-in=kotlin.RequiresOptIn"
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
//    stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
}

dependencies {

    implementation(project(":feature:card:presentation"))
    implementation(project(":feature:card:domain"))
    implementation(project(":feature:card:data"))
    implementation(project(":feature:translating:domain"))
    implementation(project(":feature:translating:data"))
    implementation(project(":core:common"))
    implementation(project(":core:feature-api"))
    implementation(project(":feature:translator:presentation"))
    implementation(project(":feature:image-convertor:presentation"))
    implementation(project(":feature:translating:presentation"))


    implementation(libs.androidx.compose.materialWindow)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.graphics)
    implementation(libs.compose.preview)
    implementation(libs.compose.material)
    implementation(libs.androidx.compose.materialWindow)


    debugImplementation(libs.leakcanary)



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.tooling.ui)
    debugImplementation(libs.manifest.test)


    //lottie
    implementation(libs.lottie.compose)
    implementation(libs.lottie)

    //hilt
    implementation(libs.hilt.compose)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.instrumented.test)
    kaptAndroidTest(libs.hilt.test)
    kaptTest(libs.hilt.test)

    //cropper
    implementation(libs.cropper)
}
kapt {
    correctErrorTypes = true
}