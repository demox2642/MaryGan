import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.uiImplentation() {
    AndroidX.getAll().forEach { implementation(it) }
    Navigation.getAll().forEach { implementation(it) }
    implementation(Coil.coil)
    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-compiler:2.42")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
}
fun DependencyHandler.domainImplentation() {
    implementation(Network.retrofitConvertor)
    // implementation(AndroidX.paging)
    Coroutines.getAll().forEach { implementation(it) }
    Network.getAll().forEach { implementation(it) }
}
fun DependencyHandler.domainNetworkImplentation() {
    Network.getAll().forEach { implementation(it) }
}

fun DependencyHandler.dataImplementation() {
    Network.getAll().forEach { implementation(it) }
    implementation(AndroidX.pagingCompose)
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
}

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)
