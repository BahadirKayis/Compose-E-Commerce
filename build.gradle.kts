buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.0")
//        classpath("com.google.gms:google-services:4.3.15")
//        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
//        classpath("com.google.firebase:perf-plugin:1.4.2")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.5.3" apply false
}
