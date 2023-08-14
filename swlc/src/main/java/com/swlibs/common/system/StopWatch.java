package com.swlibs.common.system;

/**
 * Таймер, для измерения промежутков времени
 */
public class StopWatch {

    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    /**
     * Запускает измерение.
     */
    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    /**
     * Останавливает измерение
     */
    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    /**
     * Перезапускает измерение
     */
    public void restart() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    /**
     * Вернет количество миллисекунд с начала измерения до текущего момента,
     * если таймер не был остановлен, иначе с момента остановки.
     *
     * @return elaspsed time in milliseconds
     */
    public long getElapsedTime() {
        long elapsed;
        if (running) {
            elapsed = (System.currentTimeMillis() - startTime);
        } else {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }

    /**
     * Вернет количество секунд с начала измерения до текущего момента,
     * если таймер не был остановлен, иначе с момента остановки.
     *
     * @return elaspsed time in seconds
     */
    public long getElapsedTimeSecs() {
        long elapsed;
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        } else {
            elapsed = ((stopTime - startTime) / 1000);
        }
        return elapsed;
    }
}