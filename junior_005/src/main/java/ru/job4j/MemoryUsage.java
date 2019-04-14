package ru.job4j;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 14.04.2019
 */
public class MemoryUsage {

    public static class User {

        private String name;

        public Integer value;

        public User(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println(String.format("%s is finalized.", this));
        }

        @Override
        public String toString() {
            return "User{" + "name=" + name + '}';
        }
    }

    public static void main(String[] args) {
        System.out.println("start");
        info();
        for (int i = 1; i < 5001; i++) {
            System.out.println(new User(("User No." + i), Integer.valueOf(i * 1000)));
        }
        System.out.println("finish");
        info();
    }

    public static void info() {

        int mb = 1024 * 1024;

        //Getting runtime reference from system.
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Printing used memory
        System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Printing free memory
        System.out.println("Free Memory:" + runtime.freeMemory() / mb);

        //Printing total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Printing Maximum available memory
        System.out.println("Maximum Memory:" + runtime.maxMemory() / mb);
    }
}