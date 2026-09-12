# Graph Report - D:\AppModular\AppBase  (2026-09-12)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 427 nodes · 464 edges · 66 communities (50 shown, 16 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 2 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `7d8d2e7f`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- AppRepository
- Documentación Oficial — Base Reusable Arquitectura Multimodulo Feature-Based
- AppDataBase
- native-lib.cpp
- algorithm.h
- OtaFirestoreService
- SplashState
- REGLAS.md — AppBase
- DashboardEntity
- CloudManager
- ProfileModel
- CloudConnectionState
- InicioEntity
- MetricasManager
- DashboardHomeViewModel
- NubeModule
- .onCreate
- GoogleAuthManager
- Regla 4 — Módulo Basededatos como Gestor Central de Base de Datos
- ServicesManager
- AuthModule
- saveConfig
- BasededatosDashboard.kt
- ServiciosModule
- .provideAppContext
- DashboardRouter
- OtaDownloadWorker
- Regla 2 — Módulo Tema como Única Fuente de Estilos
- Regla 1 — Independencia de Módulos
- Regla 3 — VersiónCatalog (libs.versions.toml) como Única Fuente de Versiones
- ConfigModule
- Regla 5 — Nomenclatura de Archivos por Módulo
- ThemeModule
- AppBaseApplication
- BasededatosInicio.kt
- BasededatosTema.kt
- SplashRouter
- Regla 6 — Gestión de Permisos y Manifests
- Regla 8 — DI con Hilt y Koin
- UserModel.kt
- DashboardModule.kt
- OtaConfig.kt
- SplashModule.kt
- SplashModel.kt
- CloudModel.kt
- ProfileModule.kt
- Regla 13 — No Archivos Basura
- Regla 7 — Registro de Módulos

## God Nodes (most connected - your core abstractions)
1. `AppRepository` - 27 edges
2. `REGLAS.md — AppBase` - 17 edges
3. `PerfilEntity` - 11 edges
4. `UserEntity` - 11 edges
5. `AppDataBase` - 10 edges
6. `Documentación Oficial — Base Reusable Arquitectura Multimodulo Feature-Based` - 10 edges
7. `ConfigEntity` - 9 edges
8. `DashboardEntity` - 9 edges
9. `UserDao` - 8 edges
10. `CloudManager` - 8 edges

## Surprising Connections (you probably didn't know these)
- `saveConfig()` --references--> `AppConfig`  [EXTRACTED]
  basededatos/src/main/java/com/moto/project/basededatos/BasededatosConfiguracion.kt → configuracion/src/main/java/com/moto/project/configuracion/config/AppConfig.kt
- `filterActive()` --references--> `DashboardModel`  [EXTRACTED]
  basededatos/src/main/java/com/moto/project/basededatos/BasededatosDashboard.kt → dashboard/src/main/java/com/moto/project/dashboard/model/DashboardModel.kt
- `insertDashboardItem()` --references--> `DashboardModel`  [EXTRACTED]
  basededatos/src/main/java/com/moto/project/basededatos/BasededatosDashboard.kt → dashboard/src/main/java/com/moto/project/dashboard/model/DashboardModel.kt
- `toProfileModel()` --references--> `ProfileModel`  [EXTRACTED]
  basededatos/src/main/java/com/moto/project/basededatos/BasededatosPerfil.kt → perfil/src/main/java/com/moto/project/perfil/model/ProfileModel.kt
- `AppRepository` --references--> `DashboardEntity`  [EXTRACTED]
  basededatos/src/main/java/com/moto/project/basededatos/repository/AppRepository.kt → basededatos/src/main/java/com/moto/project/basededatos/entity/DashboardEntity.kt

## Import Cycles
- None detected.

## Communities (66 total, 16 thin omitted)

### Community 0 - "AppRepository"
Cohesion: 0.06
Nodes (11): ConfigDao, Flow, Flow, PerfilDao, Flow, UserDao, ConfigEntity, PerfilEntity (+3 more)

### Community 1 - "Documentación Oficial — Base Reusable Arquitectura Multimodulo Feature-Based"
Cohesion: 0.07
Nodes (26): 1. Estructura General, 2.1 Manifests Descentralizados, 2.2 Independencia de Gradle, 2.3 App como Ensamblador, 2.4 Inyección de Dependencias, 2.5 Inter-Module Dependency, 2.6 Navegación, 2.7 Catálogo de Versiones (+18 more)

### Community 2 - "AppDataBase"
Cohesion: 0.12
Nodes (7): AppDataBase, Flow, TemaDao, DatabaseModule, Context, TemaEntity, RoomDatabase

### Community 3 - "native-lib.cpp"
Cohesion: 0.15
Nodes (17): string, MetricResult, label, timestamp, value, string, MetricResult, label (+9 more)

### Community 4 - "algorithm.h"
Cohesion: 0.16
Nodes (14): calculateMean(), calculateMedian(), calculateMetrics(), calculateStandardDeviation(), MetricResult, vector, vector, gradientDescent() (+6 more)

### Community 5 - "OtaFirestoreService"
Cohesion: 0.14
Nodes (7): OtaModule, Context, OtaManager, UpdateCheckResult, Flow, OtaFirestoreService, OtaUpdate

### Community 6 - "SplashState"
Cohesion: 0.18
Nodes (11): Bundle, ComponentActivity, SplashActivity, SplashScreen(), Cargando, Error, StateFlow, Listo (+3 more)

### Community 7 - "REGLAS.md — AppBase"
Cohesion: 0.14
Nodes (13): Documentación de Normas Arquitectónicas, Regla 10 — C++ Nativo Solo en `metricas`, Regla 11 — iOS, Regla 12 — ProGuard/R8, Regla 14 — Navegación, Regla 15 — Archivos de Recursos por Módulo, Regla 9 — Build Variants, Reglas (+5 more)

### Community 8 - "DashboardEntity"
Cohesion: 0.21
Nodes (3): DashboardDao, Flow, DashboardEntity

### Community 9 - "CloudManager"
Cohesion: 0.21
Nodes (6): android, ConnectionState, CloudManager, Flow, Result, T

### Community 10 - "ProfileModel"
Cohesion: 0.18
Nodes (8): com, PerfilCollection, toEntity(), toProfileModel(), ProfileModel, Flow, Result, ProfileManager

### Community 11 - "CloudConnectionState"
Cohesion: 0.21
Nodes (11): Bool, Foundation, CloudConnectionState, connected, connecting, disconnected, error, MotoSharedState (+3 more)

### Community 12 - "InicioEntity"
Cohesion: 0.22
Nodes (3): InicioDao, Flow, InicioEntity

### Community 13 - "MetricasManager"
Cohesion: 0.27
Nodes (6): DoubleArray, Flow, MetricResult, MetricasManager, MetricResult, MetricsState

### Community 14 - "DashboardHomeViewModel"
Cohesion: 0.24
Nodes (6): DashboardHomeViewModel, DashboardUiState, StateFlow, DashboardItem(), DashboardScreen(), Modifier

### Community 15 - "NubeModule"
Cohesion: 0.20
Nodes (5): FirebaseFirestore, FirebaseRemoteConfig, FirebaseStorage, FirebaseMessaging, NubeModule

### Community 16 - ".onCreate"
Cohesion: 0.22
Nodes (5): Bundle, ComponentActivity, MainActivity, AppNavigation(), AppTheme()

### Community 17 - "GoogleAuthManager"
Cohesion: 0.22
Nodes (5): GoogleAuthManager, com, Flow, Result, AuthState

### Community 18 - "Regla 4 — Módulo Basededatos como Gestor Central de Base de Datos"
Cohesion: 0.25
Nodes (8): Convención de Nombres, Crear un Nuevo Archivo Basededatos, Descripción, Ejemplo de Archivo by Módulo, Estructura de Paquetes por Módulo, Regla 4 — Módulo Basededatos como Gestor Central de Base de Datos, Regla Adicional — Colecciones por Módulo, Reglas Específicas

### Community 19 - "ServicesManager"
Cohesion: 0.29
Nodes (4): ServiceState, Flow, Result, ServicesManager

### Community 20 - "AuthModule"
Cohesion: 0.29
Nodes (4): AuthModule, Context, FirebaseAuth, GoogleSignInClient

### Community 21 - "saveConfig"
Cohesion: 0.38
Nodes (5): ConfiguracionCollection, com, saveConfig(), toEntity(), AppConfig

### Community 22 - "BasededatosDashboard.kt"
Cohesion: 0.33
Nodes (5): DashboardCollection, filterActive(), insertDashboardItem(), Flow, DashboardModel

### Community 23 - "ServiciosModule"
Cohesion: 0.33
Nodes (3): FirebaseAuth, FirebaseMessaging, ServiciosModule

### Community 24 - ".provideAppContext"
Cohesion: 0.40
Nodes (3): AppModule, Context, MotoApplication

### Community 26 - "OtaDownloadWorker"
Cohesion: 0.40
Nodes (3): Result, OtaDownloadWorker, Worker

### Community 27 - "Regla 2 — Módulo Tema como Única Fuente de Estilos"
Cohesion: 0.40
Nodes (5): Cada Módulo Responsable de Tema, Descripción, Ejemplo de Uso Correcto, Regla 2 — Módulo Tema como Única Fuente de Estilos, Reglas Específicas

### Community 28 - "Regla 1 — Independencia de Módulos"
Cohesion: 0.40
Nodes (5): Descripción, Ejemplo, Excepción, Módulos Compartidos Permitidos, Regla 1 — Independencia de Módulos

### Community 29 - "Regla 3 — VersiónCatalog (libs.versions.toml) como Única Fuente de Versiones"
Cohesion: 0.40
Nodes (5): Descripción, Ejemplo, Estructura del Catálogo, Regla 3 — VersiónCatalog (libs.versions.toml) como Única Fuente de Versiones, Reglas Específicas

### Community 31 - "Regla 5 — Nomenclatura de Archivos por Módulo"
Cohesion: 0.50
Nodes (4): Ejemplo: módulo `dashboard`, Ejemplo: módulo `inicio`, Estructura de Carpetas por Módulo, Regla 5 — Nomenclatura de Archivos por Módulo

### Community 37 - "Regla 6 — Gestión de Permisos y Manifests"
Cohesion: 0.67
Nodes (3): Descripción, Regla 6 — Gestión de Permisos y Manifests, Reglas

### Community 38 - "Regla 8 — DI con Hilt y Koin"
Cohesion: 0.67
Nodes (3): Descripción, Regla 8 — DI con Hilt y Koin, Reglas

## Knowledge Gaps
- **80 isolated node(s):** `UserModel`, `ConfiguracionCollection`, `DashboardCollection`, `PerfilCollection`, `TemaCollection` (+75 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **16 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `AppRepository` connect `AppRepository` to `DashboardEntity`, `AppDataBase`, `InicioEntity`?**
  _High betweenness centrality (0.030) - this node is a cross-community bridge._
- **Why does `REGLAS.md — AppBase` connect `REGLAS.md — AppBase` to `Regla 6 — Gestión de Permisos y Manifests`, `Regla 8 — DI con Hilt y Koin`, `Regla 13 — No Archivos Basura`, `Regla 7 — Registro de Módulos`, `Regla 4 — Módulo Basededatos como Gestor Central de Base de Datos`, `Regla 2 — Módulo Tema como Única Fuente de Estilos`, `Regla 1 — Independencia de Módulos`, `Regla 3 — VersiónCatalog (libs.versions.toml) como Única Fuente de Versiones`, `Regla 5 — Nomenclatura de Archivos por Módulo`?**
  _High betweenness centrality (0.013) - this node is a cross-community bridge._
- **Why does `AppDataBase` connect `AppDataBase` to `AppRepository`, `DashboardEntity`, `InicioEntity`?**
  _High betweenness centrality (0.010) - this node is a cross-community bridge._
- **What connects `UserModel`, `ConfiguracionCollection`, `DashboardCollection` to the rest of the system?**
  _80 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `AppRepository` be split into smaller, more focused modules?**
  _Cohesion score 0.0647342995169082 - nodes in this community are weakly interconnected._
- **Should `Documentación Oficial — Base Reusable Arquitectura Multimodulo Feature-Based` be split into smaller, more focused modules?**
  _Cohesion score 0.07407407407407407 - nodes in this community are weakly interconnected._
- **Should `AppDataBase` be split into smaller, more focused modules?**
  _Cohesion score 0.11578947368421053 - nodes in this community are weakly interconnected._