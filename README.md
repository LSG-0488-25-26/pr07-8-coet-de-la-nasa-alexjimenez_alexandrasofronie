# 🎬 MediaApp - Aplicación de Películas y Series

Aplicación de contenido multimedia desarrollada en **Kotlin** con **Jetpack Compose** siguiendo el patrón de arquitectura **MVVM** con componentes Lazy.

## 📱 Descripción

MediaApp es una aplicación Android que muestra una lista interactiva de películas y series con información detallada de cada contenido, incluyendo detalles completos como director, reparto, duración, puntuación y sinopsis.

## ✨ Características

- 📋 **Lista de Contenido**: Visualización de películas y series con LazyColumn
- 🎬 **Diferenciación por tipo**: Distinción visual entre películas y series
- 📊 **Información detallada**: Director, reparto, duración, año, puntuación y sinopsis
- ⭐ **Sistema de valoración**: Puntuación del 0 al 10 para cada contenido
- 🎯 **Vista detalle**: Pantalla completa con información ampliada de cada película/serie
- 🧭 **Navegación fluida**: Sistema de navegación entre lista y detalle
- 🎨 **Interfaz moderna**: Desarrollada con Jetpack Compose y Material Design 3
- 🔄 **API en desarrollo**: Preparada para integrar datos desde API externa

## 🏗️ Arquitectura

El proyecto sigue el patrón **MVVM (Model-View-ViewModel)** con LiveData para gestión reactiva de datos:

```
├── model/
│   ├── Media.kt                # Data classes (Media, MediaDetails, MediaType)
│   └── Pokemon.kt              # [Deprecated] Modelo anterior
├── repository/
│   └── MediaRepository.kt      # Fuente de datos (preparado para API)
├── view/
│   ├── MediaListScreen.kt      # Pantalla de lista de contenido
│   ├── MediaDetailScreen.kt    # Pantalla de detalle
│   └── components/
│       └── MediaItem.kt        # Componente de cada item
├── viewmodel/
│   └── MediaViewModel.kt       # Lógica y gestión de estado
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
- **API**: En desarrollo (preparada para integración futura)

## 📋 Funcionalidades Técnicas

### Modelo de Datos (Media.kt)
- **MediaType Enum**: Diferencia entre MOVIE y SERIES
- **Media Data Class**: Clase principal con:
  - `title`: Título del contenido
  - `mediaType`: Tipo (Película o Serie)
  - `genre`: Género (Acción, Drama, Comedia, etc.)
  - `image`: Imagen de portada
  - `year`: Año de lanzamiento
  - `rating`: Puntuación del 0 al 10
  - `description`: Sinopsis
  - `details`: Detalles adicionales

- **MediaDetails Data Class**: Información adicional con:
  - `duration`: Duración del contenido
  - `director`: Director
  - `cast`: Lista de actores principales
  - `seasons`: Número de temporadas (solo series)
  - `episodes`: Número de episodios (solo series)

### MediaViewModel
- Gestión de estado reactiva con LiveData
- `_mediaList`: LiveData para la lista de contenido
- `_selectedMedia`: LiveData para el contenido seleccionado
- Funciones:
  - `loadMedia()`: Carga la lista desde el repositorio
  - `selectMedia()`: Selecciona un contenido para ver el detalle

### MediaRepository
- Fuente de datos simulada (en desarrollo para API)
- Datos de ejemplo de películas y series
- Preparado para integración con API externa

### Navegación
- Sistema de rutas con sealed class
- Navegación entre pantallas:
  - `ListScreen` → `DetailScreen` → vuelta con botón

### LazyColumn
- Renderizado eficiente de la lista
- `verticalArrangement` con espaciado personalizado
- `contentPadding` para evitar que el último elemento se corte

## 🎮 Cómo Usar la App

1. **Lista de Contenido**: Visualiza todas las películas y series disponibles
2. **Selección**: Toca cualquier película/serie para ver sus detalles completos
3. **Vista Detalle**: 
   - Imagen de portada del contenido
   - Título, año y género
   - Valoración (del 0 al 10)
   - Director y reparto principal
   - Duración (para películas) o episodios/temporadas (para series)
   - Sinopsis completa
4. **Volver**: Botón "Volver a la lista" para regresar

## 🛠️ Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/tu-usuario/pr07-8-coet-de-la-nasa-alexjimenez_alexandrasofronie.git
```

2. Abre el proyecto en **Android Studio**

3. Sincroniza las dependencias de Gradle

4. Ejecuta la aplicación en un emulador o dispositivo físico

5. **(Próximamente)** Configura la API key para acceso a datos externos

## 📦 Requisitos

- Android Studio Hedgehog o superior
- Kotlin 1.9+
- Android SDK 24+ (Android 7.0)
- Gradle 8.0+
- Jetpack Compose

## 🎨 Capturas de Pantalla

### Lista de Contenido
*(Capturas en desarrollo)*

### Detalle de Película/Serie
*(Capturas en desarrollo)*

## 📊 Contenido Disponible

La aplicación incluirá una amplia selección de películas y series con información completa:

### 🎬 Películas de Ejemplo
- Películas de diversos géneros (Acción, Drama, Comedia, Ciencia Ficción, etc.)
- Información completa: Director, reparto, duración, año, valoración y sinopsis

### 📺 Series de Ejemplo
- Series de múltiples géneros
- Información detallada: Creador, reparto, temporadas, episodios, año, valoración y sinopsis

**Nota**: El catálogo completo se actualizará cuando se integre la API externa.

## 📝 Características del Código

- ✅ Patrón MVVM correctamente implementado
- ✅ LiveData para gestión reactiva de estado
- ✅ Código limpio y organizado
- ✅ Comentarios en español
- ✅ Separación clara de responsabilidades
- ✅ Modelo de datos robusto con enum MediaType
- ✅ Componentes reutilizables
- ✅ Preparado para integración con API externa
- ✅ Soporte para películas y series en una misma estructura
- ✅ Data classes con propiedades opcionales para flexibilidad

## 🔄 Estado del Proyecto

### ✅ Completado
- Modelo de datos (Media, MediaDetails, MediaType)
- Documentación actualizada

### 🚧 En Desarrollo
- Repository con datos de ejemplo
- ViewModel actualizado
- Pantallas de lista y detalle adaptadas
- Integración con API externa
- Sistema de búsqueda y filtros

## 👨‍💻 Autores

**Alex Jiménez**  
**Alexandra Sofronie**

Desarrollo Aplicaciones Multiplataforma - La Salle

## 📄 Licencia

Este proyecto es parte de un ejercicio académico para la asignatura M07 - Android Studio.

---

🎬 **¡Disfruta del mejor contenido multimedia!** 📺
