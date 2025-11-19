# Architecture Strategy

We follow **Clean Architecture** with **MVVM** (Model-View-ViewModel).

## Layers

1.  **Domain Layer** (Pure Kotlin)
    *   Entities: `ScavengerItem`, `Difficulty`
    *   Interfaces: `ScavengerRepository`
    *   Use Cases: `GenerateHuntUseCase`
    *   *Rule: No Android dependencies here.*

2.  **Data Layer** (Android/Libraries)
    *   `AiService`: Retrofit/Ktor client for LLM.
    *   `RoomDatabase`: Local caching.
    *   `RepositoryImpl`: Coordinates between AI and Local DB.

3.  **UI Layer** (Jetpack Compose)
    *   `HuntViewModel`: Manages state (`Loading`, `Success`, `Error`).
    *   `HuntScreen`: The bingo grid.

## Tech Stack
*   Kotlin
*   Jetpack Compose
*   Coroutines / Flow
*   Hilt (Dependency Injection) - *To be added*
*   Room (Local DB) - *To be added*
