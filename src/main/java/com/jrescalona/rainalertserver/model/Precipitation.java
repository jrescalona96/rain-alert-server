package com.jrescalona.rainalertserver.model;

public class Precipitation {
    private final double percentage;
    private final double quantity;

    public Precipitation(double percentage, double quantity) {
        this.percentage = percentage;
        this.quantity = quantity;
    }

    public double getPercentage() {
        return percentage;
    }

    public double getQuantity() {
        return quantity;
    }


    /**
     * Checks equality of field values
     * @param precipitation
     * @return true if all fields are the same, false otherwise
     */
    public boolean equals(Precipitation precipitation) {
        return quantity == precipitation.getQuantity() && percentage == precipitation.getPercentage();

    }

    @Override
    public String toString() {
        return String.format("Chance of %.1f%% of about %.1f.", percentage, quantity);
    }
}
