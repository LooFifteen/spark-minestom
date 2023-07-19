rootProject.name = "spark-minestom"

sourceControl {
    gitRepository(uri("https://github.com/lucko/spark.git")) {
        producesModule("me.lucko:spark-minestom")
    }
    gitRepository(uri("https://github.com/lucko/spark.git")) {
        producesModule("me.lucko:spark-common")
    }
}