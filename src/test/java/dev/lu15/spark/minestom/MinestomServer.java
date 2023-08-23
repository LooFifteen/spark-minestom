package dev.lu15.spark.minestom;

import dev.lu15.spark.minestom.winmm.TimerManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.permission.Permission;

final class MinestomServer {

    private static final int TIMER_RESOLUTION = 10;

    private MinestomServer() {

    }

    public static void main(String[] args) throws IOException {
        // Set up timing fix
        TimerManager timerManager;
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            timerManager = new TimerManager();
            timerManager.addTimerResolution(TIMER_RESOLUTION);
        } else timerManager = null;

        // Set up Minestom
        MinecraftServer server = MinecraftServer.init();

        MojangAuth.init();

        // Enable Spark and create a temporary directory for it to store configuration in
        Path sparkDirectory = Files.createTempDirectory("spark");
        Spark.enable(sparkDirectory);

        InstanceContainer instance = MinecraftServer.getInstanceManager().createInstanceContainer();
        instance.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK));

        MinecraftServer.getGlobalEventHandler().addListener(PlayerLoginEvent.class, event -> {
            event.setSpawningInstance(instance);
            Player player = event.getPlayer();
            player.setRespawnPoint(new Pos(0, 41, 0));
            player.addPermission(new Permission("spark"));
        });

        MinecraftServer.getSchedulerManager().buildShutdownTask(() -> {
            // Disable Spark
            Spark.disable();

            // Delete the temporary directory
            try (Stream<Path> walk = Files.walk(sparkDirectory)) {
                walk.sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Disable timing fix
            if (timerManager != null) timerManager.removeTimerResolution(TIMER_RESOLUTION);
        });

        server.start("0.0.0.0", 25565);
    }

}
