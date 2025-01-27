plugins {
    kotlin("js") version "1.9.0"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation(npm("tensorflow", "^3.0.0")) // TensorFlow.js npm package
}

kotlin {
    js {
        browser {
            testTask {
                useMocha()
            }
        }
    }
}
