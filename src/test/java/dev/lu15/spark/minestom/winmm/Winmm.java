package dev.lu15.spark.minestom.winmm;

import com.sun.jna.Library;

public interface Winmm extends Library {

    int timeBeginPeriod(int period);

    int timeEndPeriod(int period);

}
