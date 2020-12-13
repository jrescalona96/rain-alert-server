package com.jrescalona.rainalertserver.model;

public class Precipitation {
    private double percentRainChance;
    private double quantity;

    public Precipitation(double percentage, double quantity) {
        this.percentRainChance = percentage;
        this.quantity = quantity;
    }

    public double getPercentRainChance() {
        return percentRainChance;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setPercentRainChance(double percentRainChance) {
        this.percentRainChance = percentRainChance;
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
        return quantity == precipitation.getQuantity() && percentRainChance == precipitation.getPercentRainChance();

    }

    @Override
    public String toString() {
        return String.format("Chance of %.1f%% of about %.1f.", percentRainChance, quantity);
    }
}
