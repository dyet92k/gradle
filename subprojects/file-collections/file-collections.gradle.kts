/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    id("gradlebuild.distribution.api-java")
}

dependencies {
    implementation(project(":baseServices"))
    implementation(project(":baseServicesGroovy"))
    implementation(project(":coreApi"))
    implementation(project(":files"))
    implementation(project(":modelCore"))
    implementation(project(":logging"))
    implementation(project(":native"))

    implementation(libs.slf4jApi)
    implementation(libs.groovy)
    implementation(libs.guava)
    implementation(libs.commonsIo)
    implementation(libs.commonsLang)
    implementation(libs.inject)

    testImplementation(project(":processServices"))
    testImplementation(project(":resources"))
    testImplementation(project(":snapshots"))
    testImplementation(testFixtures(project(":core")))
    testImplementation(testFixtures(project(":coreApi")))
    testImplementation(testFixtures(project(":modelCore")))

    testFixturesImplementation(project(":baseServices"))
    testFixturesImplementation(project(":coreApi"))
    testFixturesImplementation(project(":native"))

    testFixturesImplementation(libs.guava)

    testRuntimeOnly(project(":distributionsCore")) {
        because("Tests instantiate DefaultClassLoaderRegistry which requires a 'gradle-plugins.properties' through DefaultPluginModuleRegistry")
    }
    integTestDistributionRuntimeOnly(project(":distributionsCore"))
}

strictCompile {
    ignoreRawTypes() // raw types used in public API
}

classycle {
    // Some cycles have been inherited from the time these classes were in :core
    excludePatterns.set(listOf("org/gradle/api/internal/file/collections/"))
}
