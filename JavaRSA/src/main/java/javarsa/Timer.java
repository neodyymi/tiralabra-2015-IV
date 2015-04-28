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

    public static double getTimeInMilliseconds() {
        return time/1000000.00;
    }
    
    public static long getTime() {
        return time;
    }

    public static void setStartTime() {
        startTime = System.nanoTime();
    }

    public static void stopTime() {
        time = System.nanoTime() - startTime;
    }
}
