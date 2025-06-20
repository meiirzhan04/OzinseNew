import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlin-android")
    kotlin("kapt")
    alias(libs.plugins.google.gms.google.services)/*
    id("com.google.gms.google-services")*/
}

android {
    namespace = "com.example.ozinsenew"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ozinsenew"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation("com.google.accompanist:accompanist-placeholder-material:0.32.0")
    implementation("com.google.accompanist:accompanist-flowlayout:0.30.1")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.10.2")
    implementation("androidx.room:room-runtime:2.7.1")
    implementation("androidx.room:room-ktx:2.7.1")
    implementation(libs.firebase.firestore)
    kapt("androidx.room:room-compiler:2.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation ("com.google.accompanist:accompanist-pager:0.33.2-alpha")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.33.2-alpha")
    implementation( "androidx.lifecycle:lifecycle-livedata-ktx:2.9.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.8.2")
    annotationProcessor("androidx.room:room-compiler:2.7.1")
    implementation ("androidx.datastore:datastore-preferences:1.1.7")
    implementation ("androidx.compose.ui:ui:1.8.2")
    implementation ("com.google.code.gson:gson:2.11.0")
    implementation ("androidx.navigation:navigation-compose:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.9.1")
    implementation("com.google.firebase:firebase-auth-ktx:23.2.1")
    implementation("androidx.datastore:datastore-preferences:1.1.7")/*
    implementation ("com.google.firebase:firebase-auth-ktx:31.2.0")*/
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}