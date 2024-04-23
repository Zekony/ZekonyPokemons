buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Plugins.android_build_gradle)
        classpath(Dependencies.Plugins.kotlin_version)
    }
}

allprojects {

    repositories {
        google()
        mavenCentral()
    }
}