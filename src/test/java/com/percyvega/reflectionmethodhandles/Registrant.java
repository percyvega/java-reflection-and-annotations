package com.percyvega.reflectionmethodhandles;

public class Registrant {
    private static int count;

    private String name;
    private long savingsAmount;

    public Registrant() {
        // not thread safe
        count++;

        setSavingsAmount(count * 1000);
    }

    public Registrant(String name) {
        this();
        this.name = name;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Registrant.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSavingsAmount() {
        return savingsAmount;
    }

    private void setSavingsAmount(long savingsAmount) {
        this.savingsAmount = savingsAmount;
    }

    @Override
    public String toString() {
        return "Registrant{" +
                "name='" + name + '\'' +
                ", savingsAmount=" + savingsAmount +
                '}';
    }
}
