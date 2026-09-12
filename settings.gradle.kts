pluginManagement { repositories { google(); mavenCentral(); gradlePluginPortal() } }
dependencyResolutionManagement { repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS); repositories { google(); mavenCentral(); gradlePluginPortal() } }
versionCatalogs { create("libs") { from(files("gradle/libs.versions.toml")) } }
rootProject.name = "AppBase"
include(":app"); include(":inicio"); include(":dashboard"); include(":nube"); include(":autenticacion"); include(":tema"); include(":configuracion"); include(":perfil"); include(":basededatos"); include(":servicios"); include(":metricas")
