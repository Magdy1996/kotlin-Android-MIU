# My Application

This is a simple Android application that demonstrates a clean architecture approach to building a UI with Jetpack Compose. The app displays a series of images with titles and allows the user to navigate through them.

## Architecture

The application follows the architectural principles outlined in the project requirements, emphasizing a clear separation of concerns between the UI, ViewModel, and data layers.

*   **UI Layer (`ui` package):** Built with Jetpack Compose, this layer is responsible for rendering the UI based on the state provided by the ViewModel. It is stateless and reacts to changes in the `UiState`.
*   **ViewModel Layer (`ui.explorer` package):** The `ImageViewModel` is responsible for holding and managing the UI state. It exposes the state as a `StateFlow` and provides methods for the UI to interact with the data layer.
*   **Data Layer (`data` package):** This layer consists of a repository that abstracts the data source from the rest of the application. The `IImageRepository` interface defines the contract for the data layer, and the `StaticImageRepository` provides a concrete implementation with a static list of images.

## Screenshots

### Running App

Here are a few screenshots of the application in action:

![App Screenshot 1](screenshots/SCREENSHOT_APP_1.png)
![App Screenshot 2](screenshots/SCREENSHOT_APP_2.png)

### Test Report

A screenshot of the unit test report, showing that all tests passed successfully:

![Test Report](screenshots/SCREENSHOT_TEST_REPORT.png)

## How to Build and Run

1.  Clone the repository.
2.  Open the project in Android Studio.
3.  Sync Gradle.
4.  Run the `app` configuration.

## How to Run Tests

To run the unit tests, you can either:

*   Run the `./gradlew testDebugUnitTest` command in the terminal.
*   Use the Gradle tool window in Android Studio to run the `testDebugUnitTest` task.

The test report will be generated in `app/build/reports/tests/testDebugUnitTest/`.
