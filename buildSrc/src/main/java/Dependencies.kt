object BuildConfig {
    const val APP_ID = "com.example.workoutapp"
    const val MIN_SDK = 23
    const val TARGET_SDK = 30
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
}

object AndroidX {
    private const val APP_COMPAT_VERSION = "1.2.0"
    private const val CORE_KTX_VERSION = "1.3.2"
    private const val RECYCLERVIEW_VERSION = "1.1.0"
    private const val NAVIGATION_FRAGMENT_VERSION = "2.1.0"
    private const val LIFECYCLE_VERSION = "2.2.0"
    private const val ROOM_VERSION = "2.2.5"
    private const val VIEW_PAGER2_VERSION = "1.0.0"
    private const val LEGACY_SUPPORT_VERSION = "1.0.0"
    private const val CONSTRAINT_LAYOUT_VERSION = "2.0.4"
    private const val CARD_VIEW_VERSION = "1.0.0"

    const val APP_COMPAT = "androidx.appcompat:appcompat:$APP_COMPAT_VERSION"
    const val CORE_KTX = "androidx.core:core-ktx:$CORE_KTX_VERSION"
    const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:$RECYCLERVIEW_VERSION"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_FRAGMENT_VERSION"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_FRAGMENT_VERSION"
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION"
    const val LIFECYCLE_EXTENSION =  "androidx.lifecycle:lifecycle-extensions:$LIFECYCLE_VERSION"
    const val ROOM = "androidx.room:room-runtime:$ROOM_VERSION"
    const val ROOM_COMPLIER = "androidx.room:room-compiler:$ROOM_VERSION"
    const val VIEW_PAGER2 = "androidx.viewpager2:viewpager2:$VIEW_PAGER2_VERSION"
    const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:$LEGACY_SUPPORT_VERSION"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION"
    const val CARD_VIEW = "androidx.cardview:cardview:$CARD_VIEW_VERSION"
}

object Kotlin {
    private const val KOTLIN_STB_VERSION = "1.4.10"

    const val KOTLIN_STB = "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_STB_VERSION"
}

object Dagger {
    private const val DAGGER_VERSION = "2.27"

    const val DAGGER = "com.google.dagger:dagger:$DAGGER_VERSION"
    const val DAGGER_ANDROID = "com.google.dagger:dagger-android-support:$DAGGER_VERSION"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:$DAGGER_VERSION"
    const val DAGGER_PROCESSOR = "com.google.dagger:dagger-android-processor:$DAGGER_VERSION"
}

object Rx {
    private const val RX_ANDROID_VERSION = "2.1.1"
    private const val RX_JAVA_VERSION = "2.2.20"
    private const val RX_KOTLIN_VERSION = "2.4.0"
    private const val RX_BINDING_VERSION = "3.1.0"

    const val RX_ANDROID = "io.reactivex.rxjava2:rxandroid:$RX_ANDROID_VERSION"
    const val RX_JAVA = "io.reactivex.rxjava2:rxjava:$RX_JAVA_VERSION"
    const val RX_KOTLIN = "io.reactivex.rxjava2:rxkotlin:$RX_KOTLIN_VERSION"
    const val RX_BINDING = "com.jakewharton.rxbinding3:rxbinding:$RX_BINDING_VERSION"
    const val RX_BINDING_RECYCLERVIEW = "com.jakewharton.rxbinding3:rxbinding-recyclerview:$RX_BINDING_VERSION"
}

object Retrofit {
    private const val RETROFIT_VERSION = "2.7.2"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"
    const val RETROFIT_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:$RETROFIT_VERSION"
}

object AutoDispose {
    private const val AUTODISPOSE_VERSION = "1.4.0"

    const val AUTODISPOSE = "com.uber.autodispose:autodispose:$AUTODISPOSE_VERSION"
    const val AUTODISPOSE_ANDROID = "com.uber.autodispose:autodispose-android-archcomponents:$AUTODISPOSE_VERSION"
}