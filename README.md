# Android MVVM Example with Hilt, Coroutines, Retrofit, and Compose

This Android example project demonstrates the implementation of the Model-View-ViewModel (MVVM) architecture using modern Android technologies. The project showcases the use of Hilt for dependency injection, Coroutines for asynchronous operations, Retrofit for API communication, and Jetpack Compose for building the user interface. It also emphasizes the importance of handling API responses in a state-based manner using sealed classes and showcases the usage of named qualifiers for Hilt with annotations.

## Prerequisites

Before you begin, ensure that you have the following set up:

- Android Studio with the latest Android SDK and Gradle version.
- A basic understanding of Android development.
- Familiarity with MVVM architecture and Android Jetpack components.

## Features

- MVVM architecture
- Dependency injection with Hilt
- Asynchronous operations with Coroutines
- Network communication with Retrofit
- User interface built using Jetpack Compose
- Handling API responses using sealed classes for state-based management
- Usage of named qualifiers for Hilt with annotations

## Project Structure

The project is organized into packages to maintain a clean and structured codebase:

- **data**: Contains data sources, repositories, and API service interfaces.
- **di**: Contains Hilt modules and annotations.
- **model**: Defines data models.
- **ui**: Compose UI components and ViewModels.
- **util**: Utility classes and extensions.
