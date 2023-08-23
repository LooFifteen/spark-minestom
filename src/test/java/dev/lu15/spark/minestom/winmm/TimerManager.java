package dev.lu15.spark.minestom.winmm;

import com.sun.jna.Native;
import org.jetbrains.annotations.NotNull;

public final class TimerManager {

    private final @NotNull Winmm winmm;

    public TimerManager() {
        this.winmm = Native.load("winmm", Winmm.class);
    }

    public void addTimerResolution(int resolution) {
        this.winmm.timeBeginPeriod(resolution);
    }

    public void removeTimerResolution(int resolution) {
        this.winmm.timeEndPeriod(resolution);
    }

}
