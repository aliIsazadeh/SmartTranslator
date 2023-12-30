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
