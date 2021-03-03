package de.brewy.domain;

public class Measurement {

    private static Measurement instance;

    private String temperatureSensorOne;
    private String temperatureSensorTwo;

    private Measurement(){
        temperatureSensorOne = "0";
        temperatureSensorTwo = "0";
    }

    public static Measurement getInstance() {
        if (Measurement.instance == null) {
            Measurement.instance = new Measurement();
        }
        return Measurement.instance;
    }

    public String getTemperatureSensorOne() {
        return temperatureSensorOne;
    }

    public void setTemperatureSensorOne(String temperatureSensorOne) {
        this.temperatureSensorOne = temperatureSensorOne;
    }

    public String getTemperatureSensorTwo() {
        return temperatureSensorTwo;
    }

    public void setTemperatureSensorTwo(String temperatureSensorTwo) {
        this.temperatureSensorTwo = temperatureSensorTwo;
    }
}
