package ru.job4j.jmm;

public class CounterThreadSynchro extends Thread {
    private CounterSynchro counterSynchro;

    public CounterThreadSynchro(CounterSynchro counterSynchro) {
        this.counterSynchro = counterSynchro;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counterSynchro.increaseCounter();
        }
    }
}