package solarsync.dashboard.solarsynbackend.entities.dtos;

public class SensorData {
    private double temperature;
    private double humidity;

    public SensorData() {
    }

    public SensorData(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }
}