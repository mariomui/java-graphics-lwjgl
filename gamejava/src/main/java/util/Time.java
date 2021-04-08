package util;

public class Time {
    // supposedly loading the class via classloader should initialize this class.
    public static float timeStarted = System.nanoTime();

    public static float getTime() {
        // coerce nanoTime to a float
        float currentTime = (float)System.nanoTime();
        // 1 s = 1x 10^9ns;
        // so i have .0000000001ns; elapses.
        /*
          (x)s    1s
          ----  =  ----
          1ns      1x 10^9ns

          1x10^-9s  DESIRED
           ----     ---
           1 ns;    GIVEN ns

         ratio * GIVEN ns = DESIRED ns
         */
        return (float)((currentTime- Time.timeStarted) * 1E-9);
    }
}