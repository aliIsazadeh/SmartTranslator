object Version {

    const val core = "1.12.0"
    const val lifeCycleRuntime = "2.6.2"
    const val composeActivity = "1.8.1"
    const val composeBom = "2023.08.00"
    const val junit = "4.13.2"
    const val androidJunit = "1.1.5"
    const val espresso = "3.5.1"
    const val appCompat = "1.6.1"
    const val androidMaterial = "1.11.0"
    const val retrofit = "2.9.0"
    const val okHttp = "4.1.0"
    const val hilt = "2.48"
    const val navVersion = "2.7.6"
    const val coil = "2.5.0"
    const val viewModelLifeCycle = "1.0.0-alpha03"
    const val hiltCompiler = "1.0.0-alpha03"

}

object Deps {
    const val core = "androidx.core:core-ktx:${Version.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val androidMaterial = "com.google.android.material:material:${Version.androidMaterial}"
}

object CoroutinesLifeCycleScope {
    const val lifeCycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifeCycleRuntime}"
}

object JetPackCompose {
    const val composeActivity = "androidx.activity:activity-compose:${Version.composeActivity}"
    const val composeBom = "androidx.compose:compose-bom:${Version.composeBom}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeGraphics = "androidx.compose.ui:ui-graphics"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial = "androidx.compose.material3:material3"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Version.navVersion}"

}

object TestImplementation {
    const val junit = "junit:junit:${Version.junit}"
}

object AndroidTestImplementation {
    const val junit = "androidx.test.ext:junit:${Version.androidJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
    const val composeBom = "androidx.compose:compose-bom:${Version.composeBom}"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4"
}

object DebugImplementation {
    const val toolingUi = "androidx.compose.ui:ui-tooling"
    const val manifestTest = "androidx.compose.ui:ui-test-manifest"
}

object Retrofit {

    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Version.okHttp}"
}

object Coil {
    const val coilImplementation = "io.coil-kt:coil-compose:${Version.coil}"
}

object ViewModel {
}


object DaggerHilt {

    const val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Version.hilt}"
    const val viewModelLifeCycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Version.viewModelLifeCycle}"
    const val viewModelCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"

    const val hiltCompose = "androidx.hilt:hilt-navigation-compose:${Version.hiltCompiler}"


}