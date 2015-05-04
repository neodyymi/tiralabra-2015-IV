/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa;

/**
 *
 * @author vrsaari
 */
public class Timer {

    private static long time;
    private static long startTime;

    /**
     * 
     * @return time in milliseconds
     */
    public static double getTimeInMilliseconds() {
        return time/1000000.00;
    }
    
    /**
     * 
     * @return time in nanoseconds
     */
    public static long getTime() {
        return time;
    }

    /**
     * sets starting time to current time
     */
    public static void setStartTime() {
        startTime = System.nanoTime();
    }

    /**
     * counts time from starting time and stores it
     */
    public static void stopTime() {
        time = System.nanoTime() - startTime;
    }
}
