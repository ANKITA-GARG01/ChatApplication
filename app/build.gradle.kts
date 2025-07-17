import android.databinding.tool.writer.ViewBinder
import android.databinding.tool.writer.ViewBinding

plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")

//        id("org.jetbrains.kotlin.android.extensions") version "2.2.0"


}


android {
    namespace = "com.example.chat"
    compileSdk = 36


        buildFeatures {
            viewBinding = true
        }


    defaultConfig {
        applicationId = "com.example.chat"
        minSdk = 23


        //noinspection OldTargetApi
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
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:33.16.0"))



        // Add the dependency for the Firebase Authentication library
        // When using the BoM, you don't specify versions in Firebase library dependencies
        implementation("com.google.firebase:firebase-auth")

        // Also add the dependency for the Google Play services library and specify its version
        implementation(libs.play.services.auth)

    //firebase Realtime Database
    implementation(libs.firebase.database)
    implementation (libs.material)
    implementation(libs.lottie)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    implementation(libs.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}