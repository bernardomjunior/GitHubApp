object Versions {
    const val koin = "3.2.0"
    const val retrofit = "2.9.0"
    const val junit = "4.13.2"
    const val junitExt = "1.1.3"
    const val espresso = "3.4.0"
    const val coreKtx = "1.8.0"
    const val appcompat = "1.4.2"
    const val material = "1.6.1"
    const val constraintlayout = "2.1.4"
    const val navigation = "2.5.0"
    const val lifeCycle = "2.4.1"
    const val glide = "4.13.0"
    const val mockWebserver = "4.10.0"
    const val kotlinCoroutinesTest = "1.4.2"
    const val navigationTest = "2.4.2"
}

object Dependencies {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val koin =  "io.insert-koin:koin-android:${Versions.koin}"
    const val koinTest =  "io.insert-koin:koin-test:${Versions.koin}"
    const val koinTestJunit =  "io.insert-koin:koin-test-junit4:${Versions.koin}"

    const val junit = "junit:junit:${Versions.junit}"
    const val junitTest = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val mockWebserver = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebserver}"
    const val kotlinCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutinesTest}"
    const val kotlinCoroutinesAndroidTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutinesTest}"
    const val navigationTest = "androidx.navigation:navigation-testing:${Versions.navigationTest}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val viewModelLifeCycle =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    const val runtimeLifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
    const val navGraph = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}