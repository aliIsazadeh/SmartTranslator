pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }

    }
}

rootProject.name = "SmartTranslator"
include(":app")
include(":core:common")
include(":core:feature-api")
include(":core:network")
include(":core:database")
include(":feature:card")
include(":feature:translator:data")
include(":feature:card:data")
include(":feature:profile:data")
include(":feature:image-convertor:data")
include(":feature:card:domain")
include(":feature:card:presentation")
include(":core:ui")
include(":feature:translator:domain")
include(":feature:translator:presentation")
include(":feature:translating:data")
include(":feature:translating:domain")
include(":feature:image-convertor:domain")
include(":feature:image-convertor:presentation")
include(":feature:translating:presentation")
include(":core:web-socket")
