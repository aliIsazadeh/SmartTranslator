// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("org.jetbrains.kotlin.kapt") version "2.1.0" // یا نسخه مشابه


}