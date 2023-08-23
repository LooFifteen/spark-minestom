# spark-minestom

## Dependencies
```kts
repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots/") // spark
    maven("https://jitpack.io/") // minestom-ce
    maven("https://repo.hypera.dev/snapshots/") // spark-minestom
}

dependencies {
    implementation("dev.lu15:spark-minestom:1.10-SNAPSHOT")
}
```

## Usage
```java
// Enable Spark
Spark.enable(Path);

// Disable Spark
Spark.disable();
```