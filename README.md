# AppBase

## Documentación Oficial — Base Reusable Arquitectura Multimodulo Feature-Based

---

### 1. Estructura General

Este proyecto sigue una arquitectura **multimodulo estricta** basada en **feature-based modularization**. Cada módulo es una mini-aplicación independiente con su propio `build.gradle`, `AndroidManifest.xml` y paquete de código fuente.

**Total de módulos: 11**

| Módulo | Tipo | Descripción |
|--------|------|-------------|
| `app` | Ensamblador | Punto de entrada. No tiene lógica de negocio. Solo llama a los módulos. |
| `inicio` | Feature | Splash screen y pantalla de login. Redirige al dashboard según autenticación. |
| `dashboard` | Feature | Panel principal con navegación inferior. Muestra widgets y contenido. |
| `nube` | Compartido | Servicios cloud de Firebase: Firestore, Storage, Messaging, RemoteConfig. |
| `autenticacion` | Compartido | Autenticación con Google OAuth y Firebase Auth. |
| `tema` | Compartido | Variables visuales globales: colores, tipografía, `AppTheme`. |
| `configuracion` | Compartido | Configuración global singleton (`AppConfig`). Tiempo de splash, URLs, etc. |
| `perfil` | Compartido | Gestión de perfil de usuario. |
| `basededatos` | Compartido | Base de datos Room con SQLite, offline-first. DAOs, Entities, Repository. |
| `servicios` | Compartido | Conexión intermódulos con Firebase. |
| `metricas` | C++ Nativo | Módulo NDK/CMake para métricas, matemáticas y algoritmos complejos. |

---

### 2. Reglas de Arquitectura

#### 2.1 Manifests Descentralizados
Cada módulo tiene su propio `AndroidManifest.xml` en `src/main/`. Declara sus propias actividades, servicios y permisos específicos. El `app` solo declara el `MainActivity` y la clase `Application`.

#### 2.2 Independencia de Gradle
Cada módulo tiene su propio `build.gradle`. El `settings.gradle.kts` (raíz) es el **único índice** que registra qué módulos existen. Si un módulo no está en `settings.gradle.kts`, el compilador lo ignora.

#### 2.3 App como Ensamblador
El módulo `app` actúa **exclusivamente como ensamblador**. Su única función es:
- Encender la aplicación (`MainActivity`)
- Llamar a los módulos mediante `implementation project(':modulo')`
- No contiene lógica de negocio, ni VMs, ni repositorios

#### 2.4 Inyección de Dependencias
Se utiliza **Hilt** como sistema principal de inyección de dependencias:
- Cada módulo tiene su propio `@Module` con `@InstallIn(SingletonComponent::class)`
- Se crean **singletons** para cada servicio
- Koin está disponible como alternativa

#### 2.5 Inter-Module Dependency
Las comunicaciones entre módulos siguen el patrón:
```
_modulo:nombre.metodo
Ejemplo: nube.enviaPermisos, servicios.inicializarServicios
```
Cada módulo expone sus funciones a través de interfaces públicas.

#### 2.6 Navegación
- `inicio` → `dashboard` (si autenticado) o `inicio/login` (si no autenticado)
- `dashboard` → `perfil`, `configuracion` (vía BottomNavigation)
- `SplashRouter.determinarDestino()` decide la pantalla destino

#### 2.7 Catálogo de Versiones
Todas las versiones de librerías se definen en `gradle/libs.versions.toml`. Ningún módulo puede tener una versión distinta de una librería compartida.

---

### 3. Build Variants (Variantes de Compilación)

El proyecto tiene **dos variantes de compilación** configuradas:

#### 3.1 Variante `desarrollador` (Debug Admin)
```groovy
buildType {
    name = "desarrollador"
    debuggable = true
    minifyEnabled = false
}
```
**Características:**
- Acceso completo a administración de permisos desde la app
- Botón de administración visible en todas las pantallas
- `BuildConfig.DEBUG = true`
- `BuildConfig.FLAVOR = "desarrollador"`
- Puede editar permisos de usuarios, roles y configuración global

**Cómo activar:**
```
./gradlew assembleDesarrolladorDebug
```

#### 3.2 Variante `norma` (Release Normal)
```groovy
buildType {
    name = "norma"
    debuggable = false
    minifyEnabled = true
    proguardFiles = getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
    signingConfig = signingConfigs.release
}
```
**Características:**
- Usuario estándar sin acceso a administración
- R8/ProGuard activado para minificación y ofuscación
- `BuildConfig.DEBUG = false`
- `BuildConfig.FLAVOR = "norma"`
- APK optimizado y ofuscado

**Cómo activar:**
```
./gradlew assembleNormaRelease
```

#### 3.3 Diferencias en Código
```kotlin
if (BuildConfig.FLAVOR == "desarrollador") {
    // Mostrar opciones de administración
} else {
    // Modo usuario normal
}
```

---

### 4. Métodos y Patrones de Código

#### 4.1 Singleton con Hilt
```kotlin
@Singleton
@Inject
class MiServicio @Inject constructor() {
    // Instancia única global
}
```

#### 4.2 Repository Pattern (basededatos)
```kotlin
@Singleton
class AppRepository @Inject constructor(
    private val userDao: UserDao,
    private val configDao: ConfigDao
) {
    val allUsers: Flow<List<UserEntity>> = userDao.getAllUsers()
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)
}
```

#### 4.3 ViewModel con Hilt
```kotlin
@HiltViewModel
class MiViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {
    // Lógica de negocio
}
```

#### 4.4 Splash Screen Pattern
```
SplashActivity → SplashViewModel (viewModelScope.launch) → SplashRouter.determinarDestino()
→ DashboardActivity (si autenticado) o LoginActivity (si no)
```

---

### 5. Configuración de Build

#### 5.1 Archivos de Configuración
| Archivo | Ubicación | Propósito |
|---------|-----------|-----------|
| `settings.gradle.kts` | Raíz | Índice de módulos y catálogo de versiones |
| `build.gradle` (root) | Raíz | Definición de plugins globales |
| `gradle/libs.versions.toml` | `gradle/` | Diccionario de versiones de librerías |
| `gradle.properties` | Raíz | Propiedades de Gradle (JVM, caching, etc.) |
| `local.properties` | Raíz | Rutas del SDK y NDK |
| `proguard-rules.pro` | Cada módulo | Reglas de ofuscación R8 |

---

### 6. Directorios de Construcción

```
AppBase/
├── app/                    → Ensamblador principal
├── inicio/                 → Splash + Login
├── dashboard/              → Panel principal
├── nube/                   → Firebase services
├── autenticacion/          → Google OAuth
├── tema/                   → Estilo visual
├── configuracion/          → Config global
├── perfil/                 → Gestión de perfil
├── basededatos/            → Room + SQLite
├── servicios/              → Conexión intermódulos
├── metricas/               → C++ nativo (NDK)
├── cpp/                    → Cabeceras C++ compartidas
├── ios/                    → Proyecto iOS (Swift)
├── gradle/                 → Configuración Gradle + TOML
├── build/                  → Salidas de compilación
└── settings.gradle.kts     → Índice de módulos
```

---

### 7. Reglas de Desarrollo

1. **Nunca editar `app` para lógica de negocio** — solo encender módulos
2. **Nunca agregar módulos sin actualizar `settings.gradle.kts`**
3. **Nunca cambiar versiones fuera de `libs.versions.toml`**
4. **Cada módulo debe tener su propio `AndroidManifest.xml`**
5. **Usar Hilt para DI, no constructores manuales**
6. **Las funciones compartidas van en `servicios` o `nube`**
7. **C++ solo en `metricas` para algoritmos y matemáticas**
8. **iOS vive en `ios/` con código Swift nativo**
9. **ProGuard activo solo en `norma`, nunca en `desarrollador`**
10. **Verificar `BuildConfig.FLAVOR` para control de permisos**
11. **No dejar archivos de configuración basura** (scripts automáticos, settings duplicados)
12. **Usar nombres genéricos** — este proyecto es una base reutilizable

---

### 8. Comandos de Compilación

```bash
# Variante desarrollador (debug con permisos admin)
./gradlew assembleDesarrolladorDebug

# Variante norma (release optimizado)
./gradlew assembleNormaRelease

# Limpiar todo
./gradlew clean

# Verificar variantes disponibles
./gradlew tasks --all | findstr "assemble"
```

---

### 9. Tecnologías Utilizadas

- **Lenguaje**: Kotlin (Android), Swift (iOS), C++ (módulo nativo)
- **Build**: Gradle 8.5, AGP 8.4.0
- **DI**: Hilt, Koin
- **Base de datos**: Room 2.6.1, SQLite offline-first
- **Cloud**: Firebase BOM 32.7.1 (Auth, Firestore, Storage, Messaging)
- **Navegación**: Navigation 2.7.6, Compose
- **Paginación**: Paging 3.2.1
- **Red**: OkHttp 4.12.0, Retrofit 2.9.0
- **Imágenes**: Coil 2.5.0
- **Minificación**: R8 / ProGuard
- **NDK**: CMake 3.22.1
- **Version Catalog**: TOML (libs.versions.toml)

---

*Última actualización: Septiembre 2026*
*AppBase — Base Reusable Arquitectura Multimodulo Feature-Based*
