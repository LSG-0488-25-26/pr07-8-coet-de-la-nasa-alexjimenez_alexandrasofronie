# 🎬 MediaApp - Aplicación de Películas y Series

Aplicación de contenido multimedia desarrollada en **Kotlin** con **Jetpack Compose** siguiendo el patrón de arquitectura **MVVM**, integrada con **TMDB API** para obtener datos en tiempo real.

## 📱 Descripción

MediaApp es una aplicación Android que muestra una lista interactiva de películas y series populares obtenidas desde **The Movie Database (TMDB)**, con información detallada de cada contenido, incluyendo pósters, géneros, puntuaciones y sinopsis.

## ✨ Características

- 📋 **Lista de Contenido**: Visualización de películas y series populares con LazyColumn
- 🎬 **Diferenciación por tipo**: Distinción visual entre películas y series
- 🌐 **Integración con TMDB API**: Datos en tiempo real desde The Movie Database
- 🖼️ **Carga de imágenes**: Pósters cargados desde URLs con Glide Compose
- 📊 **Información detallada**: Género, año, puntuación y sinopsis de cada contenido
- ⭐ **Sistema de valoración**: Puntuación del 0 al 10 desde TMDB
- 🎯 **Vista detalle**: Pantalla completa con información ampliada de cada película/serie
- 🧭 **Navegación fluida**: Sistema de navegación entre lista y detalle
- 🎨 **Interfaz moderna**: Desarrollada con Jetpack Compose y Material Design 3
- ⚡ **Operaciones asíncronas**: Uso de Kotlin Coroutines para llamadas a la API
- 🔄 **Estado de carga**: Indicador visual mientras se obtienen los datos

## 🏗️ Arquitectura

El proyecto sigue el patrón **MVVM (Model-View-ViewModel)** con LiveData para gestión reactiva de datos y Retrofit para consumo de API REST:

```
├── model/
│   ├── Media.kt                # Data classes (Media, MediaDetails, MediaType, GenreMapper)
│   ├── Result_Movies.kt        # Modelo de respuesta de películas de TMDB
│   ├── Result_Series.kt        # Modelo de respuesta de series de TMDB
│   ├── TMDB_Response_Movies.kt # Wrapper de respuesta de películas
│   └── TMD_Response_Series.kt  # Wrapper de respuesta de series
├── network/
│   └── TMDBApiService.kt       # Interfaz de Retrofit para TMDB API
├── repository/
│   └── MediaRepository.kt      # Repositorio con llamadas a la API
├── view/
│   ├── MediaListScreen.kt      # Pantalla de lista de contenido
│   ├── MediaDetailScreen.kt    # Pantalla de detalle
│   └── components/
│       └── MediaItem.kt        # Componente de cada item
├── viewmodel/
│   └── MediaViewModel.kt       # Lógica y gestión de estado con corrutines
├── ui/theme/
│   ├── Color.kt                # Colores y estilos
│   ├── Theme.kt                # Tema de la app
│   └── Type.kt                 # Tipografía
├── Routes.kt                   # Sistema de navegación
└── MainActivity.kt             # Actividad principal
```

## 🚀 Tecnologías Utilizadas

- **Lenguaje**: Kotlin
- **UI Framework**: Jetpack Compose
- **Arquitectura**: MVVM
- **Gestión de estado**: LiveData (MutableLiveData)
- **Navegación**: Navigation Compose
- **Material Design 3**: Material3 Components
- **Lazy Components**: LazyColumn para listas eficientes
- **API REST**: Retrofit 2 + Gson Converter
- **Manejo de imágenes**: Glide Compose para carga de imágenes desde URLs
- **Asincronía**: Kotlin Coroutines (viewModelScope)
- **Networking**: OkHttp con Logging Interceptor para debugging
- **API externa**: The Movie Database (TMDB)

## 📋 Funcionalidades Técnicas

### Modelo de Datos (Media.kt)
- **MediaType Enum**: Diferencia entre MOVIE y SERIES
- **Media Data Class**: Clase principal con:
  - `id`: Identificador único del contenido
  - `title`: Título del contenido
  - `mediaType`: Tipo (Película o Serie)
  - `genre`: Género (Acción, Drama, Comedia, etc.)
  - `imageUrl`: URL del póster (cargado desde TMDB)
  - `year`: Año de lanzamiento
  - `rating`: Puntuación del 0 al 10
  - `description`: Sinopsis
  - `details`: Detalles adicionales (nullable)

- **MediaDetails Data Class**: Información adicional con:
  - `duration`: Duración del contenido
  - `director`: Director
  - `cast`: Lista de actores principales
  - `seasons`: Número de temporadas (solo series)
  - `episodes`: Número de episodios (solo series)

- **GenreMapper Object**: Mapea IDs de géneros de TMDB a nombres en español
  - Convierte códigos numéricos (28, 12, 35...) a géneros legibles ("Acción", "Aventura", "Comedia"...)

- **Extension Functions**: `toMedia()`
  - Convierte respuestas de la API (`Result_Movies`, `Result_Series`) al modelo `Media` de la app

### TMDBApiService (Retrofit)
- Interfaz de Retrofit para consumir la API de TMDB
- Endpoints implementados:
  - `getPopularMovies()`: Obtiene películas populares
  - `getPopularSeries()`: Obtiene series populares
- Configuración con `GsonConverterFactory` para conversión JSON
- `BASE_URL`: `https://api.themoviedb.org/3/`

### MediaRepository
- Repositorio con llamadas a la API mediante Retrofit
- Función `suspend getMediaList()`:
  - Realiza llamadas asíncronas a TMDB API con corrutinas
  - Combina películas y series en una sola lista
  - Mapea las respuestas JSON a objetos `Media` con `toMedia()`
  - Manejo de errores con `try-catch` y logging
- Usa API Key para autenticación

### MediaViewModel
- Gestión de estado reactiva con LiveData y corrutinas
- `_mediaList`: LiveData para la lista de contenido
- `_selectedMedia`: LiveData para el contenido seleccionado
- `_isLoading`: LiveData para el estado de carga
- Funciones:
  - `loadMedia()`: Carga la lista desde el repositorio usando `viewModelScope.launch`
  - `selectMedia()`: Selecciona un contenido para ver el detalle
- Las corrutinas se cancelan automáticamente cuando el ViewModel se destruye

### Navegación
- Sistema de rutas con sealed class
- Navegación entre pantallas:
  - `ListScreen` → `DetailScreen` → vuelta con botón

### LazyColumn
- Renderizado eficiente de la lista
- `verticalArrangement` con espaciado personalizado
- `contentPadding` para evitar que el último elemento se corte

## 🎮 Cómo Usar la App

1. **Carga Inicial**: La app carga automáticamente las películas y series populares desde TMDB
2. **Indicador de Carga**: Visualiza un indicador mientras se obtienen los datos
3. **Lista de Contenido**: Visualiza todas las películas y series populares disponibles
   - Póster del contenido
   - Título y año
   - Tipo (Película/Serie) y género
   - Valoración con estrellas
4. **Selección**: Toca cualquier película/serie para ver sus detalles completos
5. **Vista Detalle**: 
   - Póster de alta calidad del contenido
   - Título, año y género
   - Valoración (del 0 al 10) desde TMDB
   - Sinopsis completa
   - **Detalles adicionales** (disponibilidad según contenido):
     - Director y reparto principal
     - Duración (para películas) o episodios/temporadas (para series)
6. **Volver**: Botón "Volver a la lista" para regresar

## 🛠️ Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/tu-usuario/pr07-8-coet-de-la-nasa-alexjimenez_alexandrasofronie.git
```

2. Abre el proyecto en **Android Studio**

3. Sincroniza las dependencias de Gradle

4. **Configura tu API Key de TMDB**:
   - Regístrate en [The Movie Database (TMDB)](https://www.themoviedb.org/)
   - Obtén tu API Key desde tu perfil
   - Reemplaza la API Key en `MediaRepository.kt`:
     ```kotlin
     private val apiKey = "TU_API_KEY_AQUI"
     ```

5. **Asegúrate de tener permisos de Internet** (ya incluidos en `AndroidManifest.xml`)

6. Ejecuta la aplicación en un emulador o dispositivo físico

## 📦 Requisitos

- Android Studio Hedgehog o superior
- Kotlin 1.9+
- Android SDK 24+ (Android 7.0)
- Gradle 8.0+
- Jetpack Compose
- **Conexión a Internet** (para obtener datos desde TMDB API)
- **API Key de TMDB** (gratuita con registro)

## 📚 Dependencias Principales

```gradle
// Retrofit para consumo de API REST
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// OkHttp Logging Interceptor para debugging
implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

// Kotlin Coroutines para operaciones asíncronas
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

// LiveData para gestión reactiva
implementation("androidx.compose.runtime:runtime-livedata:1.5.4")

// Glide Compose para carga de imágenes desde URLs
implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
```

## 🎨 Capturas de Pantalla

### Lista de Contenido
*(Capturas en desarrollo)*

### Detalle de Película/Serie
*(Capturas en desarrollo)*

## 📊 Contenido Disponible

La aplicación obtiene contenido en tiempo real desde **TMDB (The Movie Database)**:

### 🎬 Películas Populares
- Películas más populares del momento desde TMDB
- Información incluida: Título, póster, género, año, valoración y sinopsis
- Géneros: Acción, Aventura, Comedia, Drama, Terror, Ciencia Ficción, Animación, y más

### 📺 Series Populares
- Series más populares del momento desde TMDB
- Información incluida: Título, póster, género, año, valoración y sinopsis
- Géneros: Acción, Drama, Comedia, Ciencia Ficción, Misterio, Crimen, y más

**Nota**: El contenido se actualiza automáticamente con cada ejecución de la app, obteniendo las películas y series más populares desde TMDB.

## 📝 Características del Código

- ✅ Patrón MVVM correctamente implementado
- ✅ LiveData para gestión reactiva de estado
- ✅ Kotlin Coroutines para operaciones asíncronas
- ✅ Retrofit 2 para consumo de API REST
- ✅ Glide Compose para carga eficiente de imágenes
- ✅ Código limpio y organizado
- ✅ Comentarios en español para mejor comprensión
- ✅ Separación clara de responsabilidades (MVVM)
- ✅ Modelo de datos robusto con enum `MediaType`
- ✅ Mapeo de géneros desde IDs numéricos a nombres legibles
- ✅ Extension functions para conversión de modelos API a modelos de app
- ✅ Manejo de errores con `try-catch` y logging
- ✅ Componentes reutilizables en Compose
- ✅ Integración completa con TMDB API
- ✅ Soporte para películas y series en una misma estructura
- ✅ Data classes con propiedades opcionales para flexibilidad
- ✅ Estado de carga visible en la UI
- ✅ Permisos de Internet configurados en `AndroidManifest.xml`

## 👨‍💻 Autores

**Alex Jiménez**  
**Alexandra Sofronie**

Desarrollo Aplicaciones Multiplataforma - La Salle

## 📄 Licencia

Este proyecto es parte de un ejercicio académico para la asignatura M07 - Android Studio.

---

🎬 **¡Disfruta del mejor contenido multimedia!** 📺
