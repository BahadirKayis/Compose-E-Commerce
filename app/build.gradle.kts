plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.bahadir.tostbangcase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bahadir.tostbangcase"
        minSdk = 24
        targetSdk = 34
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
                "proguard-rules.pro",
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
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Core
    implementation("androidx.core:core-ktx:1.12.0")
    // Compose
    implementation("androidx.compose.material:material:1.5.1")
    implementation("androidx.compose.ui:ui-util:1.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Compose Preview
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    // Coroutine Image Loader (Coil)
    implementation("io.coil-kt:coil-compose:2.2.2")
    // Room
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")
    // Dagger-Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    annotationProcessor("com.google.dagger:dagger-android-processor:2.44")
    ksp("com.google.dagger:dagger-compiler:2.48") // Dagger compiler
    ksp("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.navigation:navigation-compose:2.7.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // okhttp
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    // Additional Views for Compose
    implementation("com.google.accompanist:accompanist-insets:0.17.0")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.25.1")
    implementation("com.google.accompanist:accompanist-pager:0.27.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.27.0")
    implementation("com.google.accompanist:accompanist-placeholder:0.27.0")
// Lottie
    implementation("com.airbnb.android:lottie-compose:4.2.0")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Material Icons
    implementation("androidx.compose.material:material-icons-extended:1.5.1")

    // Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")
}
