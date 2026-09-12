# REGLAS.md — AppBase

## Documentación de Normas Arquitectónicas

Este archivo define las reglas que rigen cómo se maneja cada módulo en el proyecto **AppBase**. Las nuevas reglas se agregan al final sin borrar las anteriores.

---

## Regla 1 — Independencia de Módulos

**Nunca mezclar archivos de un módulo dentro de otro módulo**, a menos que sea un módulo compartido que solo comparta lo necesario.

### Descripción
Cada módulo es una mini-aplicación independiente con su propio código fuente. Los archivos de un módulo **nunca** deben ubicarse en la carpeta de otro módulo.

### Ejemplo
```
✅ Correcto:
  dashboard/src/main/java/com/moto/project/dashboard/home/DashboardScreen.kt
  inicio/src/main/java/com/moto/project/inicio/splash/SplashScreen.kt

❌ Incorrecto:
  dashboard/src/main/java/com/moto/project/inicio/splash/SplashScreen.kt
  dashboard/src/main/java/com/moto/project/dashboard/home/DashboardScreen.kt (mezclado)
```

### Excepción
El módulo `dashboard` es una **miniApp compartida** que gestiona los módulos existentes (`perfil`, `configuracion`, `basededatos`, etc.). Solo debe contener lógica de coordinación, no lógica de negocio propia. Los datos los obtiene de los módulos que gestiona.

### Módulos Compartidos Permitidos
| Módulo | Tipo | Permite compartir |
|--------|------|-------------------|
| `tema` | Compartido | Variables visuales globales, colores, tipografía |
| `basededatos` | Compartido | DAOs, Entities, Repositorios |
| `nube` | Compartido | Servicios Firebase |
| `autenticacion` | Compartido | Gestión de autenticación |
| `configuracion` | Compartido | Configuración global singleton |
| `servicios` | Compartido | Conexión intermódulos |
| `perfil` | Compartido | Gestión de perfil de usuario |
| `dashboard` | Feature | MiniApp que coordina otros módulos |
| `inicio` | Feature | Splash y login |
| `app` | Ensamblador | Punto de entrada sin lógica de negocio |
| `metricas` | C++ Nativo | Algoritmos y matemáticas |

---

## Regla 2 — Módulo Tema como Única Fuente de Estilos

**Todo estilo visual debe venir del módulo `tema`.** Ningún módulo puede crear sus propios colores, tipografías, estilos o temas.

### Descripción
El módulo `tema` contiene **todas** las variables visuales globales:
- Colores (`Primary`, `Secondary`, `Background`, `Surface`, `Error`, `OnPrimary`, etc.)
- Tipografía (`Typography`)
- Tema (`AppTheme`)
- Cualquier otro recurso visual

### Reglas Específicas
1. **Nunca crear** `Color()`, `TextStyle()`, `Typography()` o `MaterialTheme()` con valores hardcodeados en otro módulo
2. **Siempre importar** desde `com.moto.project.theme` para obtener variables visuales
3. **Si un módulo necesita un color nuevo**, se agrega a `tema/Colors.kt` y se usa desde ahí
4. **Si un módulo necesita una tipografía nueva**, se agrega a `tema/Colors.kt` (en `Typography`) y se usa desde ahí

### Ejemplo de Uso Correcto
```kotlin
// ✅ Correcto: usar variables de tema
import com.moto.project.theme.Primary
import com.moto.project.theme.OnPrimary
import com.moto.project.theme.Typography

Column(modifier = Modifier.background(Primary)) {
    Text("Hola", style = Typography.bodyLarge)
}

// ❌ Incorrecto: crear colores hardcodeados
val myColor = Color(0xFF1565C0) // No usar esto, usar Primary
```

### Cada Módulo Responsable de Tema
- `inicio` → usa `Primary`, `OnPrimary` del tema para splash
- `dashboard` → usa `Background`, `Surface` del tema
- `configuracion` → usa `Surface`, `OnBackground` del tema
- `perfil` → usa `OnSurface`, `Background` del tema
- `basededatos` → no tiene UI directa, pero si la tuviera usa variables de tema
- `autenticacion` → usa `Primary`, `OnPrimary` del tema
- `servicios` → usa `Surface`, `OnBackground` del tema
- `app` → usa `Theme.AppBase` del tema

---

## Regla 3 — VersiónCatalog (libs.versions.toml) como Única Fuente de Versiones

**Todos los módulos deben depender de `libs.versions.toml`** para versiones de librerías. Ningún módulo puede tener una versión hardcodeada de una librería compartida.

### Descripción
El archivo `gradle/libs.versions.toml` es el **catálogo central** de versiones. Cada módulo usa `libs.nombre` para referenciar dependencias.

### Reglas Específicas
1. **Nunca** poner una versión hardcodeada en un `build.gradle` de módulo (excepto Koin si es necesario)
2. **Si se necesita una nueva librería**, se agrega al `libs.versions.toml`
3. **Si se actualiza una versión**, se actualiza solo en `libs.versions.toml`
4. **Cada módulo** tiene su propio `build.gradle` pero todos dependen del mismo catálogo de versiones

### Estructura del Catálogo
```toml
[versions]
# Versiones de librerías aquí

[libraries]
# Librerías con versión desde [versions]
# Formato: nombre = { module = "grupo:nombre", version.ref = "versionKey" }

[plugins]
# Plugins con versión desde [versions]
```

### Ejemplo
```kotlin
// En cada build.gradle de módulo:
dependencies {
    implementation libs.hilt.android        // ✅ Correcto: versión desde toml
    implementation libs.firebase.auth        // ✅ Correcto
    // implementation("com.google.dagger:hilt-android:2.50")  // ❌ Incorrecto
}
```

---

## Regla 4 — Módulo Basededatos como Gestor Central de Base de Datos

**El módulo `basededatos` es la app compartida que gestiona la base de datos** de cada módulo del proyecto.

### Descripción
El módulo `basededatos` contiene:
- La base de datos Room (`AppDataBase`)
- Todas las Entities globales
- Todos los DAOs globales
- El Repository central
- El `DatabaseModule` de inyección Hilt

### Estructura de Paquetes por Módulo
Cada módulo que necesite persistencia de datos tiene **su propio archivo independiente** dentro del paquete `basededatos`. **Nunca** mezclar archivos de diferentes módulos.

### Convención de Nombres
```
basededatos/src/main/java/com/moto/project/basededatos/
├── AppDataBase.kt                    → Base de datos principal
├── AppRepository.kt                  → Repository central
├── di/
│   └── DatabaseModule.kt             → Hilt module para inyección
├── entity/
│   ├── UserEntity.kt                 → Entidad global de usuario
│   ├── ConfigEntity.kt               → Entidad global de configuración
│   ├── DashboardEntity.kt            → Entidad para dashboard
│   ├── PerfilEntity.kt               → Entidad para perfil
│   └── ConfiguracionEntity.kt        → Entidad para configuración
├── dao/
│   ├── UserDao.kt                    → DAO global de usuario
│   ├── ConfigDao.kt                  → DAO global de configuración
│   ├── DashboardDao.kt               → DAO para dashboard
│   ├── PerfilDao.kt                  → DAO para perfil
│   └── ConfiguracionDao.kt           → DAO para configuración
├── BasededatosDashboard.kt           → Colecciones/Queries para dashboard
├── BasededatosConfiguracion.kt       → Colecciones/Queries para configuración
├── BasededatosPerfil.kt              → Colecciones/Queries para perfil
├── BasededatosTema.kt                → Colecciones/Queries para tema
└── BasededatosInicio.kt              → Colecciones/Queries para inicio
```

### Reglas Específicas
1. **Cada módulo** que necesite datos debe tener su archivo `Basededatos{NombreModulo}.kt` en el paquete `basededatos`
2. **Nunca** crear un archivo como `DashboardEntity.kt` dentro de `dashboard/` — debe ir en `basededatos/entity/DashboardEntity.kt`
3. **El archivo `BasededatosDashboard.kt`** contiene las queries, colecciones y operaciones específicas para el dashboard
4. **El repositorio `AppRepository`** es el punto de acceso central, pero cada archivo `Basededatos*` puede tener sus propias funciones de extensión
5. **Las Entities** se agrupan por módulo en la carpeta `entity/`
6. **Los DAOs** se agrupan por módulo en la carpeta `dao/`

### Ejemplo de Archivo by Módulo
```kotlin
// basededatos/BasededatosDashboard.kt
package com.moto.project.basededatos

import com.moto.project.dashboard.model.DashboardModel
import kotlinx.coroutines.flow.Flow

fun Flow<List<DashboardModel>>.filterActive(): Flow<List<DashboardModel>> {
    return this.filter { it.titulo.isNotEmpty() }
}

// basededatos/BasededatosConfiguracion.kt
package com.moto.project.basededatos

import com.moto.project.configuracion.config.AppConfig
import kotlinx.coroutines.flow.Flow

fun AppConfig.toEntity(): ConfigEntity {
    return ConfigEntity(key = "app_name", value = appName, updatedAt = System.currentTimeMillis())
}
```

### Crear un Nuevo Archivo Basededatos
Cuando se agregue un nuevo módulo, crear su archivo `Basededatos{NombreModulo}.kt`:

```bash
1. Crear archivo: basededatos/src/main/java/com/moto/project/basededatos/Basededatos{NombreModulo}.kt
2. Agregar Entity: basededatos/src/main/java/com/moto/project/basededatos/entity/{NombreModulo}Entity.kt
3. Agregar DAO: basededatos/src/main/java/com/moto/project/basededatos/dao/{NombreModulo}Dao.kt
4. Agregar query en basededatos/src/main/java/com/moto/project/basededatos/{NombreModulo}Dao.kt
5. Actualizar AppDataBase para registrar el nuevo DAO
6. Actualizar AppRepository para registrar el nuevo repositorio
```

### Regla Adicional — Colecciones por Módulo
Cada archivo `Basededatos*` debe crear las **colecciones necesarias** para su módulo dentro de la base de datos. Por ejemplo:
- `BasededatosDashboard.kt` → crea la colección/query `dashboard_items`
- `BasededatosConfiguracion.kt` → crea la colección/query `app_settings`
- `BasededatosPerfil.kt` → crea la colección/query `user_profiles`

---

## Regla 5 — Nomenclatura de Archivos por Módulo

**Cada módulo sigue una convención de nomenclatura específica** para sus archivos internos.

### Estructura de Carpetas por Módulo
```
{modulo}/src/main/java/com/moto/project/{modulo}/
├── {feature}/                    → Feature o screen
│   ├── {Feature}Screen.kt        → Pantalla Compose
│   ├── {Feature}ViewModel.kt     → ViewModel con Hilt
│   ├── {Feature}Router.kt        → Router de navegación
│   └── {Feature}Model.kt         → Modelo de datos
├── di/                           → Módulos de inyección Hilt
│   └── {Feature}Module.kt
├── model/                        → Modelos de datos del módulo
│   └── {Feature}Model.kt
├── services/                     → Servicios del módulo
│   └── {Feature}Manager.kt
└── {Feature}Activity.kt          → Actividad (si aplica)
```

### Ejemplo: módulo `inicio`
```
inicio/src/main/java/com/moto/project/inicio/
├── splash/
│   ├── SplashScreen.kt
│   ├── SplashViewModel.kt
│   ├── SplashRouter.kt
│   └── SplashModel.kt
├── di/
│   └── SplashModule.kt
├── model/
│   └── SplashModel.kt
└── LoginActivity.kt
```

### Ejemplo: módulo `dashboard`
```
dashboard/src/main/java/com/moto/project/dashboard/
├── home/
│   ├── DashboardScreen.kt
│   ├── DashboardHomeViewModel.kt
│   ├── DashboardRouter.kt
│   └── DashboardModel.kt
├── widget/
│   └── DashboardWidget.kt
└── di/
    └── DashboardModule.kt
```

---

## Regla 6 — Gestión de Permisos y Manifests

**El manifest de cada módulo debe tener solo los permisos que necesita.** El `app` solo declara `MainActivity` y la clase `Application`.

### Descripción
Cada módulo declara sus propios permisos y actividades en su `AndroidManifest.xml` ubicado en `src/main/`. El módulo `app` solo tiene el `MainActivity` y la clase `Application`.

### Reglas
1. **Cada módulo** tiene su propio `AndroidManifest.xml` con sus actividades/servicios/permisos
2. **El `app`** solo declara `MainActivity`, `AppBaseApplication` y el `NavHostFragment`
3. **Ningún módulo** debe declarar permisos que no use
4. **El `app/build.gradle`** no declara permisos, solo los `implementation project(':modulo')`

---

## Regla 7 — Registro de Módulos

**Todo nuevo módulo debe registrarse en `settings.gradle.kts`** antes de poder usarse.

### Reglas
1. **`settings.gradle.kts`** es el único archivo que registra qué módulos existen
2. Si un módulo no está en `settings.gradle.kts`, el compilador lo ignora
3. **Nunca** crear un módulo sin actualizar `settings.gradle.kts`
4. **Eliminar un módulo** requiere eliminarlo de `settings.gradle.kts` primero

---

## Regla 8 — DI con Hilt y Koin

**Hilt es el sistema principal de inyección de dependencias.** Koin está disponible como alternativa para navegación Compose.

### Descripción
- **Hilt**: Para inyección de dependencias en Activities, ViewModels, Singletons
- **Koin**: Para navegación Compose y módulos de navegación
- **Nunca** mezclar Hilt y Koin para el mismo objeto inyectado

### Reglas
1. **ViewModels** siempre usan `@HiltViewModel` con `hiltViewModel()`
2. **Singletons** usan `@Singleton @Inject` con Hilt
3. **Navegación** usa Koin `module { viewModel { ... } }`
4. **Cada módulo** tiene su propio `@Module` con `@InstallIn(SingletonComponent::class)`

---

## Regla 9 — Build Variants

**El proyecto tiene dos variantes de compilación:** `desarrollador` y `norma`.

### Reglas
1. **`desarrollador`**: `debuggable = true`, `minifyEnabled = false`, acceso admin
2. **`norma`**: `debuggable = false`, `minifyEnabled = true`, usuario estándar
3. **Verificar variante** en código: `if (BuildConfig.FLAVOR == "desarrollador")`
4. **ProGuard** activo solo en `norma`

---

## Regla 10 — C++ Nativo Solo en `metricas`

**El módulo `metricas` es el único que contiene código C++ (NDK/CMake).**

### Reglas
1. **Nunca** crear código C++ en otro módulo
2. **`metricas`** tiene `CMakeLists.txt`, `native-lib.cpp`, `MetricasManager.kt`
3. **Headers C++** van en `metricas/src/main/cpp/include/`
4. **Fuentes C++** van en `metricas/src/main/cpp/src/`
5. **El módulo `cpp/`** contiene cabeceras compartidas usadas por `metricas`

---

## Regla 11 — iOS

**El código iOS vive en `ios/MotoApp/` con Swift nativo.**

### Reglas
1. **`ios/MotoApp/`** contiene el proyecto Swift
2. **`SharedModule.swift`** tiene el estado compartido observable
3. **`Features.swift`** define la estructura feature-based para iOS
4. **Nunca** mezclar código Android e iOS en la misma carpeta

---

## Regla 12 — ProGuard/R8

**ProGuard solo se activa en la variante `norma`.**

### Reglas
1. Cada módulo tiene su `proguard-rules.pro`
2. **Reglas de corrección**: usar `-keepclassmembers`, nunca `-keepclasseshavemembernames`
3. **`app/proguard-rules.pro`** contiene las reglas principales para Hilt, Firebase, Room, Koin
4. **Crear `proguard-desarrollador.pro`** y `proguard-norma.pro` para variantes específicas

---

## Regla 13 — No Archivos Basura

**El proyecto nunca debe contener archivos de configuración basura.**

### Reglas
1. **Nunca** dejar `_setup_variants.py`, scripts automáticos de modificación de build.gradle
2. **Nunca** dejar `settings.gradle` duplicado (solo `settings.gradle.kts`)
3. **Nunca** dejar módulos `gradle/src` sin uso
4. **Nunca** dejar archivos `.gradle` que no estén registrados en `settings.gradle.kts`

---

## Regla 14 — Navegación

**La navegación sigue un patrón definido:**
- `inicio` → `dashboard` (si autenticado) o `inicio/login` (si no autenticado)
- `dashboard` → `perfil`, `configuracion` (vía BottomNavigation)
- `SplashRouter.determinarDestino()` decide la pantalla destino
- Cada `{Feature}Router.kt` contiene funciones de navegación para su feature

---

## Regla 15 — Archivos de Recursos por Módulo

**Cada módulo debe tener sus propios archivos de recursos** (`strings.xml`, `colors.xml`, `themes.xml`) en `src/main/res/values/`.

### Reglas
1. **Nunca** compartir archivos de recursos entre módulos
2. Cada módulo tiene sus propios `strings.xml`, `colors.xml`
3. **El `tema` módulo** puede tener recursos de colores compartidos que otros módulos usan
4. Los `strings.xml` vacíos se crean para evitar conflictos de recursos

---

## Regla 16 — Módulo descargaOta

**El módulo `descargaOta` gestiona la descarga de versiones OTA mediante Firebase.**

### Descripción
Este módulo se conecta a Firebase Firestore para verificar actualizaciones y Firebase Storage para descargar los APKs OTA. Es un módulo compartido que depende de `nube`, `basededatos`, `configuracion` y `tema`.

### Estructura
```
descargaOta/src/main/java/com/appbase/descargaota/
├── OtaManager.kt                    → Gestor principal de OTA
├── OtaDownloadWorker.kt             → Worker para descarga en segundo plano
├── di/
│   └── OtaModule.kt                 → Hilt module para inyección
├── model/
│   └── OtaConfig.kt                 → Configuración de OTA
├── service/
│   └── OtaFirestoreService.kt       → Servicio Firestore
└── src/main/res/
    ├── values/strings.xml
    ├── values/colors.xml
    └── xml/remote_config_defaults.xml
```

### Reglas
1. **depende de** `nube` (Firebase), `basededatos` (registro de descargas), `configuracion` (URLs), `tema` (estilos)
2. **Firestore colección**: `ota_updates` para versiones y `ota_downloads` para registro
3. **Uses-permissions**: `INTERNET`, `ACCESS_NETWORK_STATE`, `WRITE_EXTERNAL_STORAGE`, `REQUEST_INSTALL_PACKAGES`
4. **Siempre usa** colores y estilos del módulo `tema`

---

## Regla 17 — Graphify Análisis de Grafos

**Graphify es la herramienta oficial de análisis de dependencias del proyecto.**

### Descripción
Graphify analiza la estructura del proyecto generando un grafo de dependencias entre módulos, archivos, clases y funciones. Ayuda a encontrar fragmentaciones fuera de cada módulo y asegura la máxima independencia.

### Uso de comandos
```bash
# Generar el grafo completo (extracción sin LLM)
python -m graphify update "D:\AppModular\AppBase" --no-cluster

# Generar árbol interactivo con D3.js
python -m graphify tree --graph "graphify-out/graph.json" --output "GRAPHIFYOUT/GRAPH_TREE.html" --root "D:\AppModular\AppBase"

# Generar diagrama de flujo con Mermaid (Call Flow)
python -m graphify export callflow-html --graph "graphify-out/graph.json" --output "GRAPHIFYOUT/CALLFLOW.html"

# Reclustering y reporte
python -m graphify cluster-only "D:\AppModular\AppBase" --graph "graphify-out/graph.json" --no-viz

# Consultas al grafo
python -m graphify query "¿Qué módulos dependen de basededatos?"
python -m graphify god-nodes --top 10          # Los más conectados
python -m graphify affected "basededatos"       # Impacto de cambios
```

### Salida
Todo el análisis se guarda en `GRAPHIFYOUT/`:
| Archivo | Descripción |
|---------|-------------|
| `index.html` | Dashboard interactivo principal |
| `GRAPH_TREE.html` | Árbol D3.js interactivo con zoom/pan |
| `CALLFLOW.html` | Diagramas Mermaid con call flow |
| `graph.json` | Datos completos del grafo |
| `index.md` | Reporte de análisis |

### Reglas
1. **`GRAPHIFYOUT/`** está en la raíz del proyecto
2. **Regenerar** el grafo cada vez que se agregue un nuevo módulo
3. **Verificar** la independencia de módulos con `graphify query`
4. **Buscar fragmentaciones** con `graphify affected`
5. **El `index.html`** es la vista principal interactiva

---

*Última actualización: Septiembre 2026*
*AppBase — Reglas Arquitectónicas*
