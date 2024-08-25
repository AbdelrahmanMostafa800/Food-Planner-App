plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.mealmate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mealmate"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation ("androidx.room:room-rxjava2:2.6.0")
    implementation ("androidx.room:room-rxjava3:2.4.2")
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")

    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation ("io.reactivex.rxjava3:rxjava:3.1.5")

    implementation (platform("com.google.firebase:firebase-bom:31.5.0"))
    implementation ("com.google.firebase:firebase-analytics")
    implementation ("com.google.firebase:firebase-auth:22.0.0")
    implementation ("com.google.android.gms:play-services-auth:20.0.0")
    implementation ("com.google.firebase:firebase-firestore-ktx:25.1.0")

    implementation ("androidx.navigation:navigation-fragment:2.5.3")
    implementation ("androidx.navigation:navigation-ui:2.5.3")

    implementation ("com.airbnb.android:lottie:6.1.0")

    implementation ("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.11.0")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.annotation)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.firebase.auth)
    implementation(libs.legacy.support.v4)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}