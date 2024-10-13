
## Speedometer in Compose
![ezgif-6-90e2f12730](https://github.com/user-attachments/assets/bc911d28-6de1-460d-91ae-b93fcffc2528)


## Gradle Integration

### Step A: Add Maven Repository
In your project-level build.gradle or settings.gradle file, add the JitPack repository:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step B: Add Dependencies [![](https://jitpack.io/v/AndroidCrafts-man/RenderSpy.svg)](https://jitpack.io/#AndroidCrafts-man/RenderSpy)
Next, include the library in your app-level build.gradle file. Replace x.x.x with the latest version

```groovy
dependencies {
	        implementation 'com.github.LogicFalcon:SpeedometerCompose:Tag'
}
```

### Get Started

            ProtectionMeter(
                    modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally),
                    inputValue = sliderPosition,
                    trackColor = Color.Gray,
                    progressColors = listOf(Color.Green, Color.Blue),
                    innerGradient = Color.White,
                    percentageColor = Color.Black,
                    needleColor = Color.Black
                    )


### Github : 
https://github.com/LogicFalcon

### Gmail :
saeedkhattak1.outlook.com


