import kotlin.reflect.full.memberProperties

object AndroidX {
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.material3}"
    const val composeMaterialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val workRuntime = "androidx.work:work-runtime-ktx:${Versions.workVersion}"
    const val composeConstraint = "androidx.constraintlayout:constraintlayout-compose:${Versions.condtraint}"
    const val paging = "androidx.paging:paging-runtime:${Versions.pagingVersion}"
    const val pagingCompose = "androidx.paging:paging-compose:1.0.0-alpha14"
    fun getAll() = AndroidX::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()
}

object Di {
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltTestUnit = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
}

object Accompanist {
    const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
    const val flowLayout = "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}"
    const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    const val viewPager = "com.google.accompanist:accompanist-pager:0.18.0"
}

object Navigation {
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigationVersion}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
    fun getAll() = Navigation::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()
}

object Network {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitConvertor = "com.squareup.retrofit2:converter-gson:${Versions.retrofitConvVersion}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLoggerVersion}"
    fun getAll() = Network::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()
}

object Coroutines {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutinesCore}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutinesAndroidVersion}"
    const val viewmodelKTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtxVersion}"
    fun getAll() = Coroutines::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()
}

object Lottie {
    const val lottie = "com.airbnb.android:lottie-compose:5.0.3"
}

object Room {
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomKTX = "androidx.room:room-ktx:${Versions.room}"
    const val roomPaging = "androidx.room:room-paging:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
}

object Coil {
    const val coil = "io.coil-kt:coil-compose:2.2.2"
}
