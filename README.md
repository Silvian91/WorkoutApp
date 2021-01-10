# Workout Notebook
## How to run the project
Since this application is not published on the Play Store it can only be run by downloading the project and running it with Android Studio:

1. Download and Install Android Studio
2. Clone the repository using SSH `git clone git@github.com:Silvian91/WorkoutApp.git`
3. In Android Studio open the project
4. Run the debug variant on a device or emulator
5. Upon reaching the Home screen the user is asked for location permission, this is used to get the user's current location and weather

### API
The app is using the [Open Weather Map](https://rapidapi.com/community/api/open-weather-map) and the 
[Inspirational Quotes](https://rapidapi.com/ipworld/api/quotes-inspirational-quotes-motivational-quotes) api. No change needed to the api key

## Teck Stack
### Patterns
Clean Architecture

MVVM

Repository Pattern

### Technologies
Kotlin


Dagger Android

RxJava2 with RxKotlin, RxBinding3, AutoDispose

AndroidX

OkHttp

Retrofit


Room


Material Components


JUnit5

Mockk

Gson

Espresso with JUnit4


Timber

## Tools
Chucker
LeakCanary
